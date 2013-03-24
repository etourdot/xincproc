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

import org.etourdot.xincproc.xpointer.exceptions.XPointerException;
import org.xml.sax.InputSource;

import javax.xml.transform.sax.SAXSource;
import java.io.StringReader;

public abstract class AbstractXPointerTest {

    public Result executeWithBaseFixup(final String pointer, final String source, final String baseURI)
            throws XPointerException
    {
        final XPointerEngine xPointerEngine = new XPointerEngine();
        xPointerEngine.setBaseURI((baseURI.equals("null")) ? null : baseURI);
        return getResult(pointer, source, xPointerEngine);
    }

    public Result executeWithLang(final String pointer, final String lang, final String source)
            throws XPointerException
    {
        final XPointerEngine xPointerEngine = new XPointerEngine();
        if ("(null)".equals(lang))
        {
            xPointerEngine.setLanguage(null);
        }
        else
        {
            xPointerEngine.setLanguage(lang);
        }
        return getResult(pointer, source, xPointerEngine);
    }

    private Result getResult(String pointer, String source, XPointerEngine xPointerEngine)
            throws XPointerException
    {
        final Result result = new Result();
        final PrintableXPointerErrorHandler printableXPointerErrorHandler = new PrintableXPointerErrorHandler();
        xPointerEngine.setXPointerErrorHandler(printableXPointerErrorHandler);
        result.result = xPointerEngine.execute(pointer, new SAXSource(new InputSource(new StringReader(source))));
        result.error = printableXPointerErrorHandler.toString();
        return result;
    }

    public Result execute(final String pointer, final String source)
            throws XPointerException
    {
        final XPointerEngine xPointerEngine = new XPointerEngine();
        return getResult(pointer, source, xPointerEngine);
    }

    class Result {
        public String result;
        public String error;
    }
}
