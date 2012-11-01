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
    public AbstractXPointerLexer()
    {
    }

    public AbstractXPointerLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }

    public AbstractXPointerLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
}
