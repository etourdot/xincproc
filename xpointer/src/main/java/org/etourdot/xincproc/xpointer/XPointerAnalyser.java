package org.etourdot.xincproc.xpointer;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.etourdot.xincproc.xpointer.model.*;

import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 15:21
 */
public final class XPointerAnalyser {
    static final String FIND_ELEMENTS = "for $d in #EXPR# return $d";
    static final String ID_SEARCH_EXPR = "//*[@id='#ID#']|fn:id('#ID#')";
    static final String FIND_XPATH= "for $d in #PATH# return $d";
    private static class ChildSequenceFunction implements Function<String, String> {
        @Override
        public String apply(String s) {
            return "/*[" + s + "]";
        }
    }
    public static final Function<String, String> CHILDSEQ_FUNCTION = new ChildSequenceFunction();

    public static Pointer analyse(final String stringToAnalyse) throws RecognitionException {
        final CharStream input = new ANTLRStringStream(stringToAnalyse);
        final XPointerLexer xPointerLexer = new XPointerLexer(input);
        final CommonTokenStream commonTokenStream = new CommonTokenStream(xPointerLexer);
        XPointerParser xPointerParser = new XPointerParser(commonTokenStream);
        XPointerParser.pointer_return result = xPointerParser.pointer();
        CommonTree ast = (CommonTree) result.getTree();
        CommonTreeNodeStream nodes = new CommonTreeNodeStream(ast);
        nodes.setTokenStream(commonTokenStream);
        XPointerTree xPointerTree = new XPointerTree(nodes);
        xPointerTree.setPointerFactory(new PointerFactory());
        return xPointerTree.pointer();
    }

    public static String getQueryFromElementScheme(final ElementScheme elementScheme) {
        StringBuilder findExpr = new StringBuilder();
        if (!Strings.isNullOrEmpty(elementScheme.getName())) {
            findExpr.append(ID_SEARCH_EXPR.replaceAll("#ID#", elementScheme.getName()));
        }
        if (!Strings.isNullOrEmpty(elementScheme.getChildSequence())) {
            findExpr.append(Joiner.on("").join(Iterables.transform(Splitter.on('/').omitEmptyStrings()
                    .split(elementScheme.getChildSequence()), CHILDSEQ_FUNCTION)));
        }
        return FIND_ELEMENTS.replaceAll("#EXPR#", findExpr.toString());
    }

    public static String getQueryFromShortHand(final ShortHand shortHand) {
        final String findExpr = ID_SEARCH_EXPR.replaceAll("#ID#", shortHand.getName());
        return FIND_ELEMENTS.replaceAll("#EXPR#", findExpr);
    }

    public static String getQueryFromXPathScheme(final XPathScheme xPathScheme) {
        return FIND_XPATH.replaceAll("#PATH#", xPathScheme.getExpression());
    }

    public static String getQueryFromXPointerScheme(final XPointerScheme xPointerScheme) {
        return FIND_XPATH.replaceAll("#PATH#", xPointerScheme.getExpression());
    }
}
