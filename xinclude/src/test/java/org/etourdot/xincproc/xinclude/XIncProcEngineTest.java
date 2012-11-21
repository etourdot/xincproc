package org.etourdot.xincproc.xinclude;

import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.junit.Test;

import java.net.URI;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: etourdot
 * Date: 26/10/2011
 * Time: 23:03:36
 */
public class XIncProcEngineTest {
    @Test
    public void testResolveBase() throws Exception {
        Stack<URI> stack = new Stack<URI>();
        stack.add(new URI("xinclude2.xml"));
        stack.add(new URI("xinclude3.xml"));
        URI resultUri = XIncProcUtils.resolveBase(new URI("xinclude1.xml"), stack);
        assertEquals(new URI("xinclude3.xml"), resultUri);
        resultUri = XIncProcUtils.resolveBase(new URI("xinclude2.xml"), stack);
        assertEquals(new URI("xinclude3.xml"), resultUri);
        try {
            resultUri = XIncProcUtils.resolveBase(new URI("xinclude3.xml"), stack);
        } catch(XIncludeFatalException e) {
            assertEquals("Inclusion loop error", e.getMessage());
        }
        stack.add(new URI("docs/xinclude4.xml"));
        resultUri = XIncProcUtils.resolveBase(new URI("test/xinclude1.xml"), stack);
        assertEquals(new URI("test/docs/xinclude4.xml"), resultUri);
        stack = new Stack<URI>();
        stack.add(new URI("xinclude3.xml"));
        stack.add(new URI("xinclude/xinclude2.xml"));
        stack.add(new URI("para.xml"));
        resultUri = XIncProcUtils.resolveBase(new URI("para.xml"), stack);
        assertEquals(new URI("xinclude/para.xml"), resultUri);

    }
}
