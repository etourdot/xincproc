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
package org.etourdot.xincproc.xinclude.stax;

import org.etourdot.xincproc.xinclude.XIncProcConfiguration;
import org.etourdot.xincproc.xinclude.XIncProcEngineHandler;
import org.etourdot.xincproc.xinclude.XIncProcFactory;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

/**
 * @author Emmanuel Tourdot
 */
public final class XIncProcStaxFactory implements XIncProcFactory
{
    private final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    private final XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
    private final XMLEventFactory xmlEventFactory = XMLEventFactory.newInstance();
    private final XIncProcConfiguration configuration;

    public XIncProcStaxFactory(XIncProcConfiguration configuration) {
        this.configuration = configuration;
    }

    public XIncProcConfiguration getConfiguration()
    {
        return configuration;
    }

    @Override
    public XIncProcEngineHandler newEngine()
    {
        return new XIncludeStaxEngine(this);

    }

    public XMLEventFactory getXmlEventFactory()
    {
        return xmlEventFactory;
    }

    public XMLOutputFactory getXmlOutputFactory()
    {
        return xmlOutputFactory;
    }

    public XMLInputFactory getXmlInputFactory() 
    {
        return xmlInputFactory;
    }
}
