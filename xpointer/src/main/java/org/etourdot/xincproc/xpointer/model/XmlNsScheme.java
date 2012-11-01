package org.etourdot.xincproc.xpointer.model;

import javax.xml.namespace.QName;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 21/09/12
 * Time: 23:05
 */
public class XmlNsScheme extends DefaultScheme {
    private static final QName XMLNS_NAME = new QName("xmlns");

    private QName qName;

    public XmlNsScheme(QName qName) {
        super(XMLNS_NAME);
        this.qName = qName;
    }

    public QName getQName() {
        return qName;
    }

    public void setQName(QName qName) {
        this.qName = qName;
    }

    @Override
    public String toString() {
        return XMLNS_NAME + "(" + qName + ")";
    }
}

