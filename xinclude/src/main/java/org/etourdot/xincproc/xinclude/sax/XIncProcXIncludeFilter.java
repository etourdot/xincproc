package org.etourdot.xincproc.xinclude.sax;

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
    private final Stack<String> currentLangStack;
    private boolean needEndXinclude;
    private boolean inDTD;
    LexicalHandler	lexicalHandler;
    private static final String LEXICALID = "http://xml.org/sax/properties/lexical-handler";

    private boolean alreadyProceedFallback;

    private int elementLevel;
    private int fallbackLevel;
    private int xIncludeLevel;
    private int needFallbackLevel;
    private int injectingXIncludeLevel;


    public XIncProcXIncludeFilter(XIncludeContext context)
    {
        this.context = context;
        this.currentLangStack = new Stack<String>();
        currentLangStack.push(context.getLanguage());
        injectingXIncludeLevel = 0;
        xIncludeLevel = 0;
        fallbackLevel = 0;
    }

    public XIncludeContext getContext()
    {
        return context;
    }

    @Override
    public void setProperty (String uri, Object value)
            throws SAXNotRecognizedException, SAXNotSupportedException
    {
        if (LEXICALID.equals(uri))
        {
            lexicalHandler = (LexicalHandler) value;
        }
        else
        {
            super.setProperty(uri, value);
        }
    }

    @Override
    public void parse (InputSource in)
            throws SAXException, IOException
    {
        XMLReader parent = getParent();

        if (parent != null)
        {
            parent.setProperty(LEXICALID, this);
        }
        super.parse (in);
    }

    @Override
    public void startDocument() throws SAXException
    {
        elementLevel = 0;
        if (!injectingXInclude())
        {
            LOG.trace("startDocument@{}", Integer.toHexString(hashCode()));
            super.startDocument();
        }
    }

    @Override
    public void endDocument() throws SAXException
    {
        if (!injectingXInclude())
        {
            LOG.trace("endDocument@{}", Integer.toHexString(hashCode()));
            super.endDocument();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException
    {
        LOG.trace("startElement@{}: {}, {}, {}", Integer.toHexString(hashCode()), uri, localName, qName);
        final AttributesImpl attributesImpl = new AttributesImpl(attributes);
        context.updateContextWithElementAttributes(attributesImpl);
        final int langAttIdx = attributesImpl.getIndex(NamespaceSupport.XMLNS,
                XIncProcConfiguration.XMLLANG_QNAME.getLocalPart());
        final QName elementQName = new QName(uri, localName);

        if (isTopElement())
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
        startElement();
        if (XIncProcUtils.isXInclude(elementQName))
        {
            if (inXIncludeElement() && !inFallbackElement() && !injectingXInclude())
            {
                throw new XIncludeFatalException("XInclude element is not allowed into xinclude");
            }
            XIncludeAttributes xIncludeAttributes = new XIncludeAttributes(attributes);
            startXIncludeElement();
            URI sourceURI = XIncProcUtils.resolveBase(context.getInitialBaseURI(), context.getBaseURIPaths());
            if (xIncludeAttributes.isHrefPresent())
            {
                sourceURI = sourceURI.resolve(xIncludeAttributes.getHref());
            }
            /*else
            {
                sourceURI = sourceURI.resolve(context.getInitialBaseURI());
            }*/
            context.setSourceURI(sourceURI);
            if (xIncludeAttributes.isHrefPresent())
            {
                context.addInInclusionChain(context.getSourceURI(), xIncludeAttributes.getXPointer());
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
            catch (final XIncludeResourceException e)
            {
                startNeedFallback();
                context.setCurrentException(e);
            }
        }
        else if (XIncProcUtils.isFallback(elementQName))
        {
            startFalbackElement();
            if (!inXIncludeElement() || (isNeedFallback() && fallbackLevel > xIncludeLevel))
            {
                throw new XIncludeFatalException("Fallback not in xinclude element container");
            }
            if (alreadyProceedFallback)
            {
                throw new XIncludeFatalException("Only one fallback element allowed in xinclude");
            }
        }
        else if (!inFallbackElement() && isNeedFallback())
        {
            throw new XIncludeFatalException("No Fallback element");
        }
        else if (isUsable())
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
        else if (XIncProcUtils.isXIncludeNamespace(elementQName))
        {
            throw new XIncludeFatalException("Any element of XInclude namespace allowed into xinclude");
        }
    }

    private void includeTextContent(final XIncludeAttributes xIncludeAttributes)
            throws XIncludeFatalException, XIncludeResourceException
    {
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
            final SAXSource source;
            if (xIncludeAttributes.isHrefPresent())
            {
                final URI sourceURI = context.getSourceURI();
                final XIncludeContext newContext = XIncludeContext.newContext(context);
                newContext.setHrefURI(xIncludeAttributes.getHref());
                newContext.setInitialBaseURI(sourceURI);
                final XMLFilter filter = XIncProcEngine.newXIncludeFilter(newContext);
                final XMLReader xmlReader = XMLReaderFactory.createXMLReader();
                xmlReader.setProperty(LEXICALID, filter);
                filter.setParent(xmlReader);
                source = new SAXSource(filter, new InputSource(new FileReader(sourceURI.getPath())));
                if (!xIncludeAttributes.isXPointerPresent())
                {
                    source.setSystemId(sourceURI.toASCIIString());
                }
            }
            else
            {
                source = new SAXSource(new InputSource(new FileReader(context.getInitialBaseURI().getPath())));
            }
            final DocumentInfo docInfo  = context.getConfiguration().getProcessor().getUnderlyingConfiguration().buildDocument(source);
            final XdmNode node = new XdmNode(docInfo);

            final XMLReader parser = XMLReaderFactory.createXMLReader();
            parser.setContentHandler(this);
            parser.setProperty(LEXICALID, this);
            startInjectingXInclude();
            if (xIncludeAttributes.isXPointerPresent())
            {
                XPointerEngine xPointerEngine = new XPointerEngine(context.getConfiguration().getProcessor());
                xPointerEngine.setLanguage(context.getLanguage());
                if (context.getConfiguration().isBaseUrisFixup() && context.getHrefURI() != null)
                {
                    xPointerEngine.setBaseURI(context.getHrefURI().toASCIIString());
                }
                SAXDestination saxDestination = new SAXDestination(this);
                LOG.trace("includeXmlContent start injecting xpointer");
                final int includeLevel = elementLevel;
                final int nbElements = xPointerEngine.executeToDestination(xIncludeAttributes.getXPointer(), node.asSource(), saxDestination);
                if (includeLevel == 1 && nbElements != 1)
                {
                    throw new XIncludeFatalException("Attempt to replace top level include with something other than one element.");
                }
                LOG.trace("includeXmlContent end injecting xpointer");
            }
            else
            {
                LOG.trace("includeXmlContent start injecting");
                parser.parse(new InputSource(new StringReader(node.toString())));
                LOG.trace("includeXmlContent end injecting");
            }
        }
        catch (final FileNotFoundException e)
        {
            throw new XIncludeResourceException(e.getMessage());
        }
        catch (final XPointerResourceException e)
        {
            throw new XIncludeResourceException(e.getMessage());
        }
        catch (final XPointerException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
        catch (final IOException e)
        {
            throw new XIncludeResourceException(e.getMessage());
        }
        catch (final XIncludeFatalException e)
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
        finally
        {
            if (injectingXInclude())
            {
                stopInjectingXInclude();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException
    {
        LOG.trace("endElement@{}:{},{},{}", Integer.toHexString(hashCode()), uri, localName, qName);
        context.updateContextWhenEndElement();
        final QName elementQName = new QName(uri, localName);
        if (XIncProcUtils.isFallback(elementQName))
        {
            if (isNeedFallback())
            {
                endNeedFallback();
            }
            endFallbackElement();
        }
        else if (XIncProcUtils.isXInclude(elementQName))
        {
            if (isNeedFallback())
            {
                endNeedFallback();
                throw new XIncludeFatalException(context.getCurrentException());
            }
            context.removeFromInclusionChain();
            if (needEndXinclude)
            {
                super.endElement(uri, localName, qName);
            }
            endXIncludeElement();
            needEndXinclude = false;
        }
        else if (isUsable())
        {
            if (!currentLangStack.empty())
            {
                currentLangStack.pop();
            }
            super.endElement(uri, localName, qName);
        }
        endElement();
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException
    {
        if (isUsable())
        {
            LOG.trace("characters@{}: {}", Integer.toHexString(hashCode()), new String(ch).substring(start,start+length).trim());
            super.characters(ch, start, length);
        }
    }

    @Override
    public void comment(final char[] ch, final int start, final int length)
            throws SAXException
    {
        LOG.trace("comment: {}", new String(ch).substring(start, start + length));
        if (lexicalHandler != null && !inDTD && !(inXIncludeElement() && !injectingXInclude() && !inFallbackElement()))
        {
            lexicalHandler.comment(ch, start, length);
        }
    }

    @Override
    public InputSource resolveEntity(final String publicId, final String systemId)
            throws SAXException, IOException
    {
        try {
            LOG.trace("resolveEntity:{},{},{}", publicId, systemId, XIncProcUtils.resolveBase(new URI(systemId), context.getBaseURIPaths()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
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
        if (isUsable())
        {
            super.processingInstruction(target, data);
        }
    }

    @Override
    public void startDTD(final String name, final String publicId, final String systemId)
            throws SAXException
    {
        LOG.trace("startDTD:{},{},{}", name,publicId,systemId);
        inDTD = true;
        if (lexicalHandler != null)
        {
            lexicalHandler.startDTD(name, publicId, systemId);
        }
    }

    @Override
    public void endDTD() throws SAXException {
        LOG.trace("endDTD");
        inDTD = false;
        if (lexicalHandler != null)
        {
            lexicalHandler.endDTD();
        }
    }

    @Override
    public void startEntity(final String name)
            throws SAXException
    {
        LOG.trace("startEntity:{}", name);
        if (lexicalHandler != null)
        {
            lexicalHandler.startEntity(name);
        }
    }

    @Override
    public void endEntity(final String name)
            throws SAXException
    {
        LOG.trace("endEntity:{}", name);
        if (lexicalHandler != null)
        {
            lexicalHandler.endEntity(name);
        }
    }

    @Override
    public void startCDATA()
            throws SAXException
    {
        LOG.trace("startCDATA");
        if (lexicalHandler != null)
        {
            lexicalHandler.startCDATA();
        }
    }

    @Override
    public void endCDATA()
            throws SAXException
    {
        LOG.trace("endCDATA");
        if (lexicalHandler != null)
        {
            lexicalHandler.endCDATA();
        }
    }

    @Override
    public void notationDecl(final String name, final String publicId, final String systemId)
            throws SAXException
    {
        LOG.trace("notationDecl:{},{},{}", name,publicId,systemId);
        super.notationDecl(name, publicId, systemId);
    }

    public boolean inFallbackElement()
    {
        return fallbackLevel > 0;
    }

    public void startElement()
    {
        elementLevel++;
    }

    public void endElement()
    {
        elementLevel--;
    }

    public boolean isTopElement()
    {
        return elementLevel == 0;
    }

    public void startFalbackElement()
    {
        fallbackLevel++;
    }

    public void endFallbackElement()
    {
        fallbackLevel--;
        alreadyProceedFallback = true;
    }

     public boolean inXIncludeElement()
    {
        return xIncludeLevel > 0;
    }

    public void startXIncludeElement()
    {
        xIncludeLevel++;
    }

    public void endXIncludeElement()
    {
        xIncludeLevel--;
        alreadyProceedFallback = false;
    }

    public boolean injectingXInclude()
    {
        return injectingXIncludeLevel > 0;
    }

    public void startInjectingXInclude()
    {
        injectingXIncludeLevel++;
    }

    public void stopInjectingXInclude()
    {
        injectingXIncludeLevel--;
    }

    public void startNeedFallback()
    {
        needFallbackLevel++;
    }

    public void endNeedFallback()
    {
        needFallbackLevel--;
    }

    public boolean isNeedFallback()
    {
        return xIncludeLevel > 0 && needFallbackLevel == xIncludeLevel;
    }

    boolean isUsable()
    {
        return (isNeedFallback() && inFallbackElement()) ||
               (!inFallbackElement() && !inXIncludeElement()) ||
               (inXIncludeElement() && injectingXInclude());
    }
}
