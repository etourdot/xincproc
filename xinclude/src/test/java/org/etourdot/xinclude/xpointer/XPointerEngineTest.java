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
package org.etourdot.xinclude.xpointer;

import junit.framework.TestCase;
import net.sf.saxon.s9api.Axis;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmSequenceIterator;
import net.sf.saxon.s9api.XdmValue;
import org.etourdot.xinclude.XIncProcConfiguration;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringWriter;
import java.util.Iterator;

/**
 * @author Emmanuel Tourdot
 */
public class XPointerEngineTest
{
    private XIncProcConfiguration configuration = new XIncProcConfiguration();
    private XPointerEngine engine = new XPointerEngine();

    @Test
    public void testParseId() throws Exception
    {
        String xpt = "element(id_test1)";
        XPointerContext context = new XPointerContext(xpt, configuration);
        XPointer pointer = engine.parse(context);
        assertNotNull(pointer);
        StringWriter writer = new StringWriter();
        pointer.setContext(new XPointerContext(xpt, configuration));
        Source source = new StreamSource(new File(getClass().getClassLoader().getResource("xpointer.xml").getPath()));
        XdmValue value = pointer.getXdmValue(source);
        assertEquals(2, getCountChildren(value));
        xpt = "id_test2";
        context = new XPointerContext(xpt, configuration);
        pointer = engine.parse(context);
        assertNotNull(pointer);
        writer = new StringWriter();
        pointer.setContext(new XPointerContext(xpt, configuration));
        value = pointer.getXdmValue(source);
        assertEquals(1, getCountChildren(value));
    }

    @Test
    public void testParseXpath() throws Exception
    {
        String xpt = "xpath(/test/test2/p[2])";
        XPointerContext context = new XPointerContext(xpt, configuration);
        XPointer pointer = engine.parse(context);
        assertNotNull(pointer);
        pointer.setContext(new XPointerContext(xpt, configuration));
        Source source = new StreamSource(new File(getClass().getClassLoader().getResource("xpointer.xml").getPath()));
        XdmValue value = pointer.getXdmValue(source);
        assertEquals(1, getCountChildren(value));
    }

    @Test
    public void testParseXpointer() throws Exception
    {
        String xpt = "xpointer(/test//p)";
        XPointerContext context = new XPointerContext(xpt, configuration);
        XPointer pointer = engine.parse(context);
        assertNotNull(pointer);
        pointer.setContext(new XPointerContext(xpt, configuration));
        Source source = new StreamSource(new File(getClass().getClassLoader().getResource("xpointer.xml").getPath()));
        XdmValue value = pointer.getXdmValue(source);
        assertEquals(3, getCountChildren(value));
    }

    private int getCountChildren(final XdmValue value)
    {
        final XdmSequenceIterator iterator = XPointer.getChildrenIterator(value);
        int countChildren = 0;
        while (iterator.hasNext())
        {
            countChildren++;
            iterator.next();
        }
        return countChildren;
    }
}
