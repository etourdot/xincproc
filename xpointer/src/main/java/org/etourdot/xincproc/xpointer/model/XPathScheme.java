package org.etourdot.xincproc.xpointer.model;

import javax.xml.namespace.QName;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 21/09/12
 * Time: 22:55
 */
public class XPathScheme extends DefaultScheme {
    private static final QName XPATH_NAME = new QName("xpath");

    private String expression;

    public XPathScheme(String expression) {
        super(XPATH_NAME);
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
        return XPATH_NAME + "(" + expression + ")";
    }
}
