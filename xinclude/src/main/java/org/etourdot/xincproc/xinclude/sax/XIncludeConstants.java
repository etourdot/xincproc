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

package org.etourdot.xincproc.xinclude.sax;

import org.xml.sax.helpers.NamespaceSupport;

import javax.xml.namespace.QName;

/**
 * XIncludeConstants provides constants used by XIncProcXIncludeFilter.
 * This constants are only for internal purpose.
 */
final class XIncludeConstants {
    public static final QName XMLBASE_QNAME = new QName(NamespaceSupport.XMLNS, "base", "xml");
    public static final QName XMLLANG_QNAME = new QName(NamespaceSupport.XMLNS, "lang", "xml");
    static final QName ATT_PARSE = new QName("parse");
    static final QName ATT_XPOINTER = new QName("xpointer");
    static final QName ATT_ENCODING = new QName("encoding");
    static final QName ATT_HREF = new QName("href");
    static final QName ATT_ACCEPT = new QName("accept");
    static final QName ATT_ACCEPT_LANGUAGE = new QName("accept-language");
    static final String TEXT = "text";
    static final String XML = "xml";

    private XIncludeConstants()
    {
    }
}
