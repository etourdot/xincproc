package org.etourdot.xincproc.xinclude;

import net.sf.saxon.om.DocumentInfo;
import net.sf.saxon.s9api.Processor;
import org.etourdot.xincproc.xpointer.SampleTestHandler;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.namespace.QName;
import javax.xml.transform.sax.SAXSource;
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

    @Ignore
    public void testLexicalHandler() throws Exception {
        final InputSource inputSource = new InputSource(getClass().getClassLoader().getResourceAsStream("nist/test/ents/prtids.xml"));
        final SAXSource source = new SAXSource(inputSource);
        final XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        SampleTestHandler handler = new SampleTestHandler();;
        xmlReader.setProperty("http://xml.org/sax/properties/declaration-handler", handler);
        xmlReader.setContentHandler(handler);
        source.setXMLReader(xmlReader);
        final Processor processor = new Processor(false);
        DocumentInfo docInfo  = processor.getUnderlyingConfiguration().buildDocument(source);
        //xmlReader.parse(inputSource);
        fail();
    }
}
