package org.etourdot.xincproc.xpointer.model;

import javax.xml.namespace.QName;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 21/09/12
 * Time: 22:53
 */
public class ShortHand extends DefaultScheme {
    private static final QName SHORTHAND_NAME = new QName("shorthand");
    private String name;

    public ShortHand(final String name)
    {
        super(SHORTHAND_NAME);
        this.name = name;
        this.expression = ID_SEARCH_EXPR.replaceAll("#ID#", name);
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return SHORTHAND_NAME + "(" + name + ")";
    }
}
