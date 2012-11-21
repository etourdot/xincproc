package org.etourdot.xincproc.xinclude.exceptions;

/**
 * @author Emmanuel Tourdot
 */
public class XIncludeDocumentNotFoundException extends Exception {
    public XIncludeDocumentNotFoundException(final Throwable nestedException)
    {
        super(nestedException);
    }

    public XIncludeDocumentNotFoundException(final String message)
    {
        super(message);
    }
}
