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

package org.etourdot.xincproc.xpointer.element;

import net.sf.saxon.s9api.SaxonApiException;
import org.concordion.integration.junit4.ConcordionRunner;
import org.etourdot.xincproc.xpointer.AbstractXPointerTest;
import org.etourdot.xincproc.xpointer.XPointerEngine;
import org.etourdot.xincproc.xpointer.exceptions.XPointerException;
import org.junit.runner.RunWith;
import org.xml.sax.InputSource;

import javax.xml.transform.sax.SAXSource;
import java.io.StringReader;

@RunWith(ConcordionRunner.class)
public class BadElementTest extends AbstractXPointerTest {
    public Boolean isElementSameShorthand(String ncname, String element, String source)
            throws SaxonApiException, XPointerException
    {
        XPointerEngine xPointerEngine = new XPointerEngine();
        SAXSource saxSource = new SAXSource(new InputSource(new StringReader(source)));
        String shortHandResult = xPointerEngine.execute(ncname, saxSource);
        saxSource = new SAXSource(new InputSource(new StringReader(source)));
        String elementResult = xPointerEngine.execute(element, saxSource);
        return shortHandResult.equals(elementResult);
    }
}
