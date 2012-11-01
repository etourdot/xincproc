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
package org.etourdot.xinclude;

import org.etourdot.xinclude.xpointer.PointerHandler;
import org.etourdot.xinclude.xpointer.XPointerContext;

import javax.xml.namespace.QName;

/**
 * @author Emmanuel Tourdot
 */
public interface XIncProcFactory
{
    XIncProcConfiguration getConfiguration();
    
    XIncProcEngineHandler newEngine();

    PointerHandler createPointerPart(final String localName, final XPointerContext context);

    PointerHandler createPointerPart(final QName qname, final String schemeData, final XPointerContext context);
}
