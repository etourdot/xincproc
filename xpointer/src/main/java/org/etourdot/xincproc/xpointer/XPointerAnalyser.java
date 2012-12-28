package org.etourdot.xincproc.xpointer;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import org.etourdot.xincproc.xpointer.model.ElementScheme;
import org.etourdot.xincproc.xpointer.model.ShortHand;
import org.etourdot.xincproc.xpointer.model.XPathScheme;
import org.etourdot.xincproc.xpointer.model.XPointerScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 15:21
 */
final class XPointerAnalyser {
    private static final Logger log = LoggerFactory.getLogger(XPointerAnalyser.class);
    private static final String FIND_ELEMENTS = "for $d in #EXPR# return $d";
    private static final String ID_SEARCH_EXPR = "(//*[@id='#ID#']|fn:id('#ID#'))";
    private static final String FIND_XPATH= "for $d in #PATH# return $d";
    private static class ChildSequenceFunction implements Function<String, String>
    {
        @Override
        public String apply(String s)
        {
            return "/*[" + s + "]";
        }
    }
    private static final Function<String, String> CHILDSEQ_FUNCTION = new ChildSequenceFunction();

    public static String getQueryFromElementScheme(final ElementScheme elementScheme)
    {
        final StringBuilder findExpr = new StringBuilder();
        if (!Strings.isNullOrEmpty(elementScheme.getName()))
        {
            findExpr.append(ID_SEARCH_EXPR.replaceAll("#ID#", elementScheme.getName()));
        }
        if (!Strings.isNullOrEmpty(elementScheme.getChildSequence()))
        {
            findExpr.append(Joiner.on("").join(Iterables.transform(Splitter.on('/').omitEmptyStrings()
                    .split(elementScheme.getChildSequence()), CHILDSEQ_FUNCTION)));
        }
        return FIND_ELEMENTS.replaceAll("#EXPR#", findExpr.toString());
    }

    public static String getQueryFromShortHand(final ShortHand shortHand)
    {
        final String findExpr = ID_SEARCH_EXPR.replaceAll("#ID#", shortHand.getName());
        return FIND_ELEMENTS.replaceAll("#EXPR#", findExpr);
    }

    public static String getQueryFromXPathScheme(final XPathScheme xPathScheme)
    {
        return FIND_XPATH.replaceAll("#PATH#", xPathScheme.getExpression());
    }

    public static String getQueryFromXPointerScheme(final XPointerScheme xPointerScheme)
    {
        return FIND_XPATH.replaceAll("#PATH#", xPointerScheme.getExpression());
    }
}
