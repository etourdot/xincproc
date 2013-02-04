package org.etourdot.xincproc.xpointer.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 21/10/12
 * Time: 23:16
 */
public class XPointerException extends Exception {
    public XPointerException(final Throwable cause)
    {
        super(cause);
    }

    public XPointerException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    public XPointerException(final String message)
    {
        super(message);
    }
}
