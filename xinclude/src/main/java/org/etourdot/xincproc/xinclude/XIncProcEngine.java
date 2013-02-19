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
package org.etourdot.xincproc.xinclude;

import com.google.common.io.ByteStreams;
import com.google.common.io.InputSupplier;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.trans.XPathException;
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

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.*;
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
        this.configuration = configuration;
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
        final XMLFilter filter = new XIncProcXIncludeFilter(context);
        return filter;
    }

    public static XMLFilter newXIncludeFilter(final XIncludeContext context)
    {
        final XMLFilter filter = new XIncProcXIncludeFilter(context);
        return filter;
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
            throws XIncludeFatalException, XPathException, XMLStreamException, IOException {
        LOG.trace("parse:{}", systemId);
        final Processor processor = configuration.getProcessor();
        final XIncProcXIncludeFilter filter;
        final URI uri;
        try
        {
            uri = new URI(systemId);
            filter = (XIncProcXIncludeFilter) newXIncludeFilter(uri);
        } catch (final URISyntaxException e)
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
            filter.setParent(xmlReader);
            final SAXSource saxSource = new SAXSource(filter, inputSource);
            XdmNode node = processor.newDocumentBuilder().build(saxSource);
            LOG.trace("parse result:{}", node.toString());
            Serializer serializer = processor.newSerializer(output);
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
