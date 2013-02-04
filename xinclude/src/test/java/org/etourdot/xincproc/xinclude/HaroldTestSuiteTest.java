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

/**
 * @author Emmanuel Tourdot
 */
public class HaroldTestSuiteTest extends XIncProcSuiteTest {
    @Ignore
    public void harold_01() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("harold/test/xmlbasetest.xml"),
                getClass().getClassLoader().getResource("harold/result/xmlbasetest.xml"));
    }
    @Ignore
    public void harold_06() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("harold/test/includedocumentwithintradocumentreferences.xml"),
                getClass().getClassLoader().getResource("harold/result/includedocumentwithintradocumentreferences.xml"));
    }
    @Ignore("xml base repeat problem")
    public void harold_10() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("harold/test/marshtestwithxmlbase.xml"),
                getClass().getClassLoader().getResource("harold/result/marshtestwithxmlbase.xml"));
    }
    @Ignore("xml base repeat problem")
    public void harold_11() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("harold/test/marshtestwithxmlbaseandemptyhref.xml"),
                getClass().getClassLoader().getResource("harold/result/marshtestwithxmlbaseandemptyhref.xml"));
    }
    @Ignore
    public void harold_21() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("harold/test/metafallbacktest6.xml"),
                getClass().getClassLoader().getResource("harold/result/metafallbacktest6.xml"));
    }
    @Ignore
    public void harold_22() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("harold/test/metafallbacktest2.xml"),
                getClass().getClassLoader().getResource("harold/result/metafallbacktest2.xml"));
    }
    @Ignore
    public void harold_55() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("harold/test/fallbacktest2.xml"),
                getClass().getClassLoader().getResource("harold/result/fallbacktest2.xml"));
    }
    @Ignore
    public void harold_66() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("harold/test/xptrtumblerfailsbutfallback.xml"),
                getClass().getClassLoader().getResource("harold/result/xptrtumblerfailsbutfallback.xml"));
    }
    @Ignore
    public void harold_72() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("harold/test/xptrfallback.xml"),
                getClass().getClassLoader().getResource("harold/result/xptrfallback.xml"));
    }
    @Ignore
    public void harold_76() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("harold/test/UTF8WithByteOrderMark.xml"),
                getClass().getClassLoader().getResource("harold/result/UTF8WithByteOrderMark.xml"));
    }
    @Ignore("need xpointer evolution: ressource error if no scheme subresource")
    public void harold_92() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("harold/test/unrecognizedschemewithfallback.xml"),
                getClass().getClassLoader().getResource("harold/result/unrecognizedschemewithfallback.xml"));
    }
}
