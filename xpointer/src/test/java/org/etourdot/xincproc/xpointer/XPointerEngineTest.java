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

package org.etourdot.xincproc.xpointer;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import org.etourdot.xincproc.xpointer.model.Pointer;
import org.etourdot.xincproc.xpointer.model.XmlNsScheme;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.junit.Assert.assertEquals;

public class XPointerEngineTest {
    private static final Logger log = LoggerFactory.getLogger(XPointerEngineTest.class);
    private Source source;
    private XPointerEngine xPointerEngine;
    private PrintableXPointerErrorHandler printableXPointerErrorHandler;

    @Before
    public void beforeTest()
    {
        source = new SAXSource(new InputSource(getClass().getClassLoader().getResourceAsStream("doc_test.xml")));
        xPointerEngine = new XPointerEngine();
        printableXPointerErrorHandler = new PrintableXPointerErrorHandler();
        xPointerEngine.setXPointerErrorHandler(printableXPointerErrorHandler);
    }

    @Test
    public void testValidPointers()
            throws Exception
    {
        final File validPointersFile = new File(this.getClass().getClassLoader().getResource("valid_pointers.txt").getFile());
        final List<String> lines = Files.readLines(validPointersFile, Charsets.UTF_8);
        for (String line : lines)
        {
            log.debug("##############");
            try
            {
                Pointer pointer = xPointerEngine.getPointer(line);
                log.debug("Pointer:{}", pointer);
                Assert.assertNotNull(line, pointer);
            }
            catch (Exception e)
            {
                Assert.fail(line);
            }
        }
    }

    @Test
    public void testInvalidPointers()
            throws Exception
    {
        final File validPointersFile = new File(this.getClass().getClassLoader().getResource("invalid_pointers.txt").getFile());
        final List<String> lines = Files.readLines(validPointersFile, Charsets.UTF_8);
        for (String line : lines)
        {
            try
            {
                Pointer pointer = xPointerEngine.getPointer(line);
                if (null != pointer && (pointer.isSchemeBased() || pointer.isShortHandPresent()))
                {
                    Assert.fail(line);
                }
            }
            catch (Exception e)
            {
            }
        }
    }

    @Test
    public void testExecuteShortHand()
            throws Exception
    {
        final String result = xPointerEngine.execute("id5", source);
        assertXMLEqual("<etat xml:id=\"id5\">VIGUEUR</etat>", result);
    }

    @Test
    public void testExecuteShortHandWithBasefixup1()
            throws Exception
    {
        final String result = xPointerEngine.setBaseURI(null).execute("id3", source);
        assertXMLEqual("<?xml version=\"1.0\" encoding=\"UTF-8\"?><etat xml:id=\"id3\">VIGUEUR</etat>", result);
    }

    @Test
    public void testExecuteShortHandWithLang()
            throws Exception
    {
        final String result = xPointerEngine.setBaseURI(null).setLanguage("fr").execute("id4", source);
        assertXMLEqual("<?xml version=\"1.0\" encoding=\"UTF-8\"?><contenu id=\"id4\" xml:lang=\"fr\">\n" +
                "            <para>Est autorisée l'approbation de l'accord entre le Gouvernement de la République française et le\n" +
                "                Gouvernement de la République du Bénin relatif à la gestion concertée des flux migratoires et au\n" +
                "                codéveloppement (ensemble cinq annexes), signé à Cotonou le 28 novembre 2007, et dont le texte est\n" +
                "                annexé à la présente loi (2).\n" +
                "            </para>\n" +
                "            <para>La présente loi sera exécutée comme loi de l'Etat.</para>\n" +
                "        </contenu>", result);
    }

    @Test
    public void testExecuteSchema() throws Exception
    {
        final String result = xPointerEngine.execute("element(id5)", source);
        assertXMLEqual("<etat xml:id=\"id5\">VIGUEUR</etat>", result);
    }

    @Test
    public void testVerifyXPathExpression() throws Exception
    {
        String validExpr = xPointerEngine.verifyXPathExpression(ImmutableList.<XmlNsScheme>of(), "//price/following-sibling::*");
        assertEquals("", validExpr);
        validExpr = xPointerEngine.verifyXPathExpression(ImmutableList.<XmlNsScheme>of(), "//price/following-sibling:*");
        assertEquals("Prefix following-sibling has not been declared", validExpr);
    }

    @Test
    public void testExecuteXPath() throws Exception
    {
        final String result = xPointerEngine.execute("xpath(//author/@id='auth1')", source);
        assertEquals("true", result);
    }

    @Test
    public void testExecuteBadXPath() throws Exception
    {
        final String result = xPointerEngine.execute("xpath(//@id='auth1'])", source);
        assertEquals("", result);
        assertEquals("Unexpected token \"]\" beyond end of expression", printableXPointerErrorHandler.toString());
    }

    @Test
    public void testExecuteBadXmlns() throws Exception
    {
        final String result = xPointerEngine.execute("xmlns(bad_expression) xpath(//author[@id='auth1'])", source);
        assertEquals("<author id=\"auth1\">myself</author>", result);
    }

    @Test
    public void testID() throws Exception
    {
        source = new SAXSource(new InputSource(getClass().getClassLoader().getResourceAsStream("idtst.xml")));
        final String result = xPointerEngine.execute("element(/1/2)xpointer(id('f2'))", source);
        assertEquals("<family fnumber=\"f2\">Clark</family>", result);
    }
}
