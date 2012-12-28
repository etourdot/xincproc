package org.etourdot.xincproc.xpointer.exceptions;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/10/12
 * Time: 23:19
 */
public class XPointerSchemeException extends RecognitionException {
    public XPointerSchemeException(final IntStream input)
    {
        super(input);
    }
}
