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

    public ShortHand(String name) {
        super(SHORTHAND_NAME);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return SHORTHAND_NAME + "(" + name + ")";
    }
}
