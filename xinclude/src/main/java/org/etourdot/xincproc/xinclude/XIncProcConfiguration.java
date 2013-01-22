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

import net.sf.saxon.s9api.Processor;
import org.etourdot.xincproc.xpointer.XPointerEngine;
import org.xml.sax.helpers.NamespaceSupport;

import javax.xml.namespace.QName;
import javax.xml.transform.ErrorListener;

/**
 * @author Emmanuel Tourdot
 */
public class XIncProcConfiguration
{
    public static final String XINCLUDE_FIXUP_BASE_URIS_FEATURE_ID
            = "http://etourdot.org/xml/features/xinclude/fixup-base-uris";
    public static final String XINCLUDE_FIXUP_LANGUAGE_FEATURE_ID
            = "http://etourdot.org/xml/features/xinclude/fixup-language";
    public static final String XINCLUDE_NAMESPACE_URI = "http://www.w3.org/2001/XInclude";
    public static final String XSL_NAMESPACE_URI = "http://www.w3.org/1999/XSL/Transform";
    public static final QName XINCLUDE_QNAME = new QName(XINCLUDE_NAMESPACE_URI, "include", "xi");
    public static final QName FALLBACK_QNAME = new QName(XINCLUDE_NAMESPACE_URI, "fallback", "xi");
    public static final QName XMLBASE_QNAME = new QName(NamespaceSupport.XMLNS, "base", "xml");
    public static final QName XMLLANG_QNAME = new QName(NamespaceSupport.XMLNS, "lang", "xml");
    public static final QName ATT_PARSE = new QName("parse");
    public static final QName ATT_XPOINTER = new QName("xpointer");
    public static final QName ATT_ENCODING = new QName("encoding");
    public static final QName ATT_HREF = new QName("href");
    public static final QName ATT_ACCEPT = new QName("accept");
    public static final QName ATT_ACCEPT_LANGUAGE = new QName("accept-language");
    public static final String TEXT = "text";
    public static final String XML = "xml";
    
    private boolean baseUrisFixup = true;
    private boolean languageFixup = true;
    private String xincfactory;
    private final Processor processor;
    private XPointerEngine xPointerEngine;

    public XIncProcConfiguration()
    {
        this.processor = new Processor(false);
    }

    public XIncProcConfiguration(final Processor processor)
    {
        this.processor = processor;
    }

    public XPointerEngine getXPointerEngine()
    {
        if (xPointerEngine == null)
        {
            xPointerEngine = new XPointerEngine();
        }
        return xPointerEngine;
    }

    public void setConfigurationProperty(final String name, final Object value)
    {
        if (XINCLUDE_FIXUP_BASE_URIS_FEATURE_ID.equals(name))
        {
            if (value instanceof Boolean)
            {
                baseUrisFixup = (Boolean) value;
            }
            else if (value instanceof String)
            {
                baseUrisFixup = Boolean.getBoolean((String) value);
            }
        }
        if (XINCLUDE_FIXUP_LANGUAGE_FEATURE_ID.equals(name))
        {
            if (value instanceof Boolean)
            {
                languageFixup = (Boolean) value;
            }
            else if (value instanceof String)
            {
                languageFixup = Boolean.getBoolean((String) value);
            }
        }
    }

    public Object getConfigurationProperty(final String name)
    {
        if (XINCLUDE_FIXUP_BASE_URIS_FEATURE_ID.equals(name))
        {
            return baseUrisFixup;
        }
        if (XINCLUDE_FIXUP_LANGUAGE_FEATURE_ID.equals(name))
        {
            return languageFixup;
        }
        return null;
    }

    public void setErrorListener(final ErrorListener errorListener)
    {
        getProcessor().getUnderlyingConfiguration().setErrorListener(errorListener);
    }

    public boolean isBaseUrisFixup() {
        return this.baseUrisFixup;
    }

    public void setBaseUrisFixup(final boolean baseUrisFixup)
    {
        this.baseUrisFixup = baseUrisFixup;
    }

    public boolean isLanguageFixup() {
        return this.languageFixup;
    }

    public void setLanguageFixup(final boolean languageFixup)
    {
        this.languageFixup = languageFixup;
    }

    public Processor getProcessor()
    {
        return processor;
    }
}
