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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 17/01/13
 * Time: 09:42
 */
public class XIncProcErrorListener implements ErrorListener {
    private static final Logger LOG = LoggerFactory.getLogger(XIncProcErrorListener.class);

    @Override
    public void warning(final TransformerException exception)
            throws TransformerException
    {
        LOG.debug("warning");
    }

    @Override
    public void error(final TransformerException exception)
            throws TransformerException
    {
        LOG.debug("error");
    }

    @Override
    public void fatalError(final TransformerException exception)
            throws TransformerException
    {
        LOG.debug("fatalError");
    }
}
