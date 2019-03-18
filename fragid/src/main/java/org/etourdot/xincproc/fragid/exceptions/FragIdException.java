package org.etourdot.xincproc.fragid.exceptions;

public class FragIdException extends Exception
{
    public FragIdException(final Throwable cause)
    {
        super(cause);
    }

    public FragIdException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    public FragIdException(final String message)
    {
        super(message);
    }
}
