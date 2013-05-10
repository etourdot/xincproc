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

package org.etourdot.xincproc.xinclude.exceptions;

import org.xml.sax.SAXException;

/**
 * Exception thrown when XInclude engine encounter a resource error
 * See http://www.w3.org/TR/xinclude/#dt-error
 */
public class XIncludeResourceException extends SAXException
{
    /**
     * Instantiates a new XIncludeResourceException.
     *
     * @param nestedException the nested exception
     */
    public XIncludeResourceException(final Exception nestedException)
    {
        super(nestedException);
    }

    /**
     * Instantiates a new XIncludeResourceException.
     *
     * @param message the message
     */
    public XIncludeResourceException(final String message)
    {
        super(message);
    }
}
