package org.etourdot.xincproc.xinclude.api;

import org.concordion.api.ExpectedToFail;
import org.concordion.integration.junit4.ConcordionRunner;
import org.etourdot.xincproc.xinclude.XIncProcEngine;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.junit.runner.RunWith;
import org.xml.sax.InputSource;

import javax.xml.transform.Result;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 03/11/12
 * Time: 12:08
 */
@RunWith(ConcordionRunner.class)
@ExpectedToFail
public class ApiTest {
    public String execute(String source) throws XIncludeFatalException {
        XIncProcEngine xIncProcEngine = new XIncProcEngine();
        SAXSource saxSource = new SAXSource(new InputSource(new StringReader(source)));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Result outputResult = new StreamResult(baos);
        xIncProcEngine.parse(saxSource, outputResult);
        return new String(baos.toByteArray());
    }
}
