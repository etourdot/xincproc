package org.etourdot.xincproc.xpointer.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 02/02/13
 * Time: 23:56
 */
public class XPointerResourceException extends XPointerException {
    public XPointerResourceException(final Throwable cause)
    {
        super(cause);
    }

    public XPointerResourceException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    public XPointerResourceException(final String message)
    {
        super(message);
    }
}
