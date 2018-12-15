/*
 * This file is part of the XIncProc framework.
 * Copyright (C) 2011 - 2013 Emmanuel Tourdot
 *
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this software.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package org.etourdot.xincproc.xinclude.sax;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import net.sf.saxon.om.DocumentInfo;
import net.sf.saxon.s9api.Destination;
import net.sf.saxon.s9api.SAXDestination;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.trans.XPathException;
import org.apache.commons.lang3.StringUtils;
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
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ext.Locator2Impl;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.NamespaceSupport;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.namespace.QName;
import javax.xml.transform.sax.SAXSource;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Stack;

/**
 * The type X inc proc x include filter.
 */
public class XIncProcXIncludeFilter extends XMLFilterImpl implements DeclHandler, LexicalHandler
{
    private static final Logger LOG = LoggerFactory.getLogger(XIncProcXIncludeFilter.class);
    private static final String FIXUP_XML_LANG = "fixup-xml-lang";
    private static final String FIXUP_XML_BASE = "fixup-xml-base";
    private static final String LEXICALID = "http://xml.org/sax/properties/lexical-handler";
    private static final String DECLID = "http://xml.org/sax/properties/declaration-handler";

    /**
     * Instantiates a new XIncProcXIncludeFilter.
     *
     * @param context the context of filtering.
     */
    public XIncProcXIncludeFilter(final XIncludeContext context)
    {
        this.context = context;
        this.currentLangStack = new Stack<String>();
        this.currentLangStack.push(context.getLanguage());
        this.injectingXIncludeLevel = 0;
        this.xIncludeLevel = 0;
        this.fallbackLevel = 0;
        this.setHasUnparserEntity(false);
    }

    /**
     * Gets Doctype.
     *
     * @return the doctype
     */
    public String getDoctype()
    {
        return this.context.getDocType();
    }

    ///////////////////////
    // Start XMLFilterImpl
    ///////////////////////
    @Override
    public void setProperty(final String name, final Object value)
            throws SAXNotRecognizedException, SAXNotSupportedException
    {
        if (XIncProcXIncludeFilter.LEXICALID.equals(name))
        {
            this.lexicalHandler = Optional.fromNullable((LexicalHandler) value);
        }
        else
        {
            super.setProperty(name, value);
        }
    }

    @Override
    public void parse(final InputSource input)
            throws SAXException, IOException
    {
        final XMLReader parent = getParent();

        if (null != parent)
        {
            parent.setProperty(XIncProcXIncludeFilter.LEXICALID, this);
            parent.setProperty(XIncProcXIncludeFilter.DECLID, this);
        }
        super.parse(input);
    }

    @Override
    public void startDocument() throws SAXException
    {
        this.elementLevel = 0;
        if (!isInjectingXInclude())
        {
            LOG.trace("startDocument@{}", Integer.toHexString(hashCode()));
            super.startDocument();
        }
    }

    @Override
    public void endDocument() throws SAXException
    {
        if (!isInjectingXInclude())
        {
            LOG.trace("endDocument@{}", Integer.toHexString(hashCode()));
            super.endDocument();
        }
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes atts)
            throws SAXException
    {
        LOG.trace("startElement@{}: {}, {}, {}", Integer.toHexString(hashCode()), uri, localName, qName);
        final AttributesImpl attributesImpl = new AttributesImpl(atts);
        this.context.updateContextWithElementAttributes(attributesImpl);
        final QName elementQName = calculateElementName(uri, localName, qName);

        startingElement();
        if (XIncProcUtils.isXInclude(elementQName))
        {
            startXIncludeElement(atts);
        }
        else if (XIncProcUtils.isFallback(elementQName))
        {
            startFallbackElement();
        }
        else if (!isInFallbackElement() && isNeedFallback())
        {
            throw new XIncludeFatalException("No Fallback element");
        }
        else if (isUsable())
        {
            startCommonElement(uri, localName, qName, attributesImpl);
        }
        else if (XIncProcUtils.isXIncludeNamespace(elementQName))
        {
            throw new XIncludeFatalException("Any element of XInclude namespace allowed into xinclude");
        }
    }

