package org.etourdot.xincproc.xinclude;

import org.junit.Test;

import javax.xml.namespace.QName;
import java.net.URI;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: etourdot
 * Date: 29/10/11
 * Time: 15:28
 */
public class XIncProcUtilsTest {

    /*@Test
    public void testCheckXInclude() throws Exception {
        XIncProcUtils.verifyXIncludeAttributes("http://www.google.com", "text", "", "", "", "");
        XIncProcUtils.verifyXIncludeAttributes("http://www.google.com", "xml", "", "", "", "");
        try {
            XIncProcUtils.verifyXIncludeAttributes("", "", "", "", "", "");
            fail();
        } catch (XIncludeFatalException e) {
        }
        try {
            XIncProcUtils.verifyXIncludeAttributes("toto://www.titi.com", "xml", "", "", "", "");
        } catch (XIncludeFatalException e) {
            fail();
        } catch (XIncludeResourceException e) {
        }
        try {
            XIncProcUtils.verifyXIncludeAttributes("", "xml", "", "", "", "");
            fail();
        } catch (XIncludeFatalException e) {
        } catch (XIncludeResourceException e) {
            fail();
        }
        XIncProcUtils.verifyXIncludeAttributes("", "xml", "element(root)", "", "", "");
    }*/

    @Test
    public void testIsXInclude() throws Exception {
        QName test1 = XIncProcConfiguration.XINCLUDE_QNAME;
        QName test2 = new QName("", "include");
        QName test3 = new QName("http://toto/", "include");
        QName test4 = new QName("http://www.w3.org/2001/XInclude", "xinclude");
        assertTrue(XIncProcUtils.isXInclude(test1));
        assertTrue(XIncProcUtils.isXInclude(test2));
        assertFalse(XIncProcUtils.isXInclude(test3));
        assertFalse(XIncProcUtils.isXInclude(test4));
    }

    @Test
    public void testIsFallback() throws Exception {
        QName test1 = XIncProcConfiguration.FALLBACK_QNAME;
        QName test2 = new QName("", "fallback");
        QName test3 = new QName("http://toto/", "fallback");
        QName test4 = new QName("http://www.w3.org/2001/XInclude", "falback");
        assertTrue(XIncProcUtils.isFallback(test1));
        assertTrue(XIncProcUtils.isFallback(test2));
        assertFalse(XIncProcUtils.isFallback(test3));
        assertFalse(XIncProcUtils.isFallback(test4));
    }

    @Test
    public void testComputeBase() throws Exception {
        URI base = new URI("xinclude/");
        base = base.resolve(new URI("input-xinclude-recursive-2.xml"));
        assertEquals("xinclude/input-xinclude-recursive-2.xml", base.toASCIIString());
        base = base.resolve(new URI("para.xml"));
        assertEquals("xinclude/para.xml", base.toASCIIString());
    }

    @Test
    public void testResolveBase() throws Exception {

    }
}
