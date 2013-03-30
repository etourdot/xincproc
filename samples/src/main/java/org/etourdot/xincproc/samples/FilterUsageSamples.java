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

package org.etourdot.xincproc.samples;

import org.etourdot.xincproc.xinclude.XIncProcEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.net.URI;

public class FilterUsageSamples
{
    static final Logger LOG = LoggerFactory.getLogger(FilterUsageSamples.class);

    public void filterSample() throws Exception
    {
        final SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setXIncludeAware(true);
        spf.setNamespaceAware(true);
        final SAXParser parser = spf.newSAXParser();
        final PElementFilter filter = new PElementFilter(parser.getXMLReader());
        final InputSource inputSource = new InputSource(getClass().getClassLoader().getResource("include.xml")
                .toExternalForm());
        filter.parse(inputSource);
        LOG.info(filter.getResult());
    }

    public void filterSample2() throws Exception
    {
        //final SAXParserFactory spf = SAXParserFactory.newInstance();
        //final SAXParser parser = spf.newSAXParser();

        final XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        final URI fileUri = getClass().getClassLoader().getResource("include.xml").toURI();
        final XMLFilter filter1 = XIncProcEngine.newXIncludeFilter(fileUri);
        xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", filter1);
        xmlReader.setProperty("http://xml.org/sax/properties/declaration-handler", filter1);
        filter1.setParent(xmlReader);
        final PElementFilter filter = new PElementFilter(filter1);
        final InputSource inputSource = new InputSource(fileUri.toASCIIString());
        filter.parse(inputSource);
        LOG.info(filter.getResult());
    }

    public static void main(String[] args) throws Exception
    {
        final FilterUsageSamples filterUsageSamples = new FilterUsageSamples();
        filterUsageSamples.filterSample();
        filterUsageSamples.filterSample2();
    }

    class PElementFilter extends XMLFilterImpl
    {
        final StringBuilder stringBuilder = new StringBuilder();
        boolean treatChar;

        public PElementFilter(XMLReader reader)
        {
            super(reader);
            treatChar = false;
        }

        @Override
        public void characters(final char[] ch, final int start, final int length) throws SAXException
        {
            if (treatChar)
            {
                stringBuilder.append(new String(ch, start, length));
            }
        }

        @Override
        public void startElement(String uri, String localName,
                                 String qName, Attributes attributes)
                throws SAXException
        {
            LOG.trace("startElement:{}", qName);
            if ("p".equals(qName))
            {
                stringBuilder.append("\np:");
                treatChar = true;
                super.startElement(uri, localName, qName, attributes);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException
        {
            if ("p".equals(qName))
            {
                stringBuilder.append(":p");
                treatChar = false;
                super.endElement(uri, localName, qName);
            }
        }

        public String getResult()
        {
            return stringBuilder.toString();
        }
    }
}
