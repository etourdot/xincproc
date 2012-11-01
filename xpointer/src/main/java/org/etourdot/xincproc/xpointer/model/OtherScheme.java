package org.etourdot.xincproc.xpointer.model;

import javax.xml.namespace.QName;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 21/09/12
 * Time: 23:37
 */
public class OtherScheme extends DefaultScheme {
    private String schemeData;

    public OtherScheme(QName nameScheme, String schemeData) {
        super(nameScheme);
        this.schemeData = schemeData;
    }

    public String getSchemeData() {
        return schemeData;
    }

    public void setSchemeData(String schemeData) {
        this.schemeData = schemeData;
    }
}
