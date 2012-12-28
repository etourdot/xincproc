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
    public ElementScheme createElementScheme(final String name, final String data)
    {
        try
        {
            return new ElementScheme(name, data);
        }
        catch (final ElementSchemeException e)
        {
            return null;
        }
    }

    public ShortHand createShortHand(final String name)
    {
        return new ShortHand(name);
    }

    public XmlNsScheme createXmlNsScheme(final String localpart, final String uri)
    {
        if (!Strings.isNullOrEmpty(localpart))
        {
            return new XmlNsScheme(new QName(uri, localpart));
        }
        else
        {
            return null;
        }
    }

    public XmlNsScheme createXmlNsScheme(final QName qName)
    {
        return new XmlNsScheme(qName);
    }

    public XPathScheme createXPathScheme(final String xpath)
    {
        return new XPathScheme(xpath);
    }

    public XPointerScheme createXPointerScheme(final String xpath)
    {
        return new XPointerScheme(xpath);
    }

    public OtherScheme createOtherScheme(final QName qName, final String data)
    {
        return new OtherScheme(qName, data);
    }
}
