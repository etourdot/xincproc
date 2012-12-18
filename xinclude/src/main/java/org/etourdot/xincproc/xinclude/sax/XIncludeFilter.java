/*
 * Copyright (C) 2011 Emmanuel Tourdot
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA 
 *
 * $Id$
 */
package org.etourdot.xincproc.xinclude.sax;

import com.google.common.base.Strings;
import net.sf.saxon.om.DocumentInfo;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.trans.XPathException;
import org.etourdot.xincproc.xinclude.XIncProcConfiguration;
import org.etourdot.xincproc.xinclude.XIncProcEngine;
import org.etourdot.xincproc.xinclude.XIncProcUtils;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeDocumentNotFoundException;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeResourceException;
import org.etourdot.xincproc.xpointer.XPointerEngine;
import org.etourdot.xincproc.xpointer.XPointerHelper;
import org.etourdot.xincproc.xpointer.exceptions.XPointerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.NamespaceSupport;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.namespace.QName;
import javax.xml.transform.sax.SAXSource;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Emmanuel Tourdot
 */
public class XIncludeFilter extends XMLFilterImpl
{
    private static final Logger LOG = LoggerFactory.getLogger(XIncludeFilter.class);
    private final XIncProcConfiguration configuration;

    private final URI baseURI;
    private final Stack<URI> baseStack = new Stack<URI>();
    private int level = 0;
    private int baseLevel = 0;
    private boolean needFallback = false;
    private boolean inFallBack = false;
    private boolean include = false;
    private final Map<String, String> namespaces = new HashMap<String, String>();

    public XIncludeFilter(final URI baseURI, final XIncProcConfiguration configuration)
    {
        this.baseURI = baseURI;
        this.level = 0;
        this.baseLevel = 0;
        this.needFallback = false;
        this.inFallBack = false;
        this.include = false;
        this.configuration = configuration;
    }

    @Override
    public void startDocument() throws SAXException
    {
        if (0 == this.baseLevel)
        {
            super.startDocument();
        }
        this.baseLevel ++;
    }

    @Override
    public void endDocument() throws SAXException
    {
        this.baseLevel --;
        if (0 == this.baseLevel)
        {
            super.endDocument();
        }
    }

    @Override
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException
    {
        if (!XIncProcConfiguration.XINCLUDE_NAMESPACE_URI.equals(uri))
        {
            super.startPrefixMapping(prefix, uri);
        }
        namespaces.put(prefix, uri);
    }

    @Override
    public void endPrefixMapping(final String prefix) throws SAXException
    {
        super.endPrefixMapping(prefix);
        namespaces.remove(prefix);
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName,
                             final Attributes attributes) throws SAXException
    {
        final QName qnameElt = new QName(uri, localName);
        if (XPointerHelper.isTopElement(qnameElt))
        {

        }
        else if (!XPointerHelper.isRootElement(qnameElt))
        {
            final String baseAtt = attributes.getValue(NamespaceSupport.XMLNS, XIncProcConfiguration.XMLBASE_QNAME.getLocalPart());
            final AttributesImpl attributesImpl = new AttributesImpl(attributes);
            if (null == baseAtt && configuration.isBaseUrisFixup() && !XIncProcUtils.isXInclude(qnameElt) &&
                    0 < baseStack.size() && level == baseLevel && !needFallback)
            {
                final URI base = baseStack.peek();
                attributesImpl.addAttribute(NamespaceSupport.XMLNS, XIncProcConfiguration.XMLBASE_QNAME.getLocalPart(),
                        "xml:base", "CDATA", base.toASCIIString());
            }
            this.level ++;
            if (XIncProcUtils.isXInclude(qnameElt))
            {
                include = true;
                try
                {
                    XIncProcUtils.checkXInclude(attributes);
                    final String parseAtt = attributes.getValue("", XIncProcConfiguration.ATT_PARSE.getLocalPart());
                    final String parse = (null == parseAtt) ? XIncProcConfiguration.XML : parseAtt;
                    if (XIncProcConfiguration.XML.equals(parse))
                    {
                        includeXmlContent(attributes);
                    }
                    else
                    {
                        includeTextContent(attributes);
                    }
                }
                catch (final XIncludeResourceException ignored)
                {
                    needFallback = true;
                }
                catch (XIncludeFatalException e)
                {
                    LOG.error(e.getMessage(), e);
                    throw new SAXException(e);
                }
                catch (XIncludeDocumentNotFoundException ignored)
                {
                    needFallback = true;
                }
                return;
            }
            else if (XIncProcUtils.isFallback(qnameElt))
            {
                if (!include)
                {
                    throw new SAXException("Fallback not in xinclude element container");
                }
                if (needFallback)
                {
                    include = false;
                }
                inFallBack = true;
                return;
            }
            else if (!inFallBack && needFallback)
            {
                throw new SAXException("No Fallback element");
            }
            super.startElement(uri, localName, qName, attributesImpl);
        }
    }

    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException
    {
        final QName qnameElt = new QName(uri, localName);
        if (XPointerHelper.isTopElement(qnameElt))
        {

        }
        else if (!XPointerHelper.isRootElement(qnameElt))
        {
            this.level --;
            if (XIncProcUtils.isXInclude(qnameElt))
            {

                if (needFallback)
                {
                    throw new SAXException("No Fallback element");
                }
                baseStack.pop();
                needFallback = false;
                include = false;
            }
            else if (XIncProcUtils.isFallback(qnameElt))
            {
                needFallback = false;
                inFallBack = false;
            }
            else
            {
                super.endElement(uri, localName, qName);
            }
        }
    }

