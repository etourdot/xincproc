package org.etourdot.xincproc.fragid.model;

import java.util.List;

public class FragId
{
    private final FRAG_TYPE type;
    private final int positionStart;
    private final int positionEnd;
    private final List<IntegrityCheck> integrityChecks;

    public FragId(final FRAG_TYPE type, final int positionStart, final int positionEnd,
                  final List<IntegrityCheck> integrityChecks)
    {
        this.type = type;
        this.positionStart = positionStart;
        this.positionEnd = positionEnd;
        this.integrityChecks = integrityChecks;
    }

    public FRAG_TYPE getType()
    {
        return type;
    }

    public int getPositionStart()
    {
        return positionStart;
    }

    public int getPositionEnd()
    {
        return positionEnd;
    }

    public List<IntegrityCheck> getIntegrityChecks()
    {
        return integrityChecks;
    }
}
