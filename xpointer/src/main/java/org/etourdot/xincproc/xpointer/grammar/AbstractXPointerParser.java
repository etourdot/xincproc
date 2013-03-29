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

package org.etourdot.xincproc.xpointer.grammar;

import org.antlr.runtime.*;
import org.etourdot.xincproc.xpointer.XPointerErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract parent class for grammar parser
 * This internal is useful to intercept actions in main parser class
 * generated via Antlr
 */
abstract class AbstractXPointerParser extends Parser implements ErrorHandling {
    protected static final Logger log = LoggerFactory.getLogger(AbstractXPointerParser.class);
    private XPointerErrorHandler xPointerErrorHandler;

    AbstractXPointerParser(final TokenStream input)
    {
        super(input);
    }

    AbstractXPointerParser(final TokenStream input, final RecognizerSharedState state)
    {
        super(input, state);
    }

    @Override
    public void setErrorHandler(final XPointerErrorHandler xPointerErrorHandler)
    {
        this.xPointerErrorHandler = xPointerErrorHandler;
    }

    void reportOtherSchemeError(final RecognitionException re)
    {
        if (re instanceof MismatchedTokenException)
        {
            emitErrorMessage("Warning: unknown scheme '" + ((MismatchedTokenException) re).token.getText() + '\'');
        }
    }

    void reportElementSchemeError(final RecognitionException re)
    {
        if (re instanceof NoViableAltException)
        {
            emitErrorMessage("Warning: unknown element scheme data '" + ((NoViableAltException) re).token.getText() + '\'');
        }
    }

    @Override
    public void emitErrorMessage(final String msg)
    {
        log.debug("emitErrorMessage '{}'", msg);
        if (null == this.xPointerErrorHandler)
        {
            super.emitErrorMessage(msg);
        }
        else
        {
            this.xPointerErrorHandler.reportError(msg);
        }
    }

    @Override
    protected Object recoverFromMismatchedToken(final IntStream input, final int ttype, final BitSet follow)
            throws RecognitionException
    {
        throw new MismatchedTokenException(ttype, input);
    }
}
