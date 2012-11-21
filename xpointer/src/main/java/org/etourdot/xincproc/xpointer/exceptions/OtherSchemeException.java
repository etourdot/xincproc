package org.etourdot.xincproc.xpointer.exceptions;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 19/11/12
 * Time: 00:05
 */
public class OtherSchemeException extends RecognitionException {
    public OtherSchemeException(IntStream input) {
        super(input);
    }
}
