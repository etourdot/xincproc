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
package org.etourdot.xincproc.xinclude.sax;

import org.etourdot.xincproc.xinclude.AbstractSuiteTest;
import org.junit.Test;

/**
 * @author Emmanuel Tourdot
 */
public class SaxEdUniTestSuiteTest extends AbstractSuiteTest {
    
    @Test
    public void sax_eduni_include_01() throws Exception
    {
        testsax(getClass().getClassLoader().getResource("eduni/test/book.xml"),
             getClass().getClassLoader().getResource("eduni/result/book.xml"));
    }

    @Test
    public void sax_eduni_include_02() throws Exception
    {
        testsax(getClass().getClassLoader().getResource("eduni/test/extract.xml"),
             getClass().getClassLoader().getResource("eduni/result/extract.xml"));
    }

    @Test
    public void sax_eduni_include_03() throws Exception
    {
        testsax(getClass().getClassLoader().getResource("eduni/test/lang.xml"),
             getClass().getClassLoader().getResource("eduni/result/lang.xml"));
    }
}
