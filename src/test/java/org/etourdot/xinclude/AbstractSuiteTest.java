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
package org.etourdot.xinclude;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import net.sf.saxon.om.DocumentInfo;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmNode;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.etourdot.xinclude.sax.XIncludeFilter;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.transform.sax.SAXSource;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

import static junit.framework.Assert.assertTrue;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.junit.Assert.fail;

/**
 * @author Emmanuel Tourdot
 */
public abstract class AbstractSuiteTest {
    static final Logger LOG = LoggerFactory.getLogger(AbstractSuiteTest.class);

    @Before
    public void setUp() throws Exception
    {
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true);
    }

    protected Diff control(final ByteArrayOutputStream output, final String fileResult) throws Exception
    {
        final String result = new String(output.toByteArray(), "UTF-8");
        //LOG.debug("Test result:{}", result);
        final String control = Files.toString(new File(fileResult), Charset.forName("UTF-8"));
        //LOG.debug("Test control:{}", control);
        return new Diff(new StringReader(control), new StringReader(result));
    }

    protected Diff checkXdmNodeWithResult(final XdmNode node, final String fileResult) throws Exception
    {
        final Processor processor = node.getProcessor();
        final Serializer serializer = processor.newSerializer();
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        serializer.setOutputStream(output);
        processor.writeXdmValue(node, serializer);
        return control(output, fileResult);
    }

    protected void testsax(final URL urlTest, final URL urlResult) throws Exception
    {
        final XIncProcEngine engine = new XIncProcEngine();
        final Processor processor = engine.getConfiguration().getProcessor();
        final InputSource input = new InputSource(new FileReader(urlTest.getPath()));
        final XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        final XIncludeFilter filter = XIncProcEngine.newXIncludeFilter(urlTest.toURI());
        filter.setParent(xmlReader);
        final SAXSource source = new SAXSource(input);
        source.setXMLReader(filter);
        final DocumentInfo doc = processor.getUnderlyingConfiguration().buildDocument(source);
        final Diff diff = checkXdmNodeWithResult(new XdmNode(doc), urlResult.getPath());
        LOG.debug("result:{}",new XdmNode(doc));
        LOG.debug("Diff result:{}", diff.toString());
        assertTrue("TestSax:" + urlTest, diff.similar());
    }

    protected void testsax(final URL urlTest, final Class exception) throws Exception
    {
        final XIncProcEngine engine = new XIncProcEngine();
        final Processor processor = engine.getConfiguration().getProcessor();
        try
        {
            final InputSource input = new InputSource(new FileReader(urlTest.getPath()));
            final XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            final XIncludeFilter filter = XIncProcEngine.newXIncludeFilter(urlTest.toURI());
            filter.setParent(xmlReader);
            final SAXSource source = new SAXSource(input);
            source.setXMLReader(filter);
            final DocumentInfo doc = processor.getUnderlyingConfiguration().buildDocument(source);
        }
        catch (Exception e)
        {
            assertTrue(e.getCause().getClass().equals(exception));
            return;
        }
        fail();
    }

    protected void teststax(final URL urlTest, final URL urlResult) throws Exception
    {
        final XIncProcEngine engine = new XIncProcEngine();
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        engine.parse(urlTest.toURI(), output);
        final String resultat = new String(output.toByteArray(), "UTF-8");
        LOG.debug("Teststax {}: {}", new Object[]{urlTest, resultat});
        Diff diff = new Diff(Resources.toString(urlResult, Charsets.UTF_8), resultat);
        assertTrue("Teststax:" + urlTest, diff.similar());
    }

    protected void teststax(final URL urlTest, final Class exception) throws Exception
    {
        final XIncProcEngine engine = new XIncProcEngine();
        try
        {
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            engine.parse(urlTest.toURI(), output);
            LOG.debug("Result:{}", new String(output.toByteArray()));
        }
        catch (Exception e)
        {
            assertTrue(e.getClass().equals(exception));
            return;
        }
        fail();
    }

    protected void testxproc(final URL urlTest, final URL urlResult,
                             final boolean fixupBase, final boolean fixupLang) throws Exception
    {
        final XIncProcEngine engine = new XIncProcEngine();
        engine.getConfiguration().setBaseUrisFixup(fixupBase);
        engine.getConfiguration().setLanguageFixup(fixupLang);
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final FileInputStream source = new FileInputStream(urlTest.getPath());
        engine.parse(source, urlTest.toExternalForm(), output);
        final String resultat = new String(output.toByteArray(), "UTF-8");
        LOG.debug("TestXProc1 {}: {}", new Object[]{urlTest, resultat});
        assertXMLEqual(Resources.toString(urlResult, Charsets.UTF_8), resultat);
    }

    protected void testxproc(final URL urlTest, final Class exception,
                             final boolean fixupBase, final boolean fixupLang) throws Exception
    {
        final XIncProcEngine engine = new XIncProcEngine();
        engine.getConfiguration().setBaseUrisFixup(fixupBase);
        engine.getConfiguration().setLanguageFixup(fixupLang);
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final FileInputStream source = new FileInputStream(urlTest.getPath());
        try
        {
            engine.parse(source, urlTest.toExternalForm(), output);
            LOG.debug("Result:{}", new String(output.toByteArray(), "UTF-8"));
        }
        catch (Exception e)
        {
            assertTrue(e.getClass().equals(exception));
            return;
        }
        fail();
    }
}
