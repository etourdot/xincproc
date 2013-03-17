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
package org.etourdot.xincproc.xinclude;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Emmanuel Tourdot
 */
public class EdUniTestSuiteTest extends XIncProcSuiteTest {
    @Ignore
    public void eduni_include_01() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("eduni/test/book.xml"),
                getClass().getClassLoader().getResource("eduni/result/book.xml"));
    }

    @Ignore
    public void eduni_include_02() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("eduni/test/extract.xml"),
                getClass().getClassLoader().getResource("eduni/result/extract.xml"));
    }

    @Test
    public void eduni_include_03() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("eduni/test/lang.xml"),
                getClass().getClassLoader().getResource("eduni/result/lang.xml"));
    }
}
