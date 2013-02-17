package org.etourdot.xincproc.xpointer;

import org.antlr.runtime.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.etourdot.xincproc.xpointer.exceptions.XPointerException;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 15:02
 */
public abstract class AbstractXPointerParser extends Parser {
    protected static final Logger log = LoggerFactory.getLogger(AbstractXPointerParser.class);
    private XPointerErrorHandler xPointerErrorHandler;

    public AbstractXPointerParser(final TokenStream input)
    {
        super(input);
    }

    public AbstractXPointerParser(final TokenStream input, final RecognizerSharedState state)
    {
        super(input, state);
    }

    public void setErrorHandler(final XPointerErrorHandler xPointerErrorHandler)
    {
        this.xPointerErrorHandler = xPointerErrorHandler;
    }

    protected void reportOtherSchemeError(final RecognitionException re)
    {
        if (re instanceof MismatchedTokenException)
        {
            emitErrorMessage("Warning: unknown scheme '" + ((MismatchedTokenException) re).token.getText() + "'");
        }
    }

    protected void reportElementSchemeError(final RecognitionException re)
    {
        if (re instanceof NoViableAltException)
        {
            emitErrorMessage("Warning: unknown element scheme data '" + ((NoViableAltException) re).token.getText() + "'");
        }
    }

    @Override
    public void emitErrorMessage(final String msg)
    {
        log.debug("emitErrorMessage '{}'", msg);
        if (xPointerErrorHandler==null)
        {
            super.emitErrorMessage(msg);
        }
        else
        {
            xPointerErrorHandler.reportError(msg);
        }
    }

    @Override
    protected Object recoverFromMismatchedToken(final IntStream input, final int ttype, final BitSet follow)
            throws RecognitionException
    {
        throw new MismatchedTokenException(ttype, input);
    }
}
