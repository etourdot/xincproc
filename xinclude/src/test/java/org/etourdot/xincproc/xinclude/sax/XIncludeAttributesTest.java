package org.etourdot.xincproc.xinclude.sax;

import org.etourdot.xincproc.xinclude.XIncProcConfiguration;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeResourceException;
import org.junit.Test;
import org.xml.sax.helpers.AttributesImpl;

import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 28/12/12
 * Time: 17:03
 */
public class XIncludeAttributesTest {

    @Test
    public void testXIncludeAttributesOk() throws Exception
    {
        AttributesImpl attributes = new AttributesImpl();
        attributes.addAttribute(XIncProcConfiguration.ATT_HREF.getNamespaceURI(), XIncProcConfiguration.ATT_HREF.getLocalPart(),
                XIncProcConfiguration.ATT_HREF.getLocalPart(), "CDATA", "http://www.google.com");
        attributes.addAttribute(XIncProcConfiguration.ATT_PARSE.getNamespaceURI(), XIncProcConfiguration.ATT_PARSE.getLocalPart(),
                XIncProcConfiguration.ATT_PARSE.getLocalPart(), "CDATA", "text");
        XIncludeAttributes xIncludeAttributes = new XIncludeAttributes(attributes);
        assertNotNull(xIncludeAttributes);
        attributes = new AttributesImpl();
        attributes.addAttribute(XIncProcConfiguration.ATT_HREF.getNamespaceURI(), XIncProcConfiguration.ATT_HREF.getLocalPart(),
                XIncProcConfiguration.ATT_HREF.getLocalPart(), "CDATA", "http://www.google.com");
        attributes.addAttribute(XIncProcConfiguration.ATT_PARSE.getNamespaceURI(), XIncProcConfiguration.ATT_PARSE.getLocalPart(),
                XIncProcConfiguration.ATT_PARSE.getLocalPart(), "CDATA", "xml");
        xIncludeAttributes = new XIncludeAttributes(attributes);
        assertNotNull(xIncludeAttributes);
    }

    @Test(expected = XIncludeResourceException.class)
    public void testResourceException() throws Exception
    {
        AttributesImpl attributes = new AttributesImpl();
        attributes.addAttribute(XIncProcConfiguration.ATT_ENCODING.getNamespaceURI(), XIncProcConfiguration.ATT_ENCODING.getLocalPart(),
                XIncProcConfiguration.ATT_ENCODING.getLocalPart(), "CDATA", "utf-9");
        attributes.addAttribute(XIncProcConfiguration.ATT_PARSE.getNamespaceURI(), XIncProcConfiguration.ATT_PARSE.getLocalPart(),
                XIncProcConfiguration.ATT_PARSE.getLocalPart(), "CDATA", "text");
        XIncludeAttributes xIncludeAttributes = new XIncludeAttributes(attributes);
    }
}
