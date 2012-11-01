package org.etourdot.xincproc.xpointer;

import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.tree.TreeNodeStream;
import org.antlr.runtime.tree.TreeParser;
import org.etourdot.xincproc.xpointer.model.PointerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 15:54
 */
public class AbstractXPointerTree extends TreeParser {
    protected PointerFactory factory;

    public AbstractXPointerTree(TreeNodeStream input) {
        super(input);
    }

    public AbstractXPointerTree(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public void setPointerFactory(PointerFactory pointerFactory) {
        this.factory = pointerFactory;
    }

    public String unescape(String toEscape) {
        return toEscape.replaceAll("\\^\\)",")").replaceAll("\\^\\(","(").replaceAll("\\^\\^","^");
    }
}
