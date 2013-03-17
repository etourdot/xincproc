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

package org.etourdot.xincproc.xpointer;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.tree.CommonErrorNode;
import org.antlr.runtime.tree.TreeNodeStream;
import org.antlr.runtime.tree.TreeParser;
import org.etourdot.xincproc.xpointer.model.PointerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 15:54
 */
public class AbstractXPointerTree extends TreeParser {
    private static final Logger log = LoggerFactory.getLogger(AbstractXPointerTree.class);
    private XPointerErrorHandler xPointerErrorHandler;
    PointerFactory factory;

    public AbstractXPointerTree(final TreeNodeStream input)
    {
        super(input);
    }

    AbstractXPointerTree(final TreeNodeStream input, final RecognizerSharedState state)
    {
        super(input, state);
    }

    public void setPointerFactory(final PointerFactory pointerFactory)
    {
        this.factory = pointerFactory;
    }

    public void setErrorHandler(final XPointerErrorHandler xPointerErrorHandler)
    {
        this.xPointerErrorHandler = xPointerErrorHandler;
    }

    private boolean updateRecoveryState()
    {
        if (state.errorRecovery)
        {
            return true;
        }
        state.syntaxErrors++;
        state.errorRecovery = true;
        return false;
    }

    public void reportPointerError(final RecognitionException e)
    {
        if (updateRecoveryState())
        {
            return;
        }
        displayPointerError(this.getTokenNames(), e);
    }

    public void reportPointerPartError(final RecognitionException e)
    {
        if (updateRecoveryState())
        {
            return;
        }
        displayPointerPartError(this.getTokenNames(), e);
    }

    public void reportElementSchemeDataError(final RecognitionException e)
    {
        if (updateRecoveryState())
        {
            return;
        }
        displayElementSchemeDataError(this.getTokenNames(), e);
    }

    private void displayPointerPartError(final String[] tokenNames, final RecognitionException e)
    {
        String msg = "Warning: bad pointerpart form '" + ((CommonErrorNode) e.node).getText() + "'";
        emitErrorMessage(msg);
    }

    private void displayPointerError(final String[] tokenNames, final RecognitionException e)
    {
        String msg = "Warning: bad pointer form '" + ((CommonErrorNode) e.node).getText() + "'";
        emitErrorMessage(msg);
    }

    private void displayElementSchemeDataError(final String[] tokenNames, final RecognitionException e)
    {
        String msg = "Warning: bad element scheme data '" + ((CommonErrorNode) e.node).getText() + "'";
        emitErrorMessage(msg);
    }

    @Override
    public void displayRecognitionError(final String[] tokenNames, final RecognitionException e)
    {
        String msg = getErrorMessage(e, tokenNames);
        emitErrorMessage(msg);
    }

    @Override
    public void emitErrorMessage(final String msg)
    {
        log.debug("emitErrorMessage '{}'", msg);
        if (xPointerErrorHandler == null)
        {
            super.emitErrorMessage(msg);
        }
        else
        {
            xPointerErrorHandler.reportError(msg);
        }
    }

    public String unescape(final String toEscape)
    {
        return toEscape.replaceAll("\\^\\)", ")").replaceAll("\\^\\(", "(").replaceAll("\\^\\^", "^");
    }
}
