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
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

public class XProcTestSuiteTest extends XIncProcSuiteTest {
    @Test
    public void xproc_include_loop() throws Exception
    {
        testException(getClass().getClassLoader().getResource("xproc/test/input-xinclude-loop-source.xml"),
                XIncludeFatalException.class, false, false);
    }

    @Test
    public void xproc_include_01() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("xproc/test/xinclude-001.xml"),
                getClass().getClassLoader().getResource("xproc/result/xinclude-001.xml"),
                false, false);
    }

    @Test
    public void xproc_include_02() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("xproc/test/xinclude-002.xml"),
                getClass().getClassLoader().getResource("xproc/result/xinclude-002.xml"), true, false);
    }

    @Test
    public void xproc_include_03() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("xproc/test/xinclude-003.xml"),
                getClass().getClassLoader().getResource("xproc/result/xinclude-003.xml"), false, false);
    }

    //@Ignore("Unidentified maven encoding problem")
    @Test
    public void xproc_include_04() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("xproc/test/input-en-cs.xml"),
                getClass().getClassLoader().getResource("xproc/result/xinclude-004.xml"), false, true);
    }

    //@Ignore("Unidentified maven encoding problem")
    @Test
    public void xproc_include_05() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("xproc/test/input-en-cs.xml"),
                getClass().getClassLoader().getResource("xproc/result/xinclude-005.xml"), false, false);
    }

    @Test
    public void xproc_include_opt_01() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("xproc/test/xinclude-o001.xml"),
                getClass().getClassLoader().getResource("xproc/result/xinclude-o001.xml"), false, false);
    }

    @Test
    public void xproc_include_opt_02() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("xproc/test/xinclude-o002.xml"),
                getClass().getClassLoader().getResource("xproc/result/xinclude-o002.xml"), false, false);
    }

    @Test
    public void xproc_include_opt_03() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("xproc/test/xinclude-o003.xml"),
                getClass().getClassLoader().getResource("xproc/result/xinclude-o003.xml"), false, false);
    }

    @Test
    public void xproc_include_opt_04() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("xproc/test/xinclude-o004.xml"),
                getClass().getClassLoader().getResource("xproc/result/xinclude-o004.xml"), false, false);
    }

    @Test
    public void xproc_include_opt_05() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("xproc/test/xinclude-o005.xml"),
                getClass().getClassLoader().getResource("xproc/result/xinclude-o005.xml"), false, false);
    }

    @Test
    public void xproc_include_opt_06() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("xproc/test/xinclude-o006.xml"),
                getClass().getClassLoader().getResource("xproc/result/xinclude-o006.xml"), false, false);
    }
}
