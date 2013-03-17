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

import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Emmanuel Tourdot
 */
public class NistTestSuiteTest extends XIncProcSuiteTest {
    @Ignore
    public void nist_include_01() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-01.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-01.xml"));
    }

    @Ignore
    public void nist_include_02() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-02.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-02.xml"));
    }

    @Ignore
    public void nist_include_03() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-03.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_04() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-04.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-04.xml"));
    }

    @Ignore
    public void nist_include_05() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-05.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_06() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-06.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-06.xml"));
    }

    @Ignore
    public void nist_include_07() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-07.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-07.xml"));
    }

    @Ignore
    public void nist_include_08() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-08.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_09() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-09.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-09.xml"));
    }

    @Ignore
    public void nist_include_10() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-10.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-10.xml"));
    }

    @Ignore
    public void nist_include_11() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-11.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_12() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-12.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_13() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-13.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-13.xml"));
    }

    @Ignore
    public void nist_include_14() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-14.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-14.xml"));
    }

    @Test
    public void nist_include_15() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-15.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_16() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-16.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-16.xml"));
    }

    @Ignore
    public void nist_include_17() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-17.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-17.xml"));
    }

    @Ignore
    public void nist_include_18() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-18.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-18.xml"));
    }

    @Ignore
    public void nist_include_19() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-19.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-19.xml"));
    }

    @Ignore
    public void nist_include_20() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-20.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-20.xml"));
    }

    @Test
    public void nist_include_21() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-21.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-21.xml"));
    }

    @Ignore
    public void nist_include_22() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-22.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-22.xml"));
    }

    @Ignore
    public void nist_include_23() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-23.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_24() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-24.xml"),
                XIncludeFatalException.class);
    }

    @Test
    public void nist_include_25() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-25.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_26() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-26.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-26.xml"));
    }

    @Ignore
    public void nist_include_27() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-27.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-27.xml"));
    }

    @Ignore
    public void nist_include_28() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-28.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-28.xml"));
    }

    @Ignore
    public void nist_include_29() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-29.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-29.xml"));
    }

    @Ignore
    public void nist_include_30() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-30.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-30.xml"));
    }

    @Ignore
    public void nist_include_31() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-31.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-31.xml"));
    }

    @Ignore
    public void nist_include_32() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-32.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_33() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-33.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_34() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-34.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-34.xml"));
    }

    @Ignore
    public void nist_include_35() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-35.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-35.xml"));
    }

    @Ignore
    public void nist_include_36() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-36.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-36.xml"));
    }

    @Ignore
    public void nist_include_37() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-37.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-37.xml"));
    }

    @Ignore
    public void nist_include_38() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-38.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-38.xml"));
    }

    @Ignore
    public void nist_include_39() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-39.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-39.xml"));
    }

    @Ignore
    public void nist_include_40() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-40.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-40.xml"));
    }

    @Ignore
    public void nist_include_41() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-41.xml"),
                XIncludeFatalException.class);
    }

    @Test
    public void nist_include_42() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-42.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_43() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-43.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_44() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-44.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_45() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-45.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_46() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-46.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_47() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-47.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_48() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-48.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_49() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-49.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-49.xml"));
    }

    @Ignore
    public void nist_include_50() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-50.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-50.xml"));
    }

    @Test
    public void nist_include_51() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-51.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-51.xml"));
    }

    @Ignore
    public void nist_include_52() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-52.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-52.xml"));
    }

    @Ignore
    public void nist_include_53() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-53.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_54() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-54.xml"),
                XIncludeFatalException.class);
    }

    @Ignore
    public void nist_include_55() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("nist/test/docs/nist-include-55.xml"),
                getClass().getClassLoader().getResource("nist/result/nist-include-55.xml"));
    }

    @Test
    public void nist_include_56() throws Exception
    {
        testException(getClass().getClassLoader().getResource("nist/test/docs/nist-include-56.xml"),
                XIncludeFatalException.class);
    }
}
