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
import org.xml.sax.SAXException;

/**
 * @author Emmanuel Tourdot
 */
public class HaroldTestSuiteTest extends XIncProcSuiteTest {
    @Ignore("charactere escaping problem ?")
    public void harold_74() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("harold/test/UTF16BigEndianWithByteOrderMark.xml"),
                getClass().getClassLoader().getResource("harold/result/UTF16BigEndianWithByteOrderMark.xml"));
    }
    @Ignore("need xpointer evolution: ressource error if no scheme subresource")
    public void harold_92() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("harold/test/unrecognizedschemewithfallback.xml"),
                getClass().getClassLoader().getResource("harold/result/unrecognizedschemewithfallback.xml"));
    }
    @Ignore("xml base repeat problem")
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
    public void harold_37() throws Exception
    {
        testException(getClass().getClassLoader().getResource("harold/test/internalcircular.xml"),
                SAXException.class);
    }
}
