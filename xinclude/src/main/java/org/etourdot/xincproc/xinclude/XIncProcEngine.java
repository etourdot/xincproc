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

package org.etourdot.xincproc.xinclude;

import com.google.common.base.Strings;
import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmNode;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.etourdot.xincproc.xinclude.sax.XIncProcXIncludeFilter;
import org.etourdot.xincproc.xinclude.sax.XIncludeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * This class contains useful methods to manage a {@link org.xml.sax.XMLFilter} for xinclusions
 * and to parse content with xinclude tags.
 *
 * Static usage of this class only use Saxon He processor. If you want to use another version
 * of the Saxon processor you should instanciate <code>XIncProcEngine</code> with {@link XIncProcConfiguration}.
 */
public final class XIncProcEngine
{
    private static final Logger LOG = LoggerFactory.getLogger(XIncProcEngine.class);
    private static final XIncProcConfiguration CONFIGURATION = XIncProcConfiguration.newXIncProcConfiguration();

    private XIncProcEngine()
    {
        this.configuration = XIncProcEngine.CONFIGURATION;
    }

    protected XIncProcEngine(final XIncProcConfiguration configuration)
    {
        this.configuration = configuration;
    }

    /**
     * Create statically a new {@link XIncProcXIncludeFilter}
     * from a base fixed URI
     *
     * @param baseURI initial base URI for the filter
     * @return an XIncProcXIncludeFilter
     */
    public static XMLFilter newXIncludeFilter(final URI baseURI)
    {
        final XIncludeContext context = new XIncludeContext(XIncProcEngine.CONFIGURATION);
        context.setSourceURI(baseURI);
        context.setInitialBaseURI(baseURI);
        return newXIncludeFilter(context);
    }

    /**
     * Create statically a new {@link XIncProcXIncludeFilter}
     * from a {@link XIncludeContext}

     * @param context intial context for the filter
     * @return an XIncProcXIncludeFilter
     */
    public static XMLFilter newXIncludeFilter(final XIncludeContext context)
    {
        return new XIncProcXIncludeFilter(context);
    }

    /**
     * Statically parse an {@link URI}
     *
     * @param baseURI URI to be parsed
     * @param output {@link OutputStream} will store result
     * @throws XIncludeFatalException Fatal exception
     */
    public static void parse(final URI baseURI, final OutputStream output)
            throws XIncludeFatalException
    {
        final Processor processor = XIncProcEngine.CONFIGURATION.getProcessor();
        final XMLFilter filter = XIncProcEngine.newXIncludeFilter(baseURI);
        final InputSource inputSource = new InputSource(baseURI.toASCIIString());
        try
        {
            final XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            filter.setParent(xmlReader);
            final SAXSource saxSource = new SAXSource(filter, inputSource);
            final XdmNode node = processor.newDocumentBuilder().wrap(saxSource);
            final Serializer serializer = processor.newSerializer(output);
            processor.writeXdmValue(node, serializer);
        }
        catch (final SAXException | SaxonApiException | ParserConfigurationException e)
        {
            throw new XIncludeFatalException(e);
        }
    }

    /**
     * Statically parse an {@link InputStream}
     *
     * @param input {@link InputStream} to parse
     * @param systemId of the input. If this is null or not an URI, method exits with XIncludeFatalException.
     * @param output {@link OutputStream} will store result
     * @throws XIncludeFatalException
     * @throws IOException
     */
    public static void parse(final InputStream input, final String systemId, final OutputStream output)
            throws XIncludeFatalException, IOException
    {
        LOG.trace("parse:{}", systemId);
        final Processor processor = XIncProcEngine.CONFIGURATION.getProcessor();
        final XIncProcXIncludeFilter filter;
        try
        {
            final URI uri = new URI(systemId);
            filter = (XIncProcXIncludeFilter) XIncProcEngine.newXIncludeFilter(uri);
        }
        catch (final URISyntaxException e)
        {
            throw new XIncludeFatalException(e);
        }
        final byte[] inputBytes = ByteStreams.toByteArray(input);
        final ByteSource supplier = ByteSource.wrap(inputBytes);
        final Charset charset = EncodingUtils.getCharset(supplier.openStream());
        final InputSource inputSource = new InputSource(supplier.openStream());
        inputSource.setSystemId(systemId);
        try
        {
            final XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            xmlReader.setFeature("http://xml.org/sax/features/resolve-dtd-uris", false);
            filter.setParent(xmlReader);
            final Source saxSource = new SAXSource(filter, inputSource);
            final XdmNode node = processor.newDocumentBuilder().build(saxSource);

            LOG.trace("parse result:{}", node.toString());
            serializeNode(output, processor, filter, charset, node);
        }
        catch (final SAXException | SaxonApiException | ParserConfigurationException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
    }

    private static void serializeNode(final OutputStream output, final Processor processor,
                                      final XIncProcXIncludeFilter filter,
                                      final Charset charset, final XdmNode node)
            throws IOException, SaxonApiException
    {
        output.write("<?xml version=\"1.0\" encoding=\"".getBytes());
        output.write(charset.displayName().getBytes());
        output.write("\"?>".getBytes());
        final String docType = filter.getDoctype();
        if (!Strings.isNullOrEmpty(docType))
        {
            output.write(docType.getBytes(StandardCharsets.UTF_8));
        }
        final Serializer serializer = processor.newSerializer(output);
        serializer.setOutputProperty(Serializer.Property.OMIT_XML_DECLARATION, "yes");
        serializer.setOutputProperty(Serializer.Property.ENCODING, charset.displayName());
        processor.writeXdmValue(node, serializer);
    }

    /**
     * Return current configuration of the XIncProcEngine
     *
     * @return the {@link XIncProcConfiguration}
     */
    public XIncProcConfiguration getConfiguration()
    {
        return this.configuration;
    }

    /**
     * Get the internal {@link XIncProcConfiguration} for static usage
     *
     * @return a static configuration
     */
    public static XIncProcConfiguration getUnderlyingConfiguration()
    {
        return XIncProcEngine.CONFIGURATION;
    }

    private XIncProcConfiguration configuration;
}
