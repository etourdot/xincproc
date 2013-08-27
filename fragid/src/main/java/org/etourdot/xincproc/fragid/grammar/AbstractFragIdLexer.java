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

package org.etourdot.xincproc.fragid.grammar;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognizerSharedState;

/**
 * Abstract parent class for grammar lexer
 * This internal is useful to intercept actions in main lexer class
 * generated via Antlr
 */
abstract class AbstractFragIdLexer extends Lexer
{
    AbstractFragIdLexer()
    {
    }

    AbstractFragIdLexer(final CharStream input)
    {
        this(input, new RecognizerSharedState());
    }

    AbstractFragIdLexer(final CharStream input, final RecognizerSharedState state)
    {
        super(input, state);
    }
}
