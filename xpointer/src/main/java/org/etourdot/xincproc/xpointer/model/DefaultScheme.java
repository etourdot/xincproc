package org.etourdot.xincproc.xpointer.model;

import javax.xml.namespace.QName;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 11:02
 */
abstract class DefaultScheme implements PointerPart {
    final static String ID_SEARCH_EXPR = "(//*[@id='#ID#']|id('#ID#'))";

    String expression;
    private QName schemeName;

    DefaultScheme(final QName schemeName)
    {
        this.schemeName = schemeName;
    }

    public QName getSchemeName()
    {
        return schemeName;
    }

    public String getExpression()
    {
        return expression;
    }
}
