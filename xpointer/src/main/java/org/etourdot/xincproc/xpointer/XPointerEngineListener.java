package org.etourdot.xincproc.xpointer;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.etourdot.xincproc.xpointer.grammar.XPointerBaseListener;
import org.etourdot.xincproc.xpointer.grammar.XPointerParser;
import org.etourdot.xincproc.xpointer.model.*;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class XPointerEngineListener extends XPointerBaseListener
{
    private Pointer pointer;
    private String elementName;
    private String elementData;
    private String xpathData;
    private String xmlnsPfx;
    private String xmlnsData;
    private QName qname;
    private String otherData;
    private List<PointerPart> pointerParts = new ArrayList<>();
    private final XPointerErrorHandler xPointerErrorHandler;

    public XPointerEngineListener(final XPointerErrorHandler xPointerErrorHandler)
    {
        this.xPointerErrorHandler = xPointerErrorHandler;
    }

    public Pointer getPointer()
    {
        return pointer;
    }

    @Override
    public void visitErrorNode(final ErrorNode node)
    {
        xPointerErrorHandler.reportError("Error: invalid xpointer expression '" + node.getText() + "'");
    }

    @Override
    public void exitSinglePointer(final XPointerParser.SinglePointerContext ctx)
    {
        this.pointer = new Pointer(new ShortHand(ctx.pointerName().getText()));
    }

    @Override
    public void exitMultiPointer(final XPointerParser.MultiPointerContext ctx)
    {
        this.pointer = new Pointer(pointerParts);
    }

    @Override
    public void enterElementPointer(final XPointerParser.ElementPointerContext ctx)
    {
        this.elementName = "";
        this.elementData = "";
    }

    @Override
    public void exitElementPointer(final XPointerParser.ElementPointerContext ctx)
    {
        pointerParts.add(PointerHelper.createElementScheme(elementName, elementData));
    }

    @Override
    public void enterXpathPointer(final XPointerParser.XpathPointerContext ctx)
    {
        this.xpathData = "";
    }

    @Override
    public void exitXpathPointer(final XPointerParser.XpathPointerContext ctx)
    {
        pointerParts.add(PointerHelper.createXPathScheme(xpathData));
    }

    @Override
    public void enterXmlnsPointer(final XPointerParser.XmlnsPointerContext ctx)
    {
        this.xmlnsPfx = "";
        this.xmlnsData = "";
    }

    @Override
    public void exitXmlnsPointer(final XPointerParser.XmlnsPointerContext ctx)
    {
        final XmlNsScheme xmlNsScheme = PointerHelper.createXmlNsScheme(xmlnsPfx, xmlnsData);
        if (xmlNsScheme != null)
        {
            pointerParts.add(xmlNsScheme);
        }
    }

    @Override
    public void enterXpointerPointer(final XPointerParser.XpointerPointerContext ctx)
    {
        this.xpathData = "";
    }

    @Override
    public void exitXpointerPointer(final XPointerParser.XpointerPointerContext ctx)
    {
        this.pointerParts.add(PointerHelper.createXPointerScheme(xpathData));
    }

    @Override
    public void enterOtherPointer(final XPointerParser.OtherPointerContext ctx)
    {
        this.otherData = "";
    }

    @Override
    public void exitOtherPointer(final XPointerParser.OtherPointerContext ctx)
    {
        xPointerErrorHandler.reportError("Warning: '" + qname + "' scheme is not supported");
    }

    @Override
    public void exitEltName(final XPointerParser.EltNameContext ctx)
    {
        this.elementName = ctx.NCNAME().getText();
    }

    @Override
    public void exitEltChild(final XPointerParser.EltChildContext ctx)
    {
        this.elementData = ctx.CHILDSEQUENCE().getText();
    }

    @Override
    public void exitEltNameChild(final XPointerParser.EltNameChildContext ctx)
    {
        this.elementName = ctx.NCNAME().getText();
        this.elementData = ctx.CHILDSEQUENCE().getText();
    }

    @Override
    public void exitXpathschemedata(final XPointerParser.XpathschemedataContext ctx)
    {
        final StringBuilder sb = new StringBuilder();
        for (XPointerParser.XpathescapeddataContext xpathescapeddataContext : ctx.xpathescapeddata())
        {
            sb.append(xpathescapeddataContext.getText());
        }
        this.xpathData = sb.toString();
    }

    @Override
    public void exitXmlnsschemedata(final XPointerParser.XmlnsschemedataContext ctx)
    {
        if (ctx.NCNAME() != null)
        {
            this.xmlnsPfx = ctx.NCNAME().getText();
            this.xmlnsData = ctx.escapednamespacename().getText();
        }
    }

    @Override
    public void exitNormQName(final XPointerParser.NormQNameContext ctx)
    {
        if (ctx.NCNAME().size() > 1)
        {
            this.qname = new QName(ctx.NCNAME(0).getText(), ctx.NCNAME(1).getText());
        }
        else
        {
            this.qname = new QName(ctx.NCNAME(0).getText());
        }
    }

    @Override
    public void exitSpecQName(final XPointerParser.SpecQNameContext ctx)
    {
        String localPart = "";
        if (ctx.ELEMENT() != null)
        {
            localPart = ctx.ELEMENT().getText();
        }
        if (ctx.XMLNS() != null)
        {
            localPart = ctx.XMLNS().getText();
        }
        if (ctx.XPATH() != null)
        {
            localPart = ctx.XPATH().getText();
        }
        if (ctx.XPOINTER() != null)
        {
            localPart = ctx.XPOINTER().getText();
        }
        this.qname = new QName(ctx.NCNAME().getText(),  localPart);
    }
}
