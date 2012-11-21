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

    public AbstractXPointerTree(TreeNodeStream input) {
        super(input);
    }

    AbstractXPointerTree(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public void setPointerFactory(PointerFactory pointerFactory) {
        this.factory = pointerFactory;
    }

    public void setErrorHandler(XPointerErrorHandler xPointerErrorHandler) {
        this.xPointerErrorHandler = xPointerErrorHandler;
    }

    private boolean updateRecoveryState() {
        if (state.errorRecovery) {
            return true;
        }
        state.syntaxErrors++;
        state.errorRecovery = true;
        return false;
    }

    public void reportPointerError(RecognitionException e) {
        if (updateRecoveryState()) return;
        displayPointerError(this.getTokenNames(), e);
    }

    public void reportPointerPartError(RecognitionException e) {
        if (updateRecoveryState()) return;
        displayPointerPartError(this.getTokenNames(), e);
    }

    public void reportElementSchemeDataError(RecognitionException e) {
        if (updateRecoveryState()) return;
        displayElementSchemeDataError(this.getTokenNames(), e);
    }

    private void displayPointerPartError(String[] tokenNames, RecognitionException e) {
        String msg = "Warning: bad pointerpart form '" + ((CommonErrorNode) e.node).getText() + "'";
        emitErrorMessage(msg);
    }

    private void displayPointerError(String[] tokenNames, RecognitionException e) {
        String msg = "Warning: bad pointer form '" + ((CommonErrorNode) e.node).getText() + "'";
        emitErrorMessage(msg);
    }

    private void displayElementSchemeDataError(String[] tokenNames, RecognitionException e) {
        String msg = "Warning: bad element scheme data '" + ((CommonErrorNode) e.node).getText() + "'";
        emitErrorMessage(msg);
    }

    @Override
    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
        String msg = getErrorMessage(e, tokenNames);
        emitErrorMessage(msg);
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

    public String unescape(String toEscape) {
        return toEscape.replaceAll("\\^\\)",")").replaceAll("\\^\\(","(").replaceAll("\\^\\^","^");
    }
}
