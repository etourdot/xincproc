package org.etourdot.xincproc.xpointer.model;

import com.google.common.base.Strings;
import org.etourdot.xincproc.xpointer.exceptions.*;

import javax.xml.namespace.QName;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 15:48
 */
public class PointerFactory {
    public ElementScheme createElementScheme(String name, String data) {
        try {
            return new ElementScheme(name, data);
        } catch (ElementSchemeException e) {
            return null;
        }
    }

    public ShortHand createShortHand(String name) {
        return new ShortHand(name);
    }

    public XmlNsScheme createXmlNsScheme(String localpart, String uri) {
        if (!Strings.isNullOrEmpty(localpart))
        {
            return new XmlNsScheme(new QName(uri, localpart));
        }
        else
        {
            return null;
        }
    }

    public XmlNsScheme createXmlNsScheme(QName qName) {
        return new XmlNsScheme(qName);
    }

    public XPathScheme createXPathScheme(String xpath) {
        return new XPathScheme(xpath);
    }

    public XPointerScheme createXPointerScheme(String xpath) {
        return new XPointerScheme(xpath);
    }

    public OtherScheme createOtherScheme(QName qName, String data) {
        return new OtherScheme(qName, data);
    }
}
