package org.etourdot.xincproc.xpointer;

import org.antlr.runtime.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 15:02
 */
public abstract class AbstractXPointerParser extends Parser {
    protected static final Logger log = LoggerFactory.getLogger(AbstractXPointerParser.class);
    private XPointerErrorHandler xPointerErrorHandler;

    public AbstractXPointerParser(TokenStream input) {
        super(input);
    }

    public AbstractXPointerParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public void setErrorHandler(XPointerErrorHandler xPointerErrorHandler) {
        this.xPointerErrorHandler = xPointerErrorHandler;
    }

    protected void reportOtherSchemeError(RecognitionException re) {
        if (re instanceof MismatchedTokenException) {
            emitErrorMessage("Warning: unknown scheme '" + ((MismatchedTokenException) re).token.getText() + "'");
        }
    }

    protected void reportElementSchemeError(RecognitionException re) {
        if (re instanceof NoViableAltException) {
            emitErrorMessage("Warning: unknown element scheme data '" + ((NoViableAltException) re).token.getText() + "'");
        }
    }

    @Override
    public void emitErrorMessage(String msg) {
        log.debug("emitErrorMessage '{}'", msg);
        if (xPointerErrorHandler==null) {
            super.emitErrorMessage(msg);
        } else {
            xPointerErrorHandler.reportError(msg);
        }
    }
}
