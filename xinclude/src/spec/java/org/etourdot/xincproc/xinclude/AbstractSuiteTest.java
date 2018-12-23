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

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import net.sf.saxon.lib.Validation;
import net.sf.saxon.s9api.Processor;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.ComparisonControllers;
import org.xmlunit.diff.Diff;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

public abstract class AbstractSuiteTest {
    static final Logger LOG = LoggerFactory.getLogger(AbstractSuiteTest.class);
    private Processor processor;

    @Before
    public void setUp() throws Exception
    {
        processor = new Processor(false);
        processor.getUnderlyingConfiguration().setSchemaValidationMode(Validation.LAX);
    }

    protected Diff control(final ByteArrayOutputStream output, final String fileResult) throws Exception
    {
        final String result = new String(output.toByteArray(), "UTF-8");
        //LOG.debug("Test result:{}", result);
        final String control = Files.toString(new File(fileResult), Charset.forName("UTF-8"));
        //LOG.debug("Test control:{}", control);
        return DiffBuilder.compare(new StringReader(control)).withTest(new StringReader(result)).normalizeWhitespace()
          .ignoreWhitespace().build();
    }

    protected void testSuccess(final URL urlTest, final URL urlResult) throws Exception
    {
        testSuccess(urlTest, urlResult, true, true);
    }

    protected void testSuccess(final URL urlTest, final URL urlResult,
                               final boolean fixupBase, final boolean fixupLang)
            throws Exception
    {
        XIncProcEngine.getUnderlyingConfiguration().setConfigurationProperty(XIncProcConfiguration.ALLOW_FIXUP_BASE_URIS, fixupBase);
        XIncProcEngine.getUnderlyingConfiguration().setConfigurationProperty(XIncProcConfiguration.ALLOW_FIXUP_LANGUAGE, fixupLang);
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final FileInputStream source = new FileInputStream(urlTest.getPath());
        XIncProcEngine.parse(source, urlTest.toExternalForm(), output);
        final String resultat = output.toString("UTF-8");
        final Diff diff = DiffBuilder.compare(Resources.toString(urlResult, Charsets.UTF_8)).withTest(resultat)
          .ignoreWhitespace().build();
        source.close();
        assertFalse("testSuccess:" + urlTest, diff.hasDifferences());
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
        XIncProcEngine.getUnderlyingConfiguration().setConfigurationProperty(XIncProcConfiguration.ALLOW_FIXUP_BASE_URIS, fixupBase);
        XIncProcEngine.getUnderlyingConfiguration().setConfigurationProperty(XIncProcConfiguration.ALLOW_FIXUP_LANGUAGE, fixupLang);
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final FileInputStream source = new FileInputStream(urlTest.getPath());
        try
        {
            XIncProcEngine.parse(source, urlTest.toExternalForm(), output);
            LOG.debug("Result:{}", new String(output.toByteArray(), "UTF-8"));
        }
        catch (Exception e)
        {
            final Class testedClass = (null == e.getCause()) ? e.getClass() : e.getCause().getClass();
            assertTrue(exception.isAssignableFrom(testedClass));
            return;
        }
        finally
        {
            source.close();
        }
        fail();
    }

    public Result execute(final String type, final String inputHref, final String outputHref)
            throws IOException
    {
        final Result result = new Result();
        XIncProcEngine.getUnderlyingConfiguration().setConfigurationProperty(XIncProcConfiguration.ALLOW_FIXUP_BASE_URIS, true);
        XIncProcEngine.getUnderlyingConfiguration().setConfigurationProperty(XIncProcConfiguration.ALLOW_FIXUP_LANGUAGE, true);
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final URL urlInput = getClass().getClassLoader().getResource("XIncl20060927/" + inputHref);
        final FileInputStream source = new FileInputStream(urlInput.getPath());
        if ("success".equals(type))
        {
            try
            {
                XIncProcEngine.parse(source, urlInput.toExternalForm(), output);
                final URL urlTest = getClass().getClassLoader().getResource("XIncl20060927/" + outputHref);
                final String expected = Resources.toString(urlTest, Charsets.UTF_8);
                final Diff diff = DiffBuilder.compare(expected).withTest(output.toString("UTF-8"))
                  .withComparisonController(ComparisonControllers.StopWhenDifferent)
                  .ignoreWhitespace().build();
                result.output = StringEscapeUtils.escapeHtml4(new String(output.toByteArray()));
                result.expected = StringEscapeUtils.escapeHtml4(expected);
                if (diff.hasDifferences()) {
                    result.result = "error";
                    result.exception = StringEscapeUtils.escapeHtml4(Joiner.on(" ").join(diff.getDifferences()));
                } else {
                    result.result = "success";
                }
            }
            catch (Exception e)
            {
                if (null != e.getCause())
                {
                    result.exception = e.getCause().getMessage();
                }
                else
                {
                    result.exception = e.getMessage();
                }
                result.result = "error";
            }
        }
        else
        {
            try
            {
                XIncProcEngine.parse(source, urlInput.toExternalForm(), output);
                result.output = new String(output.toByteArray());
                result.result = "success";
            }
            catch (Exception e)
            {
                if (null != e.getCause())
                {
                    result.exception = e.getCause().getMessage();
                }
                else
                {
                    result.exception = e.getMessage();
                }
                result.result = "error";
            }
        }
        source.close();
        return result;
    }

    class Result {
        Result()
        {
            exception = "";
        }

        public String result;
        public String error;
        public String exception;
        public String output;
        public String expected;
    }
}
