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

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.etourdot.xinclude.XIncProcConfiguration;

import javax.xml.namespace.QName;

/**
 * @author Emmanuel Tourdot
 */
public class XPointerEngine
{
    public XPointer parse(final XPointerContext context) throws RecognitionException
    {
        final CharStream input = new ANTLRStringStream(context.getXPointer());
        final XPointerLexer lexer = new XPointerLexer(input);
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        XPointerParser parser = new XPointerParser(tokens, context);
        parser.pointer();
        return parser.getXPointer();
    }

    public PointerHandler createPointerPart(final String localName, final XPointerContext context)
    {
        final PointerHandler handler = new PointerPart(localName);
        handler.setContext(context);
        return handler;
    }

    public PointerHandler createPointerPart(final QName qname, final String schemeData, final XPointerContext context)
    {
        final PointerHandler handler =  new PointerPart(qname, schemeData);
        handler.setContext(context);
        return handler;
    }

}
