package org.etourdot.xincproc.fragid;

import org.etourdot.xincproc.fragid.grammar.FragIdBaseListener;
import org.etourdot.xincproc.fragid.grammar.FragIdParser;
import org.etourdot.xincproc.fragid.model.FRAG_TYPE;
import org.etourdot.xincproc.fragid.model.FragId;
import org.etourdot.xincproc.fragid.model.INTEGRITY_TYPE;
import org.etourdot.xincproc.fragid.model.IntegrityCheck;

import java.util.ArrayList;
import java.util.List;

class FragIdEngineListener extends FragIdBaseListener
{
    private FRAG_TYPE fragType;
    private int positionStart;
    private int positionEnd;
    private List<IntegrityCheck> integrityChecks;

    FragIdEngineListener()
    {
        this.positionStart = -1;
        this.positionEnd = -1;
        this.integrityChecks = new ArrayList<>();
    }

    FragId getFragId()
    {
        return new FragId(fragType, positionStart, positionEnd, integrityChecks);
    }

    @Override
    public void exitChar_scheme(final FragIdParser.Char_schemeContext ctx)
    {
        this.fragType = FRAG_TYPE.CHAR;
    }

    @Override
    public void exitLine_scheme(final FragIdParser.Line_schemeContext ctx)
    {
        this.fragType = FRAG_TYPE.LINE;
    }

    @Override
    public void exitClose_rng(final FragIdParser.Close_rngContext ctx)
    {
        this.positionStart = Integer.valueOf(ctx.position(0).getText());
        this.positionEnd = Integer.valueOf(ctx.position(1).getText());
    }

    @Override
    public void exitLeft_rng(final FragIdParser.Left_rngContext ctx)
    {
        this.positionStart = -1;
        this.positionEnd = Integer.valueOf(ctx.position().getText());
    }

    @Override
    public void exitRight_rng(final FragIdParser.Right_rngContext ctx)
    {
        this.positionStart = Integer.valueOf(ctx.position().getText());
        this.positionEnd = -1;
    }

    @Override
    public void exitIntegrity_length(final FragIdParser.Integrity_lengthContext ctx)
    {
        integrityChecks.add(new IntegrityCheck(INTEGRITY_TYPE.LENGTH, ctx.length_scheme().NUMBER().getText(),
                ctx.mime_charset().getText()));
    }

    @Override
    public void enterIntegrity_md5(final FragIdParser.Integrity_md5Context ctx)
    {
        integrityChecks.add(new IntegrityCheck(INTEGRITY_TYPE.MD5, ctx.md5_scheme().md5_value().HEX32().getText(),
                ctx.mime_charset().getText()));
    }
}
