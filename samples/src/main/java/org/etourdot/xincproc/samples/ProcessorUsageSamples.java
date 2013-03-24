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

package org.etourdot.xincproc.samples;

import org.etourdot.xincproc.xinclude.XIncProcEngine;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 18/03/13
 * Time: 23:39
 */
public class ProcessorUsageSamples {
    private void domSample() throws Exception
    {
        final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setXIncludeAware(true);
        documentBuilderFactory.setNamespaceAware(true);
        final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        documentBuilder.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId)
                    throws SAXException, IOException
            {
                return new InputSource(systemId);
            }
        });
        final Document document = documentBuilder.parse(new InputSource(getClass().getClassLoader()
                .getResource("include.xml").toExternalForm()));
        final Source source = new DOMSource(document);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Result result = new StreamResult(baos);
        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);
        System.out.print(new String(baos.toByteArray()));
    }

    private void xincProcSample() throws Exception
    {
        //final XIncProcEngine xIncProcEngine = XIncProcEngine.newXIncProcEngine();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XIncProcEngine.parse(getClass().getClassLoader().getResource("include.xml").toURI(), baos);
        System.out.print(new String(baos.toByteArray()));
    }

    public static void main(String[] args) throws Exception
    {
        final ProcessorUsageSamples processorUsageSamples = new ProcessorUsageSamples();
        processorUsageSamples.domSample();
        processorUsageSamples.xincProcSample();
    }
}
