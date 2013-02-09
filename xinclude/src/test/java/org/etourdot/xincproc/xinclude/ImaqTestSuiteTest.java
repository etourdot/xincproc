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

import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * @author Emmanuel Tourdot
 */
public class ImaqTestSuiteTest extends XIncProcSuiteTest {
    @Test
    public void imaq_include_xml_01() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("imaq/test/XInclude/docs/include.xml"),
                getClass().getClassLoader().getResource("imaq/result/XInclude/include.xml"));
    }

    @Ignore
    public void imaq_include_xml_02() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("imaq/test/XInclude/docs/recursive.xml"),
                getClass().getClassLoader().getResource("imaq/result/XInclude/recursive.xml"));
    }

    @Ignore
    public void imaq_include_xml_03() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("imaq/test/XInclude/docs/nodes.xml"),
                getClass().getClassLoader().getResource("imaq/result/XInclude/nodes.xml"));
    }

    @Ignore
    public void imaq_include_xml_04() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("imaq/test/XInclude/docs/docids.xml"),
                getClass().getClassLoader().getResource("imaq/result/XInclude/docids.xml"));
    }

    @Ignore
    public void imaq_include_xml_05() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("imaq/test/XInclude/docs/txtinclude.xml"),
                getClass().getClassLoader().getResource("imaq/result/XInclude/txtinclude.xml"));
    }

    @Ignore
    public void imaq_include_xml_06() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("imaq/test/XInclude/docs/fallback.xml"),
                getClass().getClassLoader().getResource("imaq/result/XInclude/fallback.xml"));
    }

    @Ignore
    public void imaq_pex1_01() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("imaq/test/XInclude/docs/pex1.xml"),
                getClass().getClassLoader().getResource("imaq/result/XInclude/include.xml"));
    }

    @Ignore
    public void imaq_pex1_02() throws Exception
    {
        testException(getClass().getClassLoader().getResource("imaq/test/XInclude/docs/pex1a.xml"),
                SAXException.class);
    }

    @Ignore
    public void imaq_pex6_03() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("imaq/test/XInclude/docs/pex6a.xml"),
                getClass().getClassLoader().getResource("imaq/result/XInclude/pex6a.xml"));
    }

    @Ignore
    public void imaq_pex6_04() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("imaq/test/XInclude/docs/pex6b.xml"),
                getClass().getClassLoader().getResource("imaq/result/XInclude/pex6b.xml"));
    }

    @Ignore
    public void imaq_pex11_05() throws Exception
    {
        testException(getClass().getClassLoader().getResource("imaq/test/XInclude/docs/pex11.xml"),
                SAXException.class);
    }
}