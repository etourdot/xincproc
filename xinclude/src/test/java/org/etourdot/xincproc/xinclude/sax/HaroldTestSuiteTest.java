package org.etourdot.xincproc.xinclude.sax;

import org.etourdot.xincproc.xinclude.AbstractSuiteTest;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 23/01/13
 * Time: 22:21
 */
public class HaroldTestSuiteTest extends AbstractSuiteTest {
    @Test
    public void harold_include_01() throws Exception
    {
        testsax(getClass().getClassLoader().getResource("harold/test/xmlbasetest3.xml"),
                getClass().getClassLoader().getResource("harold/result/xmlbasetest3.xml"));
    }
}
