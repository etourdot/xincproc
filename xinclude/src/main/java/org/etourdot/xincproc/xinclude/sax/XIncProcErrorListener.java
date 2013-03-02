package org.etourdot.xincproc.xinclude.sax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 17/01/13
 * Time: 09:42
 */
public class XIncProcErrorListener implements ErrorListener {
    private static final Logger LOG = LoggerFactory.getLogger(XIncProcErrorListener.class);

    @Override
    public void warning(TransformerException exception)
            throws TransformerException
    {
        LOG.debug("warning");
    }

    @Override
    public void error(TransformerException exception)
            throws TransformerException
    {
        LOG.debug("error");
    }

    @Override
    public void fatalError(TransformerException exception)
            throws TransformerException
    {
        LOG.debug("fatalError");
    }
}
