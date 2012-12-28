package org.etourdot.xincproc.xpointer;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognizerSharedState;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 15:03
 */
public abstract class AbstractXPointerLexer extends Lexer {
    AbstractXPointerLexer()
    {
    }

    public AbstractXPointerLexer(final CharStream input)
    {
        this(input, new RecognizerSharedState());
    }

    AbstractXPointerLexer(final CharStream input, final RecognizerSharedState state)
    {
        super(input,state);
    }
}