    private void includeXmlContent(final Attributes attributes)
            throws XIncludeFatalException, XIncludeResourceException, XIncludeDocumentNotFoundException
    {
        try {
            final String href = attributes.getValue("", XIncProcConfiguration.ATT_HREF.getLocalPart());
            final String xpointer = attributes.getValue("", XIncProcConfiguration.ATT_XPOINTER.getLocalPart());
            final String baseAtt = attributes.getValue(NamespaceSupport.XMLNS, XIncProcConfiguration.XMLBASE_QNAME.getLocalPart());

            if (null != baseAtt) {
                baseStack.push(new URI(baseAtt));
            }
            baseStack.push(new URI(href));
            final URI source = XIncProcUtils.resolveBase(baseURI, baseStack);
            if (Strings.isNullOrEmpty(xpointer))
            {
                final XMLReader xmlReader = XMLReaderFactory.createXMLReader();
                xmlReader.setContentHandler(this);
                xmlReader.setEntityResolver(new XIncludeFilter.SimpleEntityResolver());
                xmlReader.parse(source.toASCIIString());
            }
            else
            {
                final XMLReader xmlReader = XMLReaderFactory.createXMLReader();
                final XMLFilter filter = XIncProcEngine.newXIncludeFilter(source);
                filter.setParent(xmlReader);
                final SAXSource saxSource = new SAXSource(xmlReader, new InputSource(new FileReader(source.getPath())));
                saxSource.setXMLReader(filter);
                final DocumentInfo doc = configuration.getProcessor().getUnderlyingConfiguration().buildDocument(saxSource);
//                for (final Entry<String, String> namespace : namespaces.entrySet())
//                {
//                    xPointerContext.addPrefix(namespace.getKey(), namespace.getValue());
//                }
//                final XPointerHelper pointerHelper = configuration.getXPointerEngine().parse(xPointerContext);
                final XMLReader parser = XMLReaderFactory.createXMLReader();
                parser.setContentHandler(this);
                parser.setEntityResolver(new XIncludeFilter.SimpleEntityResolver());
//                final StringWriter writer = new StringWriter();
                XPointerEngine xPointerEngine = new XPointerEngine(configuration.getProcessor());
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                Serializer serializer = configuration.getProcessor().newSerializer(byteArrayOutputStream);
                xPointerEngine.executeToDestination(xpointer, doc, serializer);
                serializer.close();
//                pointerHelper.parse(new StringReader(new XdmNode(doc).toString()), writer);
//                parser.parse(new InputSource(new StringReader(writer.toString())));
                parser.parse(new InputSource(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())));
            }
        }
        catch (FileNotFoundException e)
        {
            throw new XIncludeDocumentNotFoundException("Document not found");
        }
        catch (final IOException ignored)
        {
            throw new XIncludeDocumentNotFoundException("Document not found");
        }
        catch (SAXException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
        catch (URISyntaxException e)
        {
            throw new XIncludeResourceException(e.getMessage());
        }
        catch (XPathException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
        catch (SaxonApiException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
        catch (XPointerException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
    }

    private void includeTextContent(final Attributes attributes)
            throws XIncludeFatalException, XIncludeResourceException
    {
        try
        {
            final String href = attributes.getValue("", XIncProcConfiguration.ATT_HREF.getLocalPart());
            final String encoding = attributes.getValue("", XIncProcConfiguration.ATT_ENCODING.getLocalPart());
            final String baseAtt = attributes.getValue(NamespaceSupport.XMLNS, XIncProcConfiguration.XMLBASE_QNAME.getLocalPart());

            if (null != baseAtt) {
                baseStack.push(new URI(baseAtt));
            }
            baseStack.push(new URI(href));
            final URI source = XIncProcUtils.resolveBase(baseURI, baseStack);
            final String content = XIncProcUtils.readTextURI(source, encoding);
            super.characters(content.toCharArray(), 0, content.length());
        }
        catch (URISyntaxException e)
        {
            throw new XIncludeResourceException(e.getMessage());
        }
        catch (SAXException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
    }

    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException
    {
        super.characters(ch, start, length);
    }

    private static class SimpleEntityResolver implements EntityResolver
    {
        @Override
        public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException, IOException
        {
            return new InputSource(new ByteArrayInputStream("".getBytes()));
        }
    }
}
