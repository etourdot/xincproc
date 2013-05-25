package org.etourdot.xincproc.xinclude;

import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 24/05/13
 * Time: 17:30
 */
public class XInclude11SuiteTest extends XIncProcSuiteTest {
    @Test
    public void attributeCopying() throws Exception
    {
        testSuccess(getClass().getClassLoader().getResource("xincproc/att-copy.xml"),
                getClass().getClassLoader().getResource("xincproc/att-copy-result.xml"),
                false, false);
    }
}
