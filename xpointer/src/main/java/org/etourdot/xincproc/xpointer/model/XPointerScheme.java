package org.etourdot.xincproc.xpointer.model;

import javax.xml.namespace.QName;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 21/09/12
 * Time: 22:55
 */
public class XPointerScheme extends DefaultScheme {
    private static final QName XPOINTER_NAME = new QName("xpointer");

    private String expression;

    public XPointerScheme(String expression) {
        super(XPOINTER_NAME);
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return XPOINTER_NAME + "(" + expression + ")";
    }
}
