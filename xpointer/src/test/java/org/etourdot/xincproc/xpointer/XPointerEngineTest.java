package org.etourdot.xincproc.xpointer;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.Serializer;
import org.etourdot.xincproc.xpointer.model.Pointer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 28/10/12
 * Time: 11:48
 */
public class XPointerEngineTest {
    private static final Logger log = LoggerFactory.getLogger(XPointerEngineTest.class);
    private Source source;

    @Before
    public void beforeTest() {
        source = new SAXSource(new InputSource(getClass().getClassLoader().getResourceAsStream("doc_test.xml")));
    }

    @Test
    public void testValidPointers() throws Exception {
        XPointerEngine xPointerEngine = new XPointerEngine();
        File validPointersFile = new File(this.getClass().getClassLoader().getResource("valid_pointers.txt").getFile());
        List<String> lines = Files.readLines(validPointersFile, Charsets.UTF_8);
        for (String line : lines) {
            log.debug("##############");
            PrintableXPointerErrorHandler printableXPointerErrorHandler = new PrintableXPointerErrorHandler();
            xPointerEngine.setXPointerErrorHandler(printableXPointerErrorHandler);
            try {
                Pointer pointer = xPointerEngine.getPointer(line);
                log.debug("Pointer:{}", pointer);
                Assert.assertNotNull(line, pointer);
            } catch (Exception e) {
                Assert.fail(line);
            }
        }
    }

    @Test
    public void testInvalidPointers() throws Exception {
        XPointerEngine xPointerEngine = new XPointerEngine();
        File validPointersFile = new File(this.getClass().getClassLoader().getResource("invalid_pointers.txt").getFile());
        List<String> lines = Files.readLines(validPointersFile, Charsets.UTF_8);
        for (String line : lines) {
            log.debug("##############");
            try {
                Pointer pointer = xPointerEngine.getPointer(line);
                if (pointer != null && (pointer.isSchemeBased() || pointer.isShortHand())) {
                    Assert.fail(line);
                }
            } catch (Exception e) {
            }
        }
    }

    @Test
    public void testExecuteShortHand() throws Exception {
        Processor processor = new Processor(false);
        XPointerEngine xPointerEngine = new XPointerEngine();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Serializer serializer = processor.newSerializer(baos);
        xPointerEngine.executeToDestination("id3", source, serializer);
        String result = new String(baos.toByteArray());
        assertXMLEqual("<etat xml:id=\"id3\">VIGUEUR</etat>", result);
    }

    @Test
    public void testExecuteSchema() throws Exception {
        Processor processor = new Processor(false);
        XPointerEngine xPointerEngine = new XPointerEngine();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Serializer serializer = processor.newSerializer(baos);
        xPointerEngine.executeToDestination("element(id3)", source, serializer);
        String result = new String(baos.toByteArray());
        assertXMLEqual("<etat xml:id=\"id3\">VIGUEUR</etat>", result);
    }
}
