package org.etourdot.xinclude.xpointer;

import org.custommonkey.xmlunit.Diff;
import org.etourdot.xinclude.XIncProcConfiguration;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: etourdot
 * Date: 10/11/11
 * Time: 21:33
 */
public class PointerPartTest {
    private XIncProcConfiguration configuration = new XIncProcConfiguration();

    @Test
    public void testParse() throws Exception
    {
        final XPointerEngine engine = new XPointerEngine();
        XPointerContext context = new XPointerContext(null, configuration);
        final PointerPart pointerPart = new PointerPart("australian");
        pointerPart.setContext(context);
        StringWriter writer = new StringWriter();
        pointerPart.parse(getClass().getClassLoader().getResource("eduni/test/lang-samples.xml").getPath(), writer);
        String controlXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xpc:root xmlns:xpc=\"http://org.etourdot" +
            ".com/XIncProc\"><xpc:top xml:lang=\"en-au\"><sentence id=\"australian\">G'day mate.</sentence></xpc:top></xpc:root>";
        Diff diff = new Diff(controlXML, writer.toString());
        assertTrue(diff.identical());
    }
}
