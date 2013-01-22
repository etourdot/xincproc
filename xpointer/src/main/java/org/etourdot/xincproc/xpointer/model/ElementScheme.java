package org.etourdot.xincproc.xpointer.model;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import org.etourdot.xincproc.xpointer.exceptions.ElementSchemeException;

import javax.xml.namespace.QName;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 21/09/12
 * Time: 22:54
 */
public class ElementScheme extends DefaultScheme {
    private static final QName ELEMENT_NAME = new QName("element");
    private static class ChildSequenceFunction implements Function<String, String>
    {
        @Override
        public String apply(String s)
        {
            return "/*[" + s + "]";
        }
    }
    private static final Function<String, String> CHILDSEQ_FUNCTION = new ChildSequenceFunction();
    private String name;
    private String childSequence;

    public ElementScheme(final String name, final String childSequence) throws ElementSchemeException
    {
        super(ELEMENT_NAME);
        if (Strings.isNullOrEmpty(name) && Strings.isNullOrEmpty(childSequence))
        {
            throw new ElementSchemeException();
        }
        this.name = name;
        this.childSequence = childSequence;
        this.expression = initExpression(name, childSequence);
    }

    public String getChildSequence()
    {
        return childSequence;
    }

    public String getName()
    {
        return name;
    }

    private String initExpression(String name, String childSequence) {
        final StringBuilder findExpr = new StringBuilder();
        if (!Strings.isNullOrEmpty(name))
        {
            findExpr.append(ID_SEARCH_EXPR.replaceAll("#ID#", name));
        }
        if (!Strings.isNullOrEmpty(childSequence))
        {
            findExpr.append(Joiner.on("").join(Iterables.transform(Splitter.on('/').omitEmptyStrings()
                    .split(childSequence), CHILDSEQ_FUNCTION)));
        }
        return findExpr.toString();
    }

    @Override
    public String toString()
    {
        return ELEMENT_NAME + "(" + name + ',' + childSequence + ")";
    }
}
