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

package org.etourdot.xincproc.xinclude.api;

import org.concordion.api.ExpectedToFail;
import org.concordion.integration.junit4.ConcordionRunner;
import org.etourdot.xincproc.xinclude.XIncProcEngine;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.junit.runner.RunWith;
import org.xml.sax.InputSource;

import javax.xml.transform.Result;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 03/11/12
 * Time: 12:08
 */
@RunWith(ConcordionRunner.class)
@ExpectedToFail
public class ApiTest {
    public String execute(String source) throws XIncludeFatalException
    {
        XIncProcEngine xIncProcEngine = new XIncProcEngine();
        SAXSource saxSource = new SAXSource(new InputSource(new StringReader(source)));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Result outputResult = new StreamResult(baos);
        xIncProcEngine.parse(saxSource, outputResult);
        return new String(baos.toByteArray());
    }
}
