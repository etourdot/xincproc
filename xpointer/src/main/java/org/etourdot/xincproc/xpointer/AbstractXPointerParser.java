package org.etourdot.xincproc.xpointer;

import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.TokenStream;

import org.etourdot.xincproc.xpointer.model.Pointer;
import org.etourdot.xincproc.xpointer.model.PointerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 15:02
 */
public abstract class AbstractXPointerParser extends Parser {

    public AbstractXPointerParser(TokenStream input) {
        super(input);
    }

    public AbstractXPointerParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    @Override
    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
        super.displayRecognitionError(tokenNames, e);
    }


}
