package org.etourdot.xincproc.xpointer;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.Serializer;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;

import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.ByteArrayOutputStream;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 28/10/12
 * Time: 11:48
 */
public class XPointerEngineTest {
    Source source;

    @Before
    public void beforeTest() {
        source = new SAXSource(new InputSource(getClass().getClassLoader().getResourceAsStream("doc_test.xml")));
    }

    @Test
    public void testExecuteShortHand() throws Exception {
        Processor processor = new Processor(false);
        XPointerEngine xPointerEngine = new XPointerEngine();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Serializer serializer = processor.newSerializer(baos);
        xPointerEngine.execute("id3", source, serializer);
        String result = new String(baos.toByteArray());
        assertXMLEqual("<etat xml:id=\"id3\">VIGUEUR</etat>", result);
    }

    @Test
    public void testExecuteSchema() throws Exception {
        Processor processor = new Processor(false);
        XPointerEngine xPointerEngine = new XPointerEngine();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Serializer serializer = processor.newSerializer(baos);
        xPointerEngine.execute("element(id3)", source, serializer);
        String result = new String(baos.toByteArray());
        assertXMLEqual("<etat xml:id=\"id3\">VIGUEUR</etat>", result);
    }
}
