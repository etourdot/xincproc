package org.etourdot.xincproc.xinclude.sax;

import net.sf.saxon.om.DocumentInfo;
import net.sf.saxon.s9api.SAXDestination;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.trans.XPathException;
import org.etourdot.xincproc.xinclude.XIncProcConfiguration;
import org.etourdot.xincproc.xinclude.XIncProcUtils;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeResourceException;
import org.etourdot.xincproc.xpointer.XPointerEngine;
import org.etourdot.xincproc.xpointer.exceptions.XPointerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.*;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.NamespaceSupport;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.namespace.QName;
import javax.xml.transform.sax.SAXSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 18/12/12
 * Time: 23:13
 */
public class XIncProcXIncludeFilter extends XMLFilterImpl implements LexicalHandler {
    private static final Logger LOG = LoggerFactory.getLogger(XIncProcXIncludeFilter.class);
    public static final String FIXUP_XML_LANG = "fixup-xml-lang";
    public static final String FIXUP_XML_BASE = "fixup-xml-base";
    private final XIncludeContext context;
    private final Map<String, String> namespaces = new HashMap<String, String>();
    private int level;
    private final Stack<String> currentLangStack;

    public XIncProcXIncludeFilter(XIncludeContext context)
    {
        this.context = context;
        this.currentLangStack = new Stack<String>();
        currentLangStack.push(context.getLanguage());
        LOG.debug("new XIncProcXIncludeFilter context={}", context);
    }

    public XIncludeContext getContext()
    {
        return context;
    }

    @Override
    public void startDocument() throws SAXException
    {
        level = 0;
        if (!context.isInjectingXInclude())
        {
            LOG.trace("startDocument@{}", Integer.toHexString(hashCode()));
            super.startDocument();
        }
    }

    @Override
    public void endDocument() throws SAXException
    {
        if (!context.isInjectingXInclude()) {
            LOG.trace("endDocument@{}:{}", Integer.toHexString(hashCode()), context.isNeedSecondPass());
            super.endDocument();
        }
     }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException
    {
        LOG.trace("startElement@{}: {}, {}, {}", Integer.toHexString(hashCode()), uri, localName, qName);
        level ++;
        XIncludeAttributes xIncludeAttributes;
        xIncludeAttributes = new XIncludeAttributes(attributes);
        final AttributesImpl attributesImpl = new AttributesImpl(attributes);

        if (level==1)
        {
            if (context.isBaseFixup() && context.getBaseURI() != null)
            {
                final int baseAttIdx = attributesImpl.getIndex(NamespaceSupport.XMLNS, XIncProcConfiguration.XMLBASE_QNAME.getLocalPart());
                if (baseAttIdx < 0)
                {
                    attributesImpl.addAttribute(NamespaceSupport.XMLNS, XIncProcConfiguration.XMLBASE_QNAME.getLocalPart(),
                            "xml:base", "CDATA", XIncProcUtils.computeBase(context.getPaths()).toASCIIString());
                }
            }
            if (!context.isLanguageFixup())
            {
                final int langAtt = attributesImpl.getIndex(NamespaceSupport.XMLNS, XIncProcConfiguration.XMLLANG_QNAME.getLocalPart());
                if (langAtt >= 0)
                {
                    attributesImpl.removeAttribute(langAtt);
                }
            }
        }
        final QName elementQName = new QName(uri, localName);
        if (XIncProcUtils.isXInclude(elementQName) && !context.isProceedPassTwo())
        {
            context.setInInclude(true);
            if (xIncludeAttributes.isBasePresent())
            {
                context.addPath(xIncludeAttributes.getBase());
            }
            if (xIncludeAttributes.isHrefPresent())
            {
                context.addPath(xIncludeAttributes.getHref(), xIncludeAttributes.getXPointer());
            }
            else
            {
                if (context.isPassOne())
                {
                    context.setNeedSecondPass(true);
                    super.startPrefixMapping(qName.substring(0,qName.indexOf(":")), elementQName.getNamespaceURI());
                    super.startElement(uri, localName, qName, attributesImpl);
                    return;
                }
                else
                {
                    context.addPath(context.getSourceURI());
                }
            }
            try
            {
                if (xIncludeAttributes.isXmlParse())
                {
                    includeXmlContent(xIncludeAttributes);
                }
                else
                {
                    includeTextContent(xIncludeAttributes);
                }
            }
            catch(final XIncludeResourceException e)
            {
                context.setNeedFallback(true);
                context.setCurrentException(e);
            }
        }
        else if (XIncProcUtils.isFallback(elementQName))
        {
            if (!context.isInInclude())
            {
                throw new XIncludeFatalException("Fallback not in xinclude element container");
            }
            if (context.isProceedFallback())
            {
                throw new XIncludeFatalException("Only one fallback element allowed in xinclude");
            }
            if (context.isNeedFallback())
            {
                context.setInInclude(false);
            }
            context.setInFallback(true);
        }
        else if (!context.isInFallback() && context.isNeedFallback())
        {
            throw new XIncludeFatalException("No Fallback element");
        }
        else if (context.isUsable())
        {
            int langAttIdx = attributesImpl.getIndex(NamespaceSupport.XMLNS, XIncProcConfiguration.XMLLANG_QNAME.getLocalPart());
            if (langAttIdx >= 0)
            {
                currentLangStack.push(attributesImpl.getValue(langAttIdx));
            }
            else
            {
                currentLangStack.push(null);
            }
            try
            {
                super.startElement(uri, localName, qName, attributesImpl);
            }
            catch (final SAXException e)
            {
                throw new XIncludeFatalException(e);
            }
        }
    }

