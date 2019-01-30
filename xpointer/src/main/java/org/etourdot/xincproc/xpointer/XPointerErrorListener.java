package org.etourdot.xincproc.xpointer;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.BitSet;
import java.util.Collections;
import java.util.List;

public class XPointerErrorListener extends BaseErrorListener
{
    private final XPointerErrorHandler errorHandler;

    public XPointerErrorListener(final XPointerErrorHandler errorHandler)
    {
        this.errorHandler = errorHandler;
    }

    @Override
    public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol, final int line,
                            final int charPositionInLine, final String msg, final RecognitionException e)
    {
        System.err.println("line " + line + ":" + charPositionInLine + " " + msg);
        if (errorHandler != null)
        {
            errorHandler.reportError(msg);
        }
    }
}
