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
package org.etourdot.xinclude.stax;

import com.google.common.base.Charsets;
import org.etourdot.xinclude.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Emmanuel Tourdot
 */
public class XIncludeStaxEngine implements XIncProcEngineHandler
{
    private static final Logger LOG = LoggerFactory.getLogger(XIncludeStaxEngine.class);
    private final XIncProcStaxFactory factory;

    XIncludeStaxEngine(final XIncProcStaxFactory factory)
    {
        this.factory = factory;
    }

    @Override
    public void parse(final URI baseURI, final OutputStream output) throws XIncludeFatalException
    {
        final Source inputSource = new StreamSource(new File(baseURI.getRawPath()));
        final Result outputResult = new StreamResult(output);
        parse(inputSource, outputResult);
    }

    @Override
    public void parse(final Source source, final Result result) throws XIncludeFatalException
    {
        try
        {
            final XMLEventReader reader = factory.getXmlInputFactory().createXMLEventReader(source);
            final XMLEventWriter writer = factory.getXmlOutputFactory().createXMLEventWriter(result);
            XIncludeStaxProcessor processor = new XIncludeStaxProcessor(factory, new URI(source.getSystemId()));
            processor.doProcess(new URI(""), reader, writer);
            writer.flush();
            writer.close();
        }
        catch (final URISyntaxException e)
        {
            throw new XIncludeFatalException(e);
        }
        catch (final XMLStreamException e)
        {
            throw new XIncludeFatalException(e);
        }
    }

    @Override
    public void parse(final InputStream source, final String systemId, final OutputStream result) throws XIncludeFatalException
    {
        try
        {
            final XMLEventReader reader = factory.getXmlInputFactory().createXMLEventReader(source, Charsets.UTF_8.displayName());
            final XMLEventWriter writer = factory.getXmlOutputFactory().createXMLEventWriter(result, Charsets.UTF_8.displayName());
            XIncludeStaxProcessor processor = new XIncludeStaxProcessor(factory, new URI(systemId));
            processor.doProcess(new URI(""), reader, writer);
            writer.flush();
            writer.close();
        }
        catch (final URISyntaxException e)
        {
            throw new XIncludeFatalException(e);
        }
        catch (final XMLStreamException e)
        {
            throw new XIncludeFatalException(e);
        }
    }

}
