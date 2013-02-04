package org.etourdot.xincproc.xinclude.sax;

import net.sf.saxon.s9api.XdmNode;
import org.etourdot.xincproc.xinclude.XIncProcConfiguration;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 18/12/12
 * Time: 09:16
 */
public class XIncludeContext implements Cloneable {
    private enum Pass {PASS_ONE, PASS_TWO};

    private Pass currentPass;
    private URI sourceURI;
    private URI baseURI;
    private String language;
    private final Stack<String> oldPathStack = new Stack<String>();
    private final Stack<URI> pathStack = new Stack<URI>();
    private final XIncProcConfiguration configuration;
    private boolean needFallback;
    private boolean  proceedFallback;
    private Exception currentException;
    private boolean inInclude;
    private boolean inFallback;
    private int injectingXIncludeCount;
    private boolean needSecondPass;
    private boolean proceedPassTwo;
    private XdmNode sourceNode;

    public XIncludeContext(final XIncProcConfiguration configuration)
    {
        this.configuration = configuration;
        this.currentPass = Pass.PASS_ONE;
        this.injectingXIncludeCount = 0;
    }

    public boolean isLanguageFixup() {
        return configuration.isLanguageFixup();
    }

    public boolean isBaseFixup()
    {
        return configuration.isBaseUrisFixup();
    }

    public URI getSourceURI()
    {
        return sourceURI;
    }

    public void setSourceURI(final URI sourceURI)
    {
        this.sourceURI = sourceURI;
    }

    public Exception getCurrentException()
    {
        return currentException;
    }

    public void setCurrentException(final Exception currentException)
    {
        this.currentException = currentException;
    }

    public List<URI> getPaths()
    {
        return new ArrayList<URI>(pathStack);
    }

        public void addPath(final URI path)
            throws XIncludeFatalException
    {
        addPath(path, null);
    }

    public void addPath(final URI path, final String pointer)
            throws XIncludeFatalException
    {
        final String oldPath = path.toASCIIString() + ((pointer!=null)?("#" + pointer):"");
        if (oldPathStack.search(oldPath) >= 0)
        {
            throw new XIncludeFatalException("Inclusion Loop on path: " + oldPath);
        }
        oldPathStack.push(oldPath);
        pathStack.push(path);
    }

    public void removePath()
    {
        if (!oldPathStack.empty())
        {
            oldPathStack.pop();
        }
        if (!pathStack.empty())
        {
            pathStack.pop();
        }
    }

    public XIncProcConfiguration getConfiguration()
    {
        return configuration;
    }

    public URI getBaseURI()
    {
        return baseURI;
    }

    public void setBaseURI(final URI baseURI)
    {
        this.baseURI = baseURI;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(final String language)
    {
        this.language = language;
    }

    public boolean isInFallback() {
        return inFallback;
    }

    public void setInFallback(boolean inFallback) {
        this.inFallback = inFallback;
    }

    public boolean isInInclude() {
        return inInclude;
    }

    public void setInInclude(boolean inInclude) {
        this.inInclude = inInclude;
    }

    public boolean isNeedFallback() {
        return needFallback;
    }

    public void setNeedFallback(boolean needFallback) {
        this.needFallback = needFallback;
    }

    public boolean isInjectingXInclude()
    {
        return this.injectingXIncludeCount > 0;
    }

    public void startInjectingXInclude()
            throws XIncludeFatalException
    {
        this.injectingXIncludeCount ++;
        if (injectingXIncludeCount > 5)
        {
            throw new XIncludeFatalException("Inclusion Loop");
        }
    }

    public void stopInjectingXInclude()
    {
        this.injectingXIncludeCount --;
    }

    public void noInjectingXInclude()
    {
        this.injectingXIncludeCount = 0;
    }

    public boolean isProceedFallback()
    {
        return proceedFallback;
    }

    public void setProceedFallback(final boolean proceedFallback)
    {
        this.proceedFallback = proceedFallback;
    }

    public boolean isNeedSecondPass()
    {
        return needSecondPass;
    }

    public void setNeedSecondPass(final boolean needSecondPass)
    {
        this.needSecondPass = needSecondPass;
    }

    public XdmNode getSourceNode()
    {
        return sourceNode;
    }

    public void setSourceNode(final XdmNode sourceNode)
    {
        this.sourceNode = sourceNode;
    }

    public boolean isPassOne()
    {
        return currentPass.equals(Pass.PASS_ONE);
    }
    public boolean isPassTwo()
    {
        return currentPass.equals(Pass.PASS_TWO);
    }

    public void inPassOne()
    {
        this.currentPass = Pass.PASS_ONE;
    }

    public void inPassTwo()
    {
        this.currentPass = Pass.PASS_TWO;
        this.needSecondPass = false;
    }

    public boolean isProceedPassTwo()
    {
        return proceedPassTwo;
    }

    public void setProceedPassTwo(final boolean proceedPassTwo)
    {
        this.proceedPassTwo = proceedPassTwo;
    }

    @Override
    protected XIncludeContext clone()
            throws CloneNotSupportedException
    {
        return (XIncludeContext) super.clone();
    }

    @Override
    public String toString()
    {
        return "sourceURI:"+sourceURI+",baseURI:"+baseURI+",lang:"+ getLanguage()+",baseFixup:"+isBaseFixup();
    }

    boolean isUsable()
    {
        return (isInFallback() && isNeedFallback()) ||
               (!isInFallback() && !isInInclude()) ||
               (isInInclude() && isInjectingXInclude());
    }
}
