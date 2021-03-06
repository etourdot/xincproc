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

import org.junit.Test;
import javax.xml.namespace.QName;
import java.net.URI;

import static org.junit.Assert.*;

public class XIncProcUtilsTest {

    @Test
    public void testIsXInclude() throws Exception
    {
        QName test1 = XIncProcUtils.XINCLUDE_QNAME;
        QName test2 = new QName("", "include");
        QName test3 = new QName("http://toto/", "include");
        QName test4 = new QName("http://www.w3.org/2001/XInclude", "xinclude");
        assertTrue(XIncProcUtils.isXInclude(test1));
        assertTrue(XIncProcUtils.isXInclude(test2));
        assertFalse(XIncProcUtils.isXInclude(test3));
        assertFalse(XIncProcUtils.isXInclude(test4));
    }

    @Test
    public void testIsFallback() throws Exception
    {
        QName test1 = XIncProcUtils.FALLBACK_QNAME;
        QName test2 = new QName("", "fallback");
        QName test3 = new QName("http://toto/", "fallback");
        QName test4 = new QName("http://www.w3.org/2001/XInclude", "falback");
        assertTrue(XIncProcUtils.isFallback(test1));
        assertTrue(XIncProcUtils.isFallback(test2));
        assertFalse(XIncProcUtils.isFallback(test3));
        assertFalse(XIncProcUtils.isFallback(test4));
    }

    @Test
    public void testComputeBase() throws Exception
    {
        URI base = new URI("xinclude/");
        base = base.resolve(new URI("input-xinclude-recursive-2.xml"));
        assertEquals("xinclude/input-xinclude-recursive-2.xml", base.toASCIIString());
        base = base.resolve(new URI("para.xml"));
        assertEquals("xinclude/para.xml", base.toASCIIString());
    }
}
