package org.etourdot.xincproc.xinclude.sax;

import org.etourdot.xincproc.xinclude.XIncProcConfiguration;
import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 09/02/13
 * Time: 14:50
 */
public class XIncludeContextTest {
    final XIncludeContext context = new XIncludeContext(new XIncProcConfiguration());

    @Test
    public void testAddBasePath() throws Exception {
        context.setInitialBaseURI(new URI("http://www.google.com/"));
        context.addBaseURIPath(new URI("toto/"));
        context.addBaseURIPath(new URI("titi/"));
        assertEquals(2, context.getBaseURIPaths().size());
    }
}
