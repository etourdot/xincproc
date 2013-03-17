package org.etourdot.xincproc.xpointer;

import net.sf.saxon.s9api.*;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.ByteArrayOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 25/01/13
 * Time: 23:20
 */
public class TransfoHandlerTest {
    public void newTest() throws Exception {
        InputSource source = new InputSource(getClass().getClassLoader().getResourceAsStream("idtst.xml"));
        String query = "for $x in (/*[1]/*[2] union fn:id('f2') union fn:id('f2')) return $x";
        //String query = "for $x in /*[1]/*[2] return $x";
        Processor proc = new Processor(false);
        XQueryCompiler comp = proc.newXQueryCompiler();
        XQueryExecutable exp = comp.compile(query);
        XQueryEvaluator qe = exp.load();
        qe.setSource(new SAXSource(source));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        qe.setDestination(new Serializer(baos));
        qe.run();
        System.out.println(new String(baos.toByteArray()));
    }

    @Test

    public void monTest() throws Exception {
        // Create a reader, and set it's content handler to be the TransformerHandler.
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XMLReader reader = factory.newSAXParser().getXMLReader();

        SampleTestHandler sampleTestHandler = new SampleTestHandler();
        reader.setContentHandler(sampleTestHandler);

        // It's a good idea for the parser to send lexical events.
        // The TransformerHandler is also a LexicalHandler.
        reader.setProperty("http://xml.org/sax/properties/lexical-handler", sampleTestHandler);
        reader.setProperty("http://xml.org/sax/properties/declaration-handler", sampleTestHandler);
        reader.setDTDHandler(sampleTestHandler);
        reader.setEntityResolver(sampleTestHandler);

        // Parse the source XML, and send the parse events to the TransformerHandler.
        reader.parse(new InputSource(getClass().getClassLoader().getResourceAsStream("idtst.xml")));
    }
}
