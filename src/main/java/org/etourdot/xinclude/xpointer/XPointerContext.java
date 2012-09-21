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
package org.etourdot.xinclude.xpointer;

import org.etourdot.xinclude.XIncProcConfiguration;
import org.xml.sax.helpers.NamespaceSupport;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Emmanuel Tourdot
 */
public final class XPointerContext implements NamespaceContext
{
    private final Map<String, String> namespaces = new HashMap<String, String>();
    private final String xPointer;
    private final XIncProcConfiguration configuration;

    public XPointerContext(final String xPointer, final XIncProcConfiguration configuration)
    {
        this.xPointer = xPointer;
        this.configuration = configuration;
    }

    public String getXPointer()
    {
        return this.xPointer;
    }

    public Map<String, String> getNamespaces()
    {
        return this.namespaces;
    }

    public void addNamespace(final QName qname)
    {
        namespaces.put(qname.getPrefix(), qname.getNamespaceURI());
    }

    public void addPrefix(final String prefix, final String namespaceURI)
    {
        if (NamespaceSupport.XMLNS.equals(namespaceURI) ||
            NamespaceSupport.NSDECL.equals(namespaceURI) ||
            XIncProcConfiguration.XSL_NAMESPACE_URI.equals(namespaceURI))
        {
            return;
        }

        this.namespaces.put(prefix, namespaceURI);
    }

    public String getPrefix(final String namespaceURI)
    {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    public Iterator getPrefixes(final String namespaceURI)
    {
        throw new UnsupportedOperationException();
    }

    public String getNamespaceURI(final String prefix)
    {
        return this.namespaces.get(prefix);
    }

    public XIncProcConfiguration getConfiguration()
    {
        return configuration;
    }
}
