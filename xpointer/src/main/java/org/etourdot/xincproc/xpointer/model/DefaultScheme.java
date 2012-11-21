package org.etourdot.xincproc.xpointer.model;

import javax.xml.namespace.QName;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 11:02
 */
abstract class DefaultScheme implements PointerPart {
    private QName shemeName;

    DefaultScheme(QName shemeName) {
        this.shemeName = shemeName;
    }

    public QName getShemeName() {
        return shemeName;
    }

    public void setShemeName(QName shemeName) {
        this.shemeName = shemeName;
    }
}
