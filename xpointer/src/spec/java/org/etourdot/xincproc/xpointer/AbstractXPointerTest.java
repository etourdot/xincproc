package org.etourdot.xincproc.xpointer;

import net.sf.saxon.s9api.SaxonApiException;
import org.etourdot.xincproc.xpointer.exceptions.XPointerException;
import org.xml.sax.InputSource;

import javax.xml.transform.sax.SAXSource;
import java.io.StringReader;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 04/11/12
 * Time: 23:41
 */
public abstract class AbstractXPointerTest {
    public Result execute(String pointer, String source) throws SaxonApiException, XPointerException {
        Result result = new Result();
        XPointerEngine xPointerEngine = new XPointerEngine();
        PrintableXPointerErrorHandler printableXPointerErrorHandler = new PrintableXPointerErrorHandler();
        xPointerEngine.setXPointerErrorHandler(printableXPointerErrorHandler);
        result.result = xPointerEngine.execute(pointer, new SAXSource(new InputSource(new StringReader(source))));
        result.error = printableXPointerErrorHandler.toString();
        return result;
    }

    class Result {
        public String result;
        public String error;
    }
}