    @Override
    public void endElement(final String uri, final String localName, final String qName)
            throws SAXException
    {
        LOG.trace("endElement@{}:{},{},{}", Integer.toHexString(hashCode()), uri, localName, qName);
        this.context.updateContextWhenEndElement();
        final QName elementQName = new QName(uri, localName);
        if (XIncProcUtils.isFallback(elementQName))
        {
            if (isNeedFallback())
            {
                endingNeedFallback();
            }
            endingFallbackElement();
        }
        else if (XIncProcUtils.isXInclude(elementQName))
        {
            if (isNeedFallback())
            {
                endingNeedFallback();
                throw new XIncludeFatalException(this.context.getCurrentException());
            }
            this.context.removeFromInclusionChain();
            endingXIncludeElement();
        }
        else if (isUsable())
        {
            if (!this.currentLangStack.empty())
            {
                this.currentLangStack.pop();
            }
            super.endElement(uri, localName, qName);
        }
        endingElement();
    }

    @Override
    public void characters(final char[] ch, final int start, final int length)
            throws SAXException
    {
        if (isUsable())
        {
            LOG.trace("characters@{}: {}", Integer.toHexString(hashCode()), new String(ch).substring(start, start + length).trim());
            super.characters(ch, start, length);
        }
    }

    @Override
    public InputSource resolveEntity(final String publicId, final String systemId)
            throws SAXException, IOException
    {
        LOG.trace("resolveEntity:{},{},{}", publicId, systemId);
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
        LOG.trace("skippedEntity:{}", name);
        this.setHasUnparserEntity(true);
        super.characters(('&' + name + ';').toCharArray(), 0, name.length() + 2);
    }

