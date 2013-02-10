package org.etourdot.xincproc.xinclude.sax;

import net.sf.saxon.event.ReceivingContentHandler;
import net.sf.saxon.om.DocumentInfo;
import net.sf.saxon.s9api.SAXDestination;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.trans.XPathException;
import org.etourdot.xincproc.xinclude.XIncProcConfiguration;
import org.etourdot.xincproc.xinclude.XIncProcEngine;
import org.etourdot.xincproc.xinclude.XIncProcUtils;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeResourceException;
import org.etourdot.xincproc.xpointer.XPointerEngine;
import org.etourdot.xincproc.xpointer.exceptions.XPointerException;
import org.etourdot.xincproc.xpointer.exceptions.XPointerResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.*;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ext.Locator2Impl;
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
import java.net.URISyntaxException;
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
    private boolean needEndXinclude;

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
        if (!context.isInjectingXInclude())
        {
            LOG.trace("endDocument@{}:{}", Integer.toHexString(hashCode()), context.isNeedTreatIncludeWithoutHref());
            super.endDocument();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException
    {
        LOG.trace("startElement@{}: {}, {}, {}", Integer.toHexString(hashCode()), uri, localName, qName);
        level ++;
        final AttributesImpl attributesImpl = new AttributesImpl(attributes);
        context.updateContextWithElementAttributes(attributesImpl);
        LOG.debug("Start {}, context={}", localName, context);
        final int langAttIdx = attributesImpl.getIndex(NamespaceSupport.XMLNS,
                XIncProcConfiguration.XMLLANG_QNAME.getLocalPart());
        final QName elementQName = new QName(uri, localName);

         if (level==1)
        {
            if (context.isBaseFixup() && context.getHrefURI() != null && context.getCurrentBaseURI() == null)
            {
                URI newBaseURI = context.getHrefURI();
                if (context.getStackedBaseURI() != null)
                {
                    newBaseURI = context.getStackedBaseURI().resolve(newBaseURI);
                }
                attributesImpl.addAttribute(NamespaceSupport.XMLNS, XIncProcConfiguration.XMLBASE_QNAME.getLocalPart(),
                        "xml:base", "CDATA", newBaseURI.toASCIIString());
            }
            if (!context.isLanguageFixup() && langAttIdx >= 0)
            {
                attributesImpl.removeAttribute(langAttIdx);
            }
        }
        if (XIncProcUtils.isXInclude(elementQName))
        {
            XIncludeAttributes xIncludeAttributes = new XIncludeAttributes(attributes);
            context.setInInclude(true);
            URI sourceURI = XIncProcUtils.resolveBase(context.getInitialBaseURI(), context.getBaseURIPaths());
            if (xIncludeAttributes.isHrefPresent())
            {
                sourceURI = sourceURI.resolve(xIncludeAttributes.getHref());
            }
            context.setSourceURI(sourceURI);
            if (xIncludeAttributes.isHrefPresent() || context.isTreatIncludeWithoutHref())
            {
                context.addInInclusionChain(context.getSourceURI(), xIncludeAttributes.getXPointer());
            }
            else if (context.isTreatAllIncludes())
            {
                context.setNeedTreatIncludeWithoutHref(true);
                super.startPrefixMapping(qName.substring(0,qName.indexOf(":")), elementQName.getNamespaceURI());
                super.startElement(uri, localName, qName, attributesImpl);
                needEndXinclude = true;
                return;
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
        //final URI sourceURI = XIncProcUtils.resolveBase(context.getSourceURI(), context.getBaseURIPaths());
        String content = XIncProcUtils.readTextURI(context.getSourceURI(), xIncludeAttributes.getEncoding(),
                xIncludeAttributes.getAccept(), xIncludeAttributes.getAcceptLanguage());
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
            final URI sourceURI = context.getSourceURI();
            /*if (xIncludeAttributes.isBasePresent())
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
            }*/
            final XIncludeContext newContext = context.clone();
            newContext.setHrefURI(xIncludeAttributes.getHref());
            newContext.setInitialBaseURI(sourceURI);
            newContext.setInInclude(false);
            newContext.setInFallback(false);
            newContext.noInjectingXInclude();
            newContext.setProceedFallback(false);
            final XMLFilter filter = XIncProcEngine.newXIncludeFilter(newContext);
            final XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            filter.setParent(xmlReader);
            final SAXSource source;
            XdmNode node;
            if (newContext.isTreatIncludeWithoutHref())
            {
                node = newContext.getSourceNode();
            }
            else
            {
                xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", filter);
                source = new SAXSource(filter, new InputSource(new FileReader(sourceURI.getPath())));
                //source.setXMLReader(filter);
                if (!xIncludeAttributes.isXPointerPresent())
                {
                    source.setSystemId(sourceURI.toASCIIString());
                }
                DocumentInfo docInfo  = context.getConfiguration().getProcessor().getUnderlyingConfiguration().buildDocument(source);
                node = new XdmNode(docInfo);
            }
            final XMLReader parser = XMLReaderFactory.createXMLReader();
            parser.setContentHandler(this);
            parser.setProperty("http://xml.org/sax/properties/lexical-handler", this);
            context.startInjectingXInclude();
            if (xIncludeAttributes.isXPointerPresent())
            {
                XPointerEngine xPointerEngine = new XPointerEngine(context.getConfiguration().getProcessor());
                xPointerEngine.setLanguage(newContext.getLanguage());
                if (context.getConfiguration().isBaseUrisFixup() && newContext.getHrefURI() != null)
                {
                    xPointerEngine.setBaseURI(newContext.getHrefURI().toASCIIString());
                }
                SAXDestination saxDestination = new SAXDestination(this);
                LOG.trace("includeXmlContent start injecting xpointer");
                xPointerEngine.executeToDestination(xIncludeAttributes.getXPointer(), node.asSource(), saxDestination);
                LOG.trace("includeXmlContent end injecting xpointer");
            }
            else
            {
                LOG.trace("includeXmlContent start injecting");
                parser.parse(new InputSource(new StringReader(node.toString())));
                LOG.trace("includeXmlContent end injecting");
            }
            context.stopInjectingXInclude();
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
        catch (final XPointerResourceException e)
        {
            throw new XIncludeResourceException(e.getMessage());
        }
        catch (final XIncludeFatalException e)
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
        try {
            LOG.trace("resolveEntity:{},{},{}", publicId, systemId, XIncProcUtils.resolveBase(new URI(systemId), context.getBaseURIPaths()));
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return super.resolveEntity(publicId, systemId);
    }

    @Override
    public void setDocumentLocator(final Locator locator)
    {
        LOG.trace("setDocumentLocator");
        super.setDocumentLocator(new Locator2Impl(locator));
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
        context.updateContextWhenEndElement();
        final QName elementQName = new QName(uri, localName);
        if (XIncProcUtils.isFallback(elementQName))
        {
            context.setNeedFallback(false);
            context.setProceedFallback(true);
            context.setInFallback(false);
        }
        else if (XIncProcUtils.isXInclude(elementQName))
        {
            if (!context.isNeedTreatIncludeWithoutHref())
            {
                if (context.isNeedFallback())
                {
                    throw new XIncludeFatalException(context.getCurrentException());
                }
                context.setNeedFallback(false);
                context.setProceedFallback(false);
                context.removeFromInclusionChain();
            }
            if (needEndXinclude)
            {
                super.endElement(uri, localName, qName);
            }
            context.setInInclude(false);
            needEndXinclude = false;
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
        if (getContentHandler() instanceof ReceivingContentHandler)
        {
            ((ReceivingContentHandler) getContentHandler()).comment(ch, start, length);
        }
    }

    @Override
    public void notationDecl(final String name, final String publicId, final String systemId)
            throws SAXException
    {
        LOG.trace("notationDecl:{},{},{}", name,publicId,systemId);
        super.notationDecl(name, publicId, systemId);
    }
}
