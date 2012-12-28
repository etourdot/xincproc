package org.etourdot.xincproc.xpointer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 14/11/12
 * Time: 21:31
 */
public class DefaultXPointerErrorHandler implements XPointerErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(DefaultXPointerErrorHandler.class);

    public void reportError(final String error)
    {
        log.debug("reportError '{}'", error);
        System.err.println(error);
    }
}
