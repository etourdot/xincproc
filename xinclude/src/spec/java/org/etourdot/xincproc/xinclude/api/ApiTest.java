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

package org.etourdot.xincproc.xinclude.api;

import org.concordion.integration.junit4.ConcordionRunner;
import org.etourdot.xincproc.xinclude.XIncProcEngine;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.junit.runner.RunWith;

import java.io.*;
import java.net.URL;

@RunWith(ConcordionRunner.class)
public class ApiTest {
    public String execute() throws XIncludeFatalException, IOException
    {
        final URL urlInput = getClass().getClassLoader().getResource("org/etourdot/xincproc/xinclude/api/ft-include1.xml");
        final FileInputStream source = new FileInputStream(urlInput.getPath());
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XIncProcEngine.parse(source, urlInput.toExternalForm(), baos);
        return new String(baos.toByteArray());
    }
}
