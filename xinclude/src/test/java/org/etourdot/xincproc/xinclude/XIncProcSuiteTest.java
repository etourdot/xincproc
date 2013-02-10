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

import com.google.common.io.Closeables;
import com.google.common.io.Files;
import net.sf.saxon.lib.Validation;
import net.sf.saxon.s9api.Processor;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertTrue;

/**
 * @author Emmanuel Tourdot
 */
public abstract class XIncProcSuiteTest {
    static final Logger LOG = LoggerFactory.getLogger(XIncProcSuiteTest.class);
    private Processor processor;

    @Before
    public void setUp() throws Exception
    {
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true);
        XMLUnit.setControlParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
        XMLUnit.setTestParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
        XMLUnit.setSAXParserFactory("org.apache.xerces.jaxp.SAXParserFactoryImpl");
        XMLUnit.setTransformerFactory("org.apache.xalan.processor.TransformerFactoryImpl");
        processor = new Processor(false);
        processor.getUnderlyingConfiguration().setSchemaValidationMode(Validation.LAX);
    }

    protected Diff control(final ByteArrayOutputStream output, final String fileResult) throws Exception
    {
        final String result = new String(output.toByteArray(), "UTF-8");
        //LOG.debug("Test result:{}", result);
        final String control = Files.toString(new File(fileResult), Charset.forName("UTF-8"));
        //LOG.debug("Test control:{}", control);
        return new Diff(new StringReader(control), new StringReader(result));
    }

    protected void testSuccess(final URL urlTest, final URL urlResult) throws Exception
    {
        testSuccess(urlTest, urlResult, true, true);
    }

    protected void testSuccess(final URL urlTest, final URL urlResult,
                               final boolean fixupBase, final boolean fixupLang)
            throws Exception
    {
        final XIncProcEngine engine = new XIncProcEngine();
        engine.getConfiguration().setBaseUrisFixup(fixupBase);
        engine.getConfiguration().setLanguageFixup(fixupLang);
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final FileInputStream source = new FileInputStream(urlTest.getPath());
        engine.parse(source, urlTest.toExternalForm(), output);
        final String resultat = output.toString("UTF-8");
        LOG.debug("resultat:{}", resultat);
        final Diff diff = XMLUnit.compareXML(XIncProcUtils.readTextURI(urlResult.toURI(),null,null,null), resultat);
        LOG.debug("Diff result:{}", diff.toString());
        Closeables.closeQuietly(source);
        assertTrue("testSuccess:" + urlTest, diff.identical());
    }

    protected void testException(final URL urlTest, final Class exception)
            throws Exception
    {
        testException(urlTest, exception, true, true);
    }

    protected void testException(final URL urlTest, final Class exception,
                                 final boolean fixupBase, final boolean fixupLang)
            throws Exception
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
            final Class testedClass = (e.getCause()==null)?e.getClass() : e.getCause().getClass();
            assertTrue(exception.isAssignableFrom(testedClass));
            return;
        }
        finally
        {
            Closeables.closeQuietly(source);
        }
        fail();
    }
}
