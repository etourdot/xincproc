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

import org.antlr.runtime.*;
import org.etourdot.xincproc.fragid.FragIdErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract parent class for grammar parser
 * This internal is useful to intercept actions in main parser class
 * generated via Antlr
 */
abstract class AbstractFragIdParser extends Parser implements ErrorHandling
{
    private FragIdErrorHandler fragIdErrorHandler;

    AbstractFragIdParser(final TokenStream input)
    {
        super(input);
    }

    AbstractFragIdParser(final TokenStream input, final RecognizerSharedState state)
    {
        super(input, state);
    }

    @Override
    public void setErrorHandler(final FragIdErrorHandler fragIdErrorHandler)
    {
        this.fragIdErrorHandler = fragIdErrorHandler;
    }

    @Override
    public void emitErrorMessage(final String msg)
    {
        if (null == this.fragIdErrorHandler)
        {
            super.emitErrorMessage(msg);
        }
        else
        {
            this.fragIdErrorHandler.reportError(msg);
        }
    }

    @Override
    protected Object recoverFromMismatchedToken(final IntStream input, final int ttype, final BitSet follow)
            throws RecognitionException
    {
        throw new MismatchedTokenException(ttype, input);
    }
}
