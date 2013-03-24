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

package org.etourdot.xincproc.xinclude.sax;

import org.etourdot.xincproc.xinclude.exceptions.XIncludeResourceException;
import org.junit.Test;
import org.xml.sax.helpers.AttributesImpl;

import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 28/12/12
 * Time: 17:03
 */
public class XIncludeAttributesTest {

    @Test
    public void testXIncludeAttributesOk() throws Exception
    {
        AttributesImpl attributes = new AttributesImpl();
        attributes.addAttribute(XIncludeConstants.ATT_HREF.getNamespaceURI(), XIncludeConstants.ATT_HREF.getLocalPart(),
                XIncludeConstants.ATT_HREF.getLocalPart(), "CDATA", "http://www.google.com");
        attributes.addAttribute(XIncludeConstants.ATT_PARSE.getNamespaceURI(), XIncludeConstants.ATT_PARSE.getLocalPart(),
                XIncludeConstants.ATT_PARSE.getLocalPart(), "CDATA", "text");
        XIncludeAttributes xIncludeAttributes = new XIncludeAttributes(attributes);
        assertNotNull(xIncludeAttributes);
        attributes = new AttributesImpl();
        attributes.addAttribute(XIncludeConstants.ATT_HREF.getNamespaceURI(), XIncludeConstants.ATT_HREF.getLocalPart(),
                XIncludeConstants.ATT_HREF.getLocalPart(), "CDATA", "http://www.google.com");
        attributes.addAttribute(XIncludeConstants.ATT_PARSE.getNamespaceURI(), XIncludeConstants.ATT_PARSE.getLocalPart(),
                XIncludeConstants.ATT_PARSE.getLocalPart(), "CDATA", "xml");
        xIncludeAttributes = new XIncludeAttributes(attributes);
        assertNotNull(xIncludeAttributes);
    }

    @Test(expected = XIncludeResourceException.class)
    public void testResourceException() throws Exception
    {
        AttributesImpl attributes = new AttributesImpl();
        attributes.addAttribute(XIncludeConstants.ATT_ENCODING.getNamespaceURI(), XIncludeConstants.ATT_ENCODING.getLocalPart(),
                XIncludeConstants.ATT_ENCODING.getLocalPart(), "CDATA", "utf-9");
        attributes.addAttribute(XIncludeConstants.ATT_PARSE.getNamespaceURI(), XIncludeConstants.ATT_PARSE.getLocalPart(),
                XIncludeConstants.ATT_PARSE.getLocalPart(), "CDATA", "text");
        XIncludeAttributes xIncludeAttributes = new XIncludeAttributes(attributes);
    }
}
