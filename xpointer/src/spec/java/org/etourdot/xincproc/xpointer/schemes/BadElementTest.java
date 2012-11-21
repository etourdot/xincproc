package org.etourdot.xincproc.xpointer.schemes;

import net.sf.saxon.s9api.SaxonApiException;
import org.concordion.integration.junit4.ConcordionRunner;
import org.etourdot.xincproc.xpointer.AbstractXPointerTest;
import org.etourdot.xincproc.xpointer.XPointerEngine;
import org.etourdot.xincproc.xpointer.exceptions.XPointerException;
import org.junit.runner.RunWith;
import org.xml.sax.InputSource;

import javax.xml.transform.sax.SAXSource;
import java.io.StringReader;
import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 03/11/12
 * Time: 11:47
 */
@RunWith(ConcordionRunner.class)
public class BadElementTest extends AbstractXPointerTest {
    public Boolean isElementSameShorthand(String ncname, String element, String source) throws SaxonApiException, XPointerException {
        XPointerEngine xPointerEngine = new XPointerEngine();
        SAXSource saxSource = new SAXSource(new InputSource(new StringReader(source)));
        String shortHandResult = xPointerEngine.execute(ncname, saxSource);
        saxSource = new SAXSource(new InputSource(new StringReader(source)));
        String elementResult = xPointerEngine.execute(element, saxSource);
        return shortHandResult.equals(elementResult);
    }
}
