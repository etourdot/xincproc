package org.etourdot.xincproc.xpointer;

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

    public Result executeWithBaseFixup(final String pointer, final String source, final String baseURI)
            throws XPointerException {
        final XPointerEngine xPointerEngine = new XPointerEngine();
        xPointerEngine.setBaseURI((baseURI.equals("null")) ? null : baseURI);
        return getResult(pointer, source, xPointerEngine);
    }

    public Result executeWithLang(final String pointer, final String lang, final String source)
            throws XPointerException {
        final XPointerEngine xPointerEngine = new XPointerEngine();
        if ("(null)".equals(lang)) {
            xPointerEngine.setLanguage(null);
        } else {
            xPointerEngine.setLanguage(lang);
        }
        return getResult(pointer, source, xPointerEngine);
    }

    private Result getResult(String pointer, String source, XPointerEngine xPointerEngine)
            throws XPointerException {
        final Result result = new Result();
        final PrintableXPointerErrorHandler printableXPointerErrorHandler = new PrintableXPointerErrorHandler();
        xPointerEngine.setXPointerErrorHandler(printableXPointerErrorHandler);
        result.result = xPointerEngine.execute(pointer, new SAXSource(new InputSource(new StringReader(source))));
        result.error = printableXPointerErrorHandler.toString();
        return result;
    }

    public Result execute(final String pointer, final String source)
            throws XPointerException {
        final XPointerEngine xPointerEngine = new XPointerEngine();
        return getResult(pointer, source, xPointerEngine);
    }

    class Result {
        public String result;
        public String error;
    }
}
