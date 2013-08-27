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

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.tree.Tree;
import org.antlr.runtime.tree.TreeNodeStream;
import org.antlr.runtime.tree.TreeParser;
import org.etourdot.xincproc.fragid.FragIdErrorHandler;

/**
 * Abstract parent class for grammar tree parser
 * This internal is useful to intercept actions in main tree parser class
 * generated via Antlr
 */
abstract class AbstractFragIdTree extends TreeParser implements ErrorHandling
{
    private FragIdErrorHandler fragIdErrorHandler;

    AbstractFragIdTree(final TreeNodeStream input)
    {
        super(input);
    }

    AbstractFragIdTree(final TreeNodeStream input, final RecognizerSharedState state)
    {
        super(input, state);
    }

    @Override
    public void setErrorHandler(final FragIdErrorHandler fragIdErrorHandler)
    {
        this.fragIdErrorHandler = fragIdErrorHandler;
    }

    private boolean isUpdatingRecoveryState()
    {
        final boolean updatingRecoveryState;
        if (this.state.errorRecovery)
        {
            updatingRecoveryState = true;
        }
        else
        {
            this.state.syntaxErrors++;
            this.state.errorRecovery = true;
            updatingRecoveryState = false;
        }
        return updatingRecoveryState;
    }

    @Override
    public void displayRecognitionError(final String[] tokenNames, final RecognitionException e)
    {
        final String msg = getErrorMessage(e, tokenNames);
        emitErrorMessage(msg);
    }

    @Override
    public void emitErrorMessage(final String msg)
    {
        super.emitErrorMessage(msg);
    }
}
