package org.etourdot.xincproc.xpointer;

import org.concordion.integration.junit4.ConcordionRunner;
import org.etourdot.xincproc.xpointer.exceptions.XPointerException;
import org.etourdot.xincproc.xpointer.model.Pointer;
import org.junit.runner.RunWith;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 30/10/12
 * Time: 23:03
 */
@RunWith(ConcordionRunner.class)
public class SpecXPointerEngineTest {
    public String getPointerAnalysed(String strPtr) throws XPointerException {
        XPointerEngine xPointerEngine = new XPointerEngine();
        Pointer pointer = xPointerEngine.getPointer(strPtr);
        return pointer.toString();
    }
}
