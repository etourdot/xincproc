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

import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.junit.Test;

import java.net.URI;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class XIncProcEngineTest {
    @Test
    public void testResolveBase() throws Exception
    {
        Stack<URI> stack = new Stack<URI>();
        stack.add(new URI("xinclude2.xml"));
        stack.add(new URI("xinclude3.xml"));
        URI resultUri = XIncProcUtils.resolveBase(new URI("xinclude1.xml"), stack);
        assertEquals(new URI("xinclude3.xml"), resultUri);
        resultUri = XIncProcUtils.resolveBase(new URI("xinclude2.xml"), stack);
        assertEquals(new URI("xinclude3.xml"), resultUri);
        try
        {
            resultUri = XIncProcUtils.resolveBase(new URI("xinclude3.xml"), stack);
        }
        catch (XIncludeFatalException e)
        {
            assertEquals("Inclusion loop error", e.getMessage());
        }
        stack.add(new URI("docs/xinclude4.xml"));
        resultUri = XIncProcUtils.resolveBase(new URI("test/xinclude1.xml"), stack);
        assertEquals(new URI("test/docs/xinclude4.xml"), resultUri);
        stack = new Stack<URI>();
        stack.add(new URI("xinclude3.xml"));
        stack.add(new URI("xinclude/xinclude2.xml"));
        stack.add(new URI("para.xml"));
        resultUri = XIncProcUtils.resolveBase(new URI("para.xml"), stack);
        assertEquals(new URI("xinclude/para.xml"), resultUri);

    }
}
