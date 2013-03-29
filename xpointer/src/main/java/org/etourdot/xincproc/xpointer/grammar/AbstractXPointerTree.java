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

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.tree.Tree;
import org.antlr.runtime.tree.TreeNodeStream;
import org.antlr.runtime.tree.TreeParser;
import org.etourdot.xincproc.xpointer.XPointerErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract parent class for grammar tree parser
 * This internal is useful to intercept actions in main tree parser class
 * generated via Antlr
 */
class AbstractXPointerTree extends TreeParser implements ErrorHandling {
    static final Logger LOG = LoggerFactory.getLogger(AbstractXPointerTree.class);
    private XPointerErrorHandler xPointerErrorHandler;

    AbstractXPointerTree(final TreeNodeStream input)
    {
        super(input);
    }

    AbstractXPointerTree(final TreeNodeStream input, final RecognizerSharedState state)
    {
        super(input, state);
    }

    @Override
    public void setErrorHandler(final XPointerErrorHandler xPointerErrorHandler)
    {
        this.xPointerErrorHandler = xPointerErrorHandler;
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

    void reportPointerError(final RecognitionException e)
    {
        if (!isUpdatingRecoveryState())
        {
            displayPointerError(this.getTokenNames(), e);
        }
    }

    void reportPointerPartError(final RecognitionException e)
    {
        if (!isUpdatingRecoveryState())
        {
            displayPointerPartError(this.getTokenNames(), e);
        }
    }

    void reportElementSchemeDataError(final RecognitionException e)
    {
        if (!isUpdatingRecoveryState())
        {
            displayElementSchemeDataError(this.getTokenNames(), e);
        }
    }

    private void displayPointerPartError(final String[] tokenNames, final RecognitionException e)
    {
        final String msg = "Warning: bad pointerpart form '" + ((Tree) e.node).getText() + '\'';
        emitErrorMessage(msg);
    }

    private void displayPointerError(final String[] tokenNames, final RecognitionException e)
    {
        final String msg = "Warning: bad pointer form '" + ((Tree) e.node).getText() + '\'';
        emitErrorMessage(msg);
    }

    private void displayElementSchemeDataError(final String[] tokenNames, final RecognitionException e)
    {
        final String msg = "Warning: bad element scheme data '" + ((Tree) e.node).getText() + '\'';
        emitErrorMessage(msg);
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
        LOG.debug("emitErrorMessage '{}'", msg);
        if (null == this.xPointerErrorHandler)
        {
            super.emitErrorMessage(msg);
        }
        else
        {
            this.xPointerErrorHandler.reportError(msg);
        }
    }
}
