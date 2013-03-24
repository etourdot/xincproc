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

import net.sf.saxon.s9api.Processor;
import org.etourdot.xincproc.xpointer.XPointerEngine;
import org.xml.sax.helpers.NamespaceSupport;

import javax.xml.namespace.QName;
import javax.xml.transform.ErrorListener;

/**
 * @author Emmanuel Tourdot
 */
public class XIncProcConfiguration {
    public static final String ALLOW_FIXUP_BASE_URIS
            = "http://etourdot.org/xml/features/xinclude/fixup-base-uris";
    public static final String ALLOW_FIXUP_LANGUAGE
            = "http://etourdot.org/xml/features/xinclude/fixup-language";
    public static final String XINCLUDE_NAMESPACE_URI = "http://www.w3.org/2001/XInclude";
    public static final QName XINCLUDE_QNAME = new QName(XIncProcConfiguration.XINCLUDE_NAMESPACE_URI, "include", "xi");
    public static final QName FALLBACK_QNAME = new QName(XIncProcConfiguration.XINCLUDE_NAMESPACE_URI, "fallback", "xi");
    public static final QName XMLBASE_QNAME = new QName(NamespaceSupport.XMLNS, "base", "xml");
    public static final QName XMLLANG_QNAME = new QName(NamespaceSupport.XMLNS, "lang", "xml");

    private boolean baseUrisFixup = true;
    private boolean languageFixup = true;
    private final Processor processor;

    private XIncProcConfiguration()
    {
        this.processor = new Processor(false);
    }

    public static XIncProcConfiguration newXIncProcConfiguration()
    {
        return new XIncProcConfiguration();
    }

    public XPointerEngine newXPointerEngine()
    {
        return new XPointerEngine(this.processor);
    }

    public void setConfigurationProperty(final String name, final Object value)
    {
        if (XIncProcConfiguration.ALLOW_FIXUP_BASE_URIS.equals(name))
        {
            if (value instanceof Boolean)
            {
                this.baseUrisFixup = (Boolean) value;
            }
            else if (value instanceof String)
            {
                this.baseUrisFixup = Boolean.valueOf((String) value);
            }
        }
        if (XIncProcConfiguration.ALLOW_FIXUP_LANGUAGE.equals(name))
        {
            if (value instanceof Boolean)
            {
                this.languageFixup = (Boolean) value;
            }
            else if (value instanceof String)
            {
                this.languageFixup = Boolean.valueOf((String) value);
            }
        }
    }

    public void setErrorListener(final ErrorListener errorListener)
    {
        this.processor.getUnderlyingConfiguration().setErrorListener(errorListener);
    }

    public boolean isBaseUrisFixup()
    {
        return this.baseUrisFixup;
    }

    public boolean isLanguageFixup()
    {
        return this.languageFixup;
    }

    public Processor getProcessor()
    {
        return this.processor;
    }
}
