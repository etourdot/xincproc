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

import net.sf.saxon.s9api.*;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.etourdot.xincproc.xinclude.sax.XIncProcXIncludeFilter;
import org.etourdot.xincproc.xinclude.sax.XIncludeContext;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Emmanuel Tourdot
 */
public class XIncProcEngine
{

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
        XIncludeContext context = new XIncludeContext(configuration);
        context.setSourceURI(baseURI);
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
            filter.setParent(xmlReader);
            final SAXSource saxSource = new SAXSource(inputSource);
            saxSource.setXMLReader(filter);
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
            throws XIncludeFatalException
    {
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
        final InputSource inputSource = new InputSource(input);
        inputSource.setSystemId(systemId);
        try
        {
            final XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            filter.setParent(xmlReader);
            final SAXSource saxSource = new SAXSource(inputSource);
            saxSource.setXMLReader(filter);
            XdmNode node = processor.newDocumentBuilder().build(saxSource);
            if (filter.getContext().isNeedSecondPass())
            {
                InputSource inputSource1 = new InputSource(new StringReader(node.toString()));
                InputSource inputSource2 = new InputSource(new StringReader(node.toString()));
                final XIncProcXIncludeFilter secondFilter = (XIncProcXIncludeFilter) newXIncludeFilter(uri);
                secondFilter.getContext().inPassTwo();
                secondFilter.getContext().setSource(inputSource1);
                secondFilter.setParent(xmlReader);
                final SAXSource secondSource = new SAXSource(inputSource2);
                secondSource.setXMLReader(secondFilter);
                node = processor.newDocumentBuilder().wrap(secondSource);
            }
            Serializer serializer = processor.newSerializer(output);
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
