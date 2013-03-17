/*
 * This file is part of the XIncProc framework.
 * Copyright (C) 2010 - 2013 Emmanuel Tourdot
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
import com.google.common.io.ByteStreams;
import com.google.common.io.InputSupplier;
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

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/**
 * @author Emmanuel Tourdot
 */
public class XIncProcEngine {
    private static final Logger LOG = LoggerFactory.getLogger(XIncProcEngine.class);
    private static XIncProcConfiguration configuration;

    public XIncProcEngine()
    {
        configuration = new XIncProcConfiguration();
    }

    public XIncProcEngine(final XIncProcConfiguration configuration)
    {
        XIncProcEngine.configuration = configuration;
    }

    public XIncProcEngine(final Processor processor)
    {
        configuration = new XIncProcConfiguration(processor);
    }

    public static XMLFilter newXIncludeFilter(final URI baseURI)
    {
        final XIncludeContext context = new XIncludeContext(configuration);
        context.setSourceURI(baseURI);
        context.setInitialBaseURI(baseURI);
        return new XIncProcXIncludeFilter(context);
    }

    public static XMLFilter newXIncludeFilter(final XIncludeContext context)
    {
        return new XIncProcXIncludeFilter(context);
    }

    public void parse(final URI baseURI, final OutputStream output)
            throws XIncludeFatalException
    {
        final Processor processor = configuration.getProcessor();
        final XMLFilter filter = newXIncludeFilter(baseURI);
        final InputSource inputSource = new InputSource(baseURI.toASCIIString());
        try
        {
            final XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", filter);
            xmlReader.setProperty("http://xml.org/sax/properties/declaration-handler", filter);
            xmlReader.setFeature("http://xml.org/sax/features/resolve-dtd-uris", false);
            //xmlReader.setFeature("http://xml.org/sax/features/external-general-entities", false);
            filter.setParent(xmlReader);
            final SAXSource saxSource = new SAXSource(filter, inputSource);
            final XdmNode node = processor.newDocumentBuilder().wrap(saxSource);
            Serializer serializer = processor.newSerializer(output);
            processor.writeXdmValue(node, serializer);
        }
        catch (final SAXException e)
        {
            throw new XIncludeFatalException(e);
        }
        catch (SaxonApiException e)
        {
            throw new XIncludeFatalException(e);
        }
    }

    public void parse(final Source source, final Result result) throws XIncludeFatalException
    {
        //parse(source, result);
    }

    public void parse(final InputStream input, final String systemId, final OutputStream output)
            throws XIncludeFatalException, IOException
    {
        LOG.trace("parse:{}", systemId);
        final Processor processor = configuration.getProcessor();
        final XIncProcXIncludeFilter filter;
        final URI uri;
        try
        {
            uri = new URI(systemId);
            filter = (XIncProcXIncludeFilter) newXIncludeFilter(uri);
        }
        catch (final URISyntaxException e)
        {
            throw new XIncludeFatalException(e);
        }
        final byte[] inputBytes = ByteStreams.toByteArray(input);
        InputSupplier<ByteArrayInputStream> supplier = ByteStreams.newInputStreamSupplier(inputBytes);
        Charset charset = EncodingUtils.getCharset(supplier.getInput());
        final InputSource inputSource = new InputSource(supplier.getInput());
        inputSource.setSystemId(systemId);
        try
        {
            final XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", filter);
            xmlReader.setProperty("http://xml.org/sax/properties/declaration-handler", filter);
            xmlReader.setFeature("http://xml.org/sax/features/resolve-dtd-uris", false);
            //xmlReader.setFeature("http://xml.org/sax/features/external-general-entities", false);

            filter.setParent(xmlReader);
            final SAXSource saxSource = new SAXSource(filter, inputSource);
            XdmNode node = processor.newDocumentBuilder().build(saxSource);
            LOG.trace("parse result:{}", node.toString());
            output.write("<?xml version=\"1.0\" encoding=\"".getBytes());
            output.write(charset.displayName().getBytes());
            output.write("\"?>".getBytes());
            final String docType = filter.getDoctype();
            if (!Strings.isNullOrEmpty(docType))
            {
                output.write(docType.getBytes("UTF-8"));
            }
            Serializer serializer = processor.newSerializer(output);
            serializer.setOutputProperty(Serializer.Property.OMIT_XML_DECLARATION, "yes");
            serializer.setOutputProperty(Serializer.Property.ENCODING, charset.displayName());

            processor.writeXdmValue(node, serializer);
        }
        catch (final SAXException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
        catch (SaxonApiException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
    }

    public XIncProcConfiguration getConfiguration()
    {
        return configuration;
    }
}
