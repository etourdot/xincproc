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

package org.etourdot.xincproc.xpointer.model;

import com.google.common.base.Strings;
import org.etourdot.xincproc.xpointer.exceptions.ElementSchemeException;

import javax.xml.namespace.QName;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 15:48
 */
public class PointerFactory {
    public ElementScheme createElementScheme(final String name, final String data)
    {
        try
        {
            return new ElementScheme(name, data);
        }
        catch (final ElementSchemeException e)
        {
            return null;
        }
    }

    public ShortHand createShortHand(final String name)
    {
        return new ShortHand(name);
    }

    public XmlNsScheme createXmlNsScheme(final String localpart, final String uri)
    {
        if (!Strings.isNullOrEmpty(localpart))
        {
            return createXmlNsScheme(new QName(uri, localpart));
        }
        else
        {
            return null;
        }
    }

    public XmlNsScheme createXmlNsScheme(final QName qName)
    {
        return new XmlNsScheme(qName);
    }

    public XPathScheme createXPathScheme(final String xpath)
    {
        return new XPathScheme(xpath);
    }

    public XPointerScheme createXPointerScheme(final String xpath)
    {
        return new XPointerScheme(xpath);
    }

    public OtherScheme createOtherScheme(final QName qName, final String data)
    {
        return new OtherScheme(qName, data);
    }
}