    private void includeTextContent(final XIncludeAttributes xIncludeAttributes)
            throws XIncludeFatalException, XIncludeResourceException
    {
        final URI sourceURI = XIncProcUtils.resolveBase(context.getSourceURI(), context.getPaths());
        final String content = XIncProcUtils.readTextURI(sourceURI, xIncludeAttributes.getEncoding());
        try
        {
            super.characters(content.toCharArray(), 0, content.length());
        }
        catch (final SAXException e)
        {
            throw new XIncludeFatalException(e);
        }
    }

    private void includeXmlContent(final XIncludeAttributes xIncludeAttributes)
            throws XIncludeFatalException, XIncludeResourceException
    {
        try
        {
            if (currentLangStack.empty())
            {
                context.setLanguage(null);
            }
            else
            {
                context.setLanguage(currentLangStack.peek());
            }
            final URI sourceURI;
            if (xIncludeAttributes.isBasePresent())
            {
                sourceURI = context.getSourceURI().resolve(xIncludeAttributes.getBase().resolve(xIncludeAttributes.getHref()));
            }
            else if (xIncludeAttributes.isHrefPresent())
            {
                sourceURI = context.getSourceURI().resolve(xIncludeAttributes.getHref());
            }
            else
            {
                sourceURI = context.getSourceURI();
            }
            final XIncludeContext newContext = context.clone();
            newContext.setBaseURI(xIncludeAttributes.getHref());
            newContext.setSourceURI(sourceURI);
            newContext.setInInclude(false);
            newContext.setInFallback(false);
            newContext.setInjectingXInclude(false);
            newContext.setProceedFallback(false);
            if (newContext.isPassTwo())
            {
                newContext.setProceedPassTwo(true);
            }
            final XMLFilterImpl filter = new XIncProcXIncludeFilter(newContext);
            final XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            filter.setParent(xmlReader);
            final SAXSource source;
            if (newContext.isPassTwo())
            {
                source = new SAXSource(xmlReader, newContext.getSource());
            }
            else
            {
                source = new SAXSource(xmlReader, new InputSource(new FileReader(sourceURI.getPath())));
            }
            source.setXMLReader(filter);
            xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", filter);
            xmlReader.setDTDHandler(filter);
            xmlReader.setEntityResolver(filter);
            DocumentInfo docInfo  = context.getConfiguration().getProcessor().getUnderlyingConfiguration().buildDocument(source);
            XdmNode node = new XdmNode(docInfo);
            final XMLReader parser = XMLReaderFactory.createXMLReader();
            parser.setContentHandler(this);
           context.setInjectingXInclude(true);
            if (xIncludeAttributes.isXPointerPresent())
            {
                XPointerEngine xPointerEngine = new XPointerEngine(context.getConfiguration().getProcessor());
                xPointerEngine.setLanguage(newContext.getLanguage());
                if (context.getConfiguration().isBaseUrisFixup() && newContext.getBaseURI() != null)
                {
                    xPointerEngine.setBaseURI(newContext.getBaseURI().toASCIIString());
                }
                SAXDestination saxDestination = new SAXDestination(this);
                xPointerEngine.executeToDestination(xIncludeAttributes.getXPointer(), node.asSource(), saxDestination);
            }
            else
            {
                parser.parse(new InputSource(new StringReader(node.toString())));
            }
            context.setInjectingXInclude(false);
        }
        catch (final FileNotFoundException e)
        {
            throw new XIncludeResourceException(e.getMessage());
        }
        catch (final IOException e)
        {
            throw new XIncludeResourceException(e.getMessage());
        }
        catch (final CloneNotSupportedException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
        catch (final XPointerException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
        catch (final SAXException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
        catch (final XPathException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
    }

    @Override
    public InputSource resolveEntity(final String publicId, final String systemId)
            throws SAXException, IOException
    {
        LOG.trace("resolveEntity:{},{}",publicId, systemId);
        return super.resolveEntity(publicId, systemId);
    }

    @Override
    public void setDocumentLocator(final Locator locator)
    {
        LOG.trace("setDocumentLocator");
        super.setDocumentLocator(locator);
    }

    @Override
    public void skippedEntity(final String name)
            throws SAXException
    {
        LOG.trace("skippedEntity:{}",name);
        super.skippedEntity(name);
    }

    @Override
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName)
            throws SAXException
    {
        LOG.trace("unparsedEntityDecl:{},{},{},{}",name,publicId, systemId, notationName);
        super.unparsedEntityDecl(name, publicId, systemId, notationName);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException
    {
        LOG.trace("endElement@{}:{},{},{}", Integer.toHexString(hashCode()), uri, localName, qName);
        -- level;
        final QName elementQName = new QName(uri, localName);
        if (XIncProcUtils.isFallback(elementQName))
        {
            context.setNeedFallback(false);
            context.setProceedFallback(true);
            context.setInFallback(false);
        }
        else if (XIncProcUtils.isXInclude(elementQName) && !context.isNeedSecondPass())
        {
            if (context.isNeedFallback())
            {
                throw new XIncludeFatalException(context.getCurrentException());
            }
            context.setNeedFallback(false);
            context.setProceedFallback(false);
            context.setInInclude(false);
            context.removePath();
        }
        else if (context.isUsable())
        {
            if (!currentLangStack.empty())
            {
                currentLangStack.pop();
            }
            super.endElement(uri, localName, qName);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException
    {
        if (context.isUsable())
        {
            LOG.trace("characters@{}: {}", Integer.toHexString(hashCode()), new String(ch).substring(start,start+length).trim());
            super.characters(ch, start, length);
        }
    }

    @Override
    public void setFeature(String name, boolean value)
            throws SAXNotRecognizedException, SAXNotSupportedException
    {
        if (name.equalsIgnoreCase(FIXUP_XML_LANG))
        {
            context.getConfiguration().setLanguageFixup(value);
        }
        else if (name.equalsIgnoreCase(FIXUP_XML_BASE))
        {
            context.getConfiguration().setBaseUrisFixup(value);
        }
        else
        {
            super.setFeature(name, value);
        }
    }

    @Override
    public void startPrefixMapping(final String prefix, final String uri)
            throws SAXException
    {
        if (!XIncProcConfiguration.XINCLUDE_NAMESPACE_URI.equals(uri))
        {
            super.startPrefixMapping(prefix, uri);
        }
        namespaces.put(prefix, uri);
    }

    @Override
    public void endPrefixMapping(final String prefix) throws
            SAXException
    {
        super.endPrefixMapping(prefix);
        namespaces.remove(prefix);
    }

    @Override
    public void processingInstruction(final String target, final String data)
            throws SAXException
    {
        if (context.isUsable())
        {
            super.processingInstruction(target, data);
        }
    }

    @Override
    public void startDTD(final String name, final String publicId, final String systemId)
            throws SAXException
    {
        LOG.trace("startDTD:{},{},{}", name,publicId,systemId);
    }

    @Override
    public void endDTD() throws SAXException {
        LOG.trace("endDTD");
    }

    @Override
    public void startEntity(final String name)
            throws SAXException
    {
        LOG.trace("startEntity:{}", name);
    }

    @Override
    public void endEntity(final String name)
            throws SAXException
    {
        LOG.trace("startDTD:{}", name);
    }

    @Override
    public void startCDATA()
            throws SAXException
    {
        LOG.trace("startCDATA");
    }

    @Override
    public void endCDATA()
            throws SAXException
    {
        LOG.trace("endCDATA");
    }

    @Override
    public void comment(final char[] ch, final int start, final int length)
            throws SAXException
    {
        LOG.trace("comment: {}", new String(ch).substring(start, start + length));
    }

    @Override
    public void notationDecl(final String name, final String publicId, final String systemId)
            throws SAXException
    {
        LOG.trace("notationDecl:{},{},{}", name,publicId,systemId);
        super.notationDecl(name, publicId, systemId);
    }


}
