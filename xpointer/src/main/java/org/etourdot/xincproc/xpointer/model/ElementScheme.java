package org.etourdot.xincproc.xpointer.model;

import com.google.common.base.Strings;
import org.etourdot.xincproc.xpointer.exceptions.ElementSchemeException;

import javax.xml.namespace.QName;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 21/09/12
 * Time: 22:54
 */
public class ElementScheme extends DefaultScheme {
    private static final QName ELEMENT_NAME = new QName("element");
    private String name;
    private String childSequence;

    public ElementScheme(final String name, final String childSequence) throws ElementSchemeException
    {
        super(ELEMENT_NAME);
        if (Strings.isNullOrEmpty(name) && Strings.isNullOrEmpty(childSequence))
        {
            throw new ElementSchemeException();
        }
        this.name = name;
        this.childSequence = childSequence;
    }

    public String getChildSequence()
    {
        return childSequence;
    }

    public void setChildSequence(final String childSequence)
    {
        this.childSequence = childSequence;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return ELEMENT_NAME + "(" + name + ',' + childSequence + ")";
    }
}
