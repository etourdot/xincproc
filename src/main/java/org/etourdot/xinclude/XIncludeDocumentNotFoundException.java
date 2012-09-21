package org.etourdot.xinclude;

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
