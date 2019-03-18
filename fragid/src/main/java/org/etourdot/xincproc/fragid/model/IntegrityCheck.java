package org.etourdot.xincproc.fragid.model;

public class IntegrityCheck
{
    private final INTEGRITY_TYPE type;
    private final String value;
    private final String charset;

    public IntegrityCheck(final INTEGRITY_TYPE type, final String value, final String charset)
    {
        this.type = type;
        this.value = value;
        this.charset = charset;
    }

    public INTEGRITY_TYPE getType()
    {
        return type;
    }

    public String getValue()
    {
        return value;
    }

    public String getCharset()
    {
        return charset;
    }
}
