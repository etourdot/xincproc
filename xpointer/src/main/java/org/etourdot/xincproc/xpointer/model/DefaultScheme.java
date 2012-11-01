package org.etourdot.xincproc.xpointer.model;

import javax.xml.namespace.QName;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 11:02
 */
public abstract class DefaultScheme implements PointerPart {
    private QName shemeName;

    public DefaultScheme(QName shemeName) {
        this.shemeName = shemeName;
    }

    public QName getShemeName() {
        return shemeName;
    }

    public void setShemeName(QName shemeName) {
        this.shemeName = shemeName;
    }
}
