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

package org.etourdot.xincproc.xpointer.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 21/10/12
 * Time: 23:16
 */
public class XPointerException extends Exception {

    public XPointerException(final Throwable cause) {
        super(cause);
    }

    public XPointerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public XPointerException(final String message) {
        super(message);
    }
}