    @Override
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId,
                                   final String notationName)
            throws SAXException
    {
        LOG.trace("unparsedEntityDecl:{},{},{},{}", name, publicId, systemId, notationName);
        this.context.addUnparsedEntityDoctype(name, publicId, systemId, notationName);
    }

    @Override
    public void setFeature(final String name, final boolean value)
            throws SAXNotRecognizedException, SAXNotSupportedException
    {
        if (name.equalsIgnoreCase(XIncProcXIncludeFilter.FIXUP_XML_LANG))
        {
            this.context.getConfiguration().setConfigurationProperty(XIncProcConfiguration.ALLOW_FIXUP_LANGUAGE, value);
        }
        else if (name.equalsIgnoreCase(XIncProcXIncludeFilter.FIXUP_XML_BASE))
        {
            this.context.getConfiguration().setConfigurationProperty(XIncProcConfiguration.ALLOW_FIXUP_BASE_URIS, value);
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
        if (!XIncProcUtils.XINCLUDE_NAMESPACE_URI.equals(uri))
        {
            super.startPrefixMapping(prefix, uri);
        }
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
    public void notationDecl(final String name, final String publicId, final String systemId)
            throws SAXException
    {
        LOG.trace("notationDecl:{},{},{}", name, publicId, systemId);
        super.notationDecl(name, publicId, systemId);
    }
    ///////////////////////
    // End XMLFilterImpl
    ///////////////////////

    ///////////////////////////////
    // Start LexicalHandler methods
    ///////////////////////////////
    @Override
    public void startDTD(final String name, final String publicId, final String systemId)
            throws SAXException
    {
        LOG.trace("startDTD:{},{},{}", name, publicId, systemId);
        this.inDTD = true;
        if (this.lexicalHandler.isPresent())
        {
            this.lexicalHandler.get().startDTD(name, publicId, systemId);
        }
        this.context.setDocType(name, publicId, systemId);
    }

    @Override
    public void endDTD() throws SAXException
    {
        LOG.trace("endDTD");
        this.inDTD = false;
        if (this.lexicalHandler.isPresent())
        {
            this.lexicalHandler.get().endDTD();
        }
    }

    @Override
    public void startEntity(final String name)
            throws SAXException
    {
        LOG.trace("startEntity:{}", name);
        if (this.lexicalHandler.isPresent())
        {
            this.lexicalHandler.get().startEntity(name);
        }
    }

    @Override
    public void endEntity(final String name)
            throws SAXException
    {
        LOG.trace("endEntity:{}", name);
        if (this.lexicalHandler.isPresent())
        {
            this.lexicalHandler.get().endEntity(name);
        }
    }

    @Override
    public void startCDATA()
            throws SAXException
    {
        LOG.trace("startCDATA");
        if (this.lexicalHandler.isPresent())
        {
            this.lexicalHandler.get().startCDATA();
        }
    }

    @Override
    public void endCDATA()
            throws SAXException
    {
        LOG.trace("endCDATA");
        if (this.lexicalHandler.isPresent())
        {
            this.lexicalHandler.get().endCDATA();
        }
    }

    @Override
    public void comment(final char[] ch, final int start, final int length)
            throws SAXException
    {
        LOG.trace("comment: {}", new String(ch).substring(start, start + length));
        if (this.lexicalHandler.isPresent() && !this.inDTD && !(isInXIncludeElement() && !isInjectingXInclude() && !isInFallbackElement()))
        {
            this.lexicalHandler.get().comment(ch, start, length);
        }
    }
    /////////////////////////////
    // End LexicalHandler methods
    /////////////////////////////

    /////////////////////////////
    // Start DeclHandler methods
    /////////////////////////////
    @Override
    public void elementDecl(final String name, final String model)
            throws SAXException
    {
        LOG.trace("elementDecl:{},{}", name, model);
        this.context.addElementDoctype(name, model);
    }

    @Override
    public void attributeDecl(final String eName, final String aName, final String type, final String mode, final String value)
            throws SAXException
    {
        LOG.trace("attributeDecl:{},{},{},{},{}", eName, aName, type, mode, value);
        this.context.addAttributeDoctype(eName, aName, type, mode, value);
    }

    @Override
    public void internalEntityDecl(final String name, final String value)
            throws SAXException
    {
        LOG.trace("internalEntityDecl:{},{}", name, value);
        this.context.addInternalEntityDoctype(name, value);
    }

    @Override
    public void externalEntityDecl(final String name, final String publicId, final String systemId)
            throws SAXException
    {
        LOG.trace("externalEntityDecl:{},{},{}", name, publicId, systemId);
        this.context.addExternalEntityDoctype(name, publicId, systemId);
    }
    ////////////////////////////
    // End DeclHandler methods
    ////////////////////////////

    /**
     * Is has unparser entity.
     *
     * @return the boolean
     */
    public boolean isHasUnparserEntity()
    {
        return this.hasUnparserEntity;
    }

    /**
     * Sets has unparser entity.
     *
     * @param hasUnparserEntity the has unparser entity
     */
    public void setHasUnparserEntity(final boolean hasUnparserEntity)
    {
        this.hasUnparserEntity = hasUnparserEntity;
    }

    ////////////////////////////
    // Private utilities methods
    ////////////////////////////
    private void includeXmlContent(final XIncludeAttributes xIncludeAttributes)
            throws XIncludeFatalException, XIncludeResourceException
    {
        try
        {
            settingLanguage();
            final SAXSource source = buildingXIncludeSource(xIncludeAttributes);
            final DocumentInfo docInfo = this.context.getConfiguration().getProcessor().getUnderlyingConfiguration()
                    .buildDocument(source);
            final XdmNode node = new XdmNode(docInfo);
            startingInjectXInclude();
            injectingXInclude(xIncludeAttributes, source, node);
        }
        catch (final XPointerResourceException e)
        {
            throw new XIncludeResourceException(e.getMessage());
        }
        catch (final XPointerException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
        catch (final XPathException e)
        {
            if (e.getCause() instanceof XIncludeFatalException)
            {
                throw (XIncludeFatalException) e.getCause();
            }
            else
            {
                throw new XIncludeFatalException(e.getMessage());
            }
        }
        finally
        {
            if (isInjectingXInclude())
            {
                endingInjectXInclude();
            }
        }
    }

    private SAXSource buildingXIncludeSource(final XIncludeAttributes xIncludeAttributes)
            throws XIncludeFatalException, XIncludeResourceException
    {
        final SAXSource source;
        if (xIncludeAttributes.isHrefPresent())
        {
            final URI sourceURI = this.context.getSourceURI();
            final XIncludeContext newContext = XIncludeContext.newContext(this.context);
            final URI hrefUri = xIncludeAttributes.getHref();
            if (xIncludeAttributes.isBasePresent())
            {
                newContext.setHrefURI(xIncludeAttributes.getBase().resolve(hrefUri));
            }
            else
            {
                newContext.setHrefURI(hrefUri);
            }
            newContext.setInitialBaseURI(sourceURI);
            final XMLFilter filter = XIncProcEngine.newXIncludeFilter(newContext);
            final XMLReader xmlReader;
            try
            {
                xmlReader = XMLReaderFactory.createXMLReader();
                xmlReader.setProperty(XIncProcXIncludeFilter.LEXICALID, filter);
                xmlReader.setProperty(XIncProcXIncludeFilter.DECLID, filter);
                xmlReader.setFeature("http://xml.org/sax/features/resolve-dtd-uris", false);
                xmlReader.setFeature("http://xml.org/sax/features/external-general-entities", false);
            }
            catch (final SAXException e)
            {
                throw new XIncludeFatalException(e.getMessage());
            }
            filter.setParent(xmlReader);
            final InputStream inputStream = readInputStream(sourceURI);
            final InputStreamReader characterStream = new InputStreamReader(inputStream);
            final InputSource inputSource = new InputSource(characterStream);
            source = new SAXSource(filter, inputSource);
            if (!xIncludeAttributes.isXPointerPresent())
            {
                source.setSystemId(sourceURI.toASCIIString());
            }
        }
        else
        {
            final InputStream inputStream = readInputStream(this.context.getInitialBaseURI());
            final InputStreamReader characterStream = new InputStreamReader(inputStream);
            final InputSource inputSource = new InputSource(characterStream);
            source = new SAXSource(inputSource);
        }
        return source;
    }

    private InputStream readInputStream(final URI uri)
            throws XIncludeResourceException
    {
        try
        {
            final String scheme = uri.getScheme();
            if (scheme == null || "file".equals(scheme))
            {
                return new FileInputStream(uri.getPath());
            }
            else
            {
                return uri.toURL().openStream();
            }
        }
        catch (final FileNotFoundException e)
        {
            throw new XIncludeResourceException(e.getMessage());
        }
        catch (final MalformedURLException e)
        {
            throw new XIncludeResourceException(e.getMessage());
        }
        catch (final IOException e)
        {
            throw new XIncludeResourceException(e.getMessage());
        }
    }

    private void settingLanguage()
    {
        if (this.currentLangStack.empty())
        {
            this.context.setLanguage(null);
        }
        else
        {
            this.context.setLanguage(this.currentLangStack.peek());
        }
    }

    private void injectingXInclude(final XIncludeAttributes xIncludeAttributes, final SAXSource source, final XdmNode node)
            throws XIncludeFatalException, XPointerException
    {
        final Destination saxDestination = new SAXDestination(this);
        if (xIncludeAttributes.isXPointerPresent())
        {
            if (source.getXMLReader() instanceof XIncProcXIncludeFilter)
            {
                final XIncProcXIncludeFilter filter = (XIncProcXIncludeFilter) source.getXMLReader();
                if (filter.isHasUnparserEntity())
                {
                    throw new XIncludeFatalException("Resolve an xpointer scheme on a document that contains unexpanded entity reference information items");
                }
            }
            final XPointerEngine xPointerEngine = this.context.getConfiguration().newXPointerEngine();
            xPointerEngine.setLanguage(this.context.getLanguage());
            if (this.context.getConfiguration().isBaseUrisFixup() && (null != this.context.getHrefURI())
                    && xIncludeAttributes.isHrefPresent())
            {
                xPointerEngine.setBaseURI(this.context.getHrefURI().toASCIIString());
            }
            LOG.trace("includeXmlContent start injecting xpointer");
            final int includeLevel = this.elementLevel;
            final int nbElements = xPointerEngine.executeToDestination(xIncludeAttributes.getXPointer(), node.asSource(),
                    saxDestination);
            if ((1 == includeLevel) && (1 != nbElements))
            {
                throw new XIncludeFatalException("Attempt to replace top level include with something other than one element.");
            }
            LOG.trace("includeXmlContent end injecting xpointer");
        }
        else
        {
            LOG.trace("includeXmlContent start injecting");
            try
            {
                this.context.getConfiguration().getProcessor().writeXdmValue(node, saxDestination);
            }
            catch (final SaxonApiException e)
            {
                throw new XIncludeFatalException(e.getMessage());
            }
            LOG.trace("includeXmlContent end injecting");
        }
    }

    private void includeTextContent(final XIncludeAttributes xIncludeAttributes)
            throws XIncludeFatalException, XIncludeResourceException
    {
        final String content = XIncProcUtils.readTextURI(this.context.getSourceURI(), xIncludeAttributes.getEncoding(),
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

    private void startCommonElement(final String uri, final String localName, final String qName, final AttributesImpl attributesImpl)
            throws XIncludeFatalException
    {
        final int langAttIdx = attributesImpl.getIndex(NamespaceSupport.XMLNS,
                XIncludeConstants.XMLLANG_QNAME.getLocalPart());
        if (isTopElement())
        {
            if (!isInXIncludeElement())
            {
                if (this.context.isBaseFixup() && (null != this.context.getHrefURI()) && (null == this.context.getCurrentBaseURI()))
                {
                    final URI newBaseURI = this.context.getHrefURI();
                    attributesImpl.addAttribute(NamespaceSupport.XMLNS, XIncludeConstants.XMLBASE_QNAME.getLocalPart(),
                            "xml:base", "CDATA", newBaseURI.toASCIIString());
                }
                if (!this.context.isLanguageFixup() && (0 <= langAttIdx))
                {
                    attributesImpl.removeAttribute(langAttIdx);
                }
            }
            /*else
            {
                final int baseAttIdc = attributesImpl.getIndex(NamespaceSupport.XMLNS,
                        XIncludeConstants.XMLBASE_QNAME.getLocalPart());
                if (this.context.isBaseFixup() && (0 <= baseAttIdc))
                {
                    attributesImpl.removeAttribute(baseAttIdc);
                }
                if (this.context.isBaseFixup() && !this.context.getBaseURIPaths().isEmpty())
                {
                    attributesImpl.addAttribute(NamespaceSupport.XMLNS, XIncludeConstants.XMLBASE_QNAME.getLocalPart(),
                            "xml:base", "CDATA", Joiner.on("").join(this.context.getBaseURIPaths()));
                }
            }*/
        }
        if (0 <= langAttIdx)
        {
            this.currentLangStack.push(attributesImpl.getValue(langAttIdx));
        }
        else
        {
            this.currentLangStack.push(null);
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

    private void startFallbackElement()
            throws XIncludeFatalException
    {
        startingFallbackElement();
        if (!isInXIncludeElement() || (isNeedFallback() && (this.fallbackLevel > this.xIncludeLevel)))
        {
            throw new XIncludeFatalException("Fallback not in xinclude element container");
        }
        if (this.alreadyProceedFallback)
        {
            throw new XIncludeFatalException("Only one fallback element allowed in xinclude");
        }
    }

    private void startXIncludeElement(final Attributes atts)
            throws XIncludeFatalException, XIncludeResourceException
    {
        if (isInXIncludeElement() && !isInFallbackElement() && !isInjectingXInclude())
        {
            throw new XIncludeFatalException("XInclude element is not allowed into xinclude");
        }
        final XIncludeAttributes xIncludeAttributes = new XIncludeAttributes(atts);
        startingXIncludeElement();
        final URI hrefUri = xIncludeAttributes.getHref();
        if (xIncludeAttributes.isBasePresent())
        {
            this.context.setHrefURI(xIncludeAttributes.getBase().resolve(hrefUri));
        }
        else
        {
            this.context.setHrefURI(hrefUri);
        }
        URI sourceURI = XIncProcUtils.resolveBase(this.context.getInitialBaseURI(), this.context.getBaseURIPaths());
        if (xIncludeAttributes.isHrefPresent())
        {
            sourceURI = sourceURI.resolve(xIncludeAttributes.getHref());
        }
        this.context.setSourceURI(sourceURI);
        if (xIncludeAttributes.isHrefPresent())
        {
            this.context.addInInclusionChain(this.context.getSourceURI(), xIncludeAttributes.getXPointer());
        }
        else
        {
            this.context.addInInclusionChain(this.context.getInitialBaseURI(), xIncludeAttributes.getXPointer());
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
            startingNeedFallback();
            this.context.setCurrentException(e);
        }
    }

    private QName calculateElementName(final String uri, final String localName, final String qName)
    {
        final QName elementQName;
        if (Strings.isNullOrEmpty(uri) && Strings.isNullOrEmpty(localName) && !Strings.isNullOrEmpty(qName))
        {
            elementQName = QName.valueOf(StringUtils.substringAfter(qName, ":"));
        }
        else
        {
            elementQName = new QName(uri, localName);
        }
        return elementQName;
    }

    private boolean isInFallbackElement()
    {
        return 0 < this.fallbackLevel;
    }

    private void startingElement()
    {
        this.elementLevel++;
    }

    private void endingElement()
    {
        this.elementLevel--;
    }

    private boolean isTopElement()
    {
        return 1 == this.elementLevel;
    }

    private void startingFallbackElement()
    {
        this.fallbackLevel++;
    }

    private void endingFallbackElement()
    {
        this.fallbackLevel--;
        this.alreadyProceedFallback = true;
    }

    private boolean isInXIncludeElement()
    {
        return 0 < this.xIncludeLevel;
    }

    private void startingXIncludeElement()
    {
        this.xIncludeLevel++;
    }

    private void endingXIncludeElement()
    {
        this.xIncludeLevel--;
        this.alreadyProceedFallback = false;
    }

    private boolean isInjectingXInclude()
    {
        return 0 < this.injectingXIncludeLevel;
    }

    private void startingInjectXInclude()
    {
        this.injectingXIncludeLevel++;
    }

    private void endingInjectXInclude()
    {
        this.injectingXIncludeLevel--;
    }

    private void startingNeedFallback()
    {
        this.needFallbackLevel++;
    }

    private void endingNeedFallback()
    {
        this.needFallbackLevel--;
    }

    private boolean isNeedFallback()
    {
        return (0 < this.xIncludeLevel) && (this.needFallbackLevel == this.xIncludeLevel);
    }

    private boolean isUsable()
    {
        return (isNeedFallback() && isInFallbackElement()) ||
                (!isInFallbackElement() && !isInXIncludeElement()) ||
                (isInXIncludeElement() && isInjectingXInclude());
    }

    private final XIncludeContext context;
    private final Stack<String> currentLangStack;
    private boolean inDTD;
    private boolean hasUnparserEntity;
    private Optional<LexicalHandler> lexicalHandler = Optional.absent();
    private boolean alreadyProceedFallback;
    private int elementLevel;
    private int fallbackLevel;
    private int xIncludeLevel;
    private int needFallbackLevel;
    private int injectingXIncludeLevel;
}
