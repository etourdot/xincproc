package org.etourdot.xincproc.xpointer.model;

import org.etourdot.xincproc.xpointer.exceptions.*;

import javax.xml.namespace.QName;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 15:48
 */
public class PointerFactory {
    public ElementScheme createElementScheme(String name, String data) throws ElementSchemeException {
        return new ElementScheme(name, data);
    }

    public ShortHand createShortHand(String name) {
        return new ShortHand(name);
    }

    public XmlNsScheme createXmlNsScheme(String localpart, String uri) throws XmlNsSchemeException {
        return new XmlNsScheme(new QName(uri, localpart));
    }

    public XmlNsScheme createXmlNsScheme(QName qName) throws XmlNsSchemeException{
        return new XmlNsScheme(qName);
    }

    public XPathScheme createXPathScheme(String xpath) throws XPathSchemeException {
        return new XPathScheme(xpath);
    }

    public XPointerScheme createXPointerScheme(String xpath) throws XPointerSchemeException {
        return new XPointerScheme(xpath);
    }

    public OtherScheme createOtherScheme(QName qName, String data) throws XPointerException {
        return new OtherScheme(qName, data);
    }
}
