package org.etourdot.xincproc.xinclude.sax;

import net.sf.saxon.s9api.XdmNode;
import org.etourdot.xincproc.xinclude.XIncProcConfiguration;
import org.etourdot.xincproc.xinclude.XIncProcUtils;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.NamespaceSupport;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 18/12/12
 * Time: 09:16
 */
public class XIncludeContext implements Cloneable {
    private final XIncProcConfiguration configuration;

    private enum Treatment {TREAT_ALL_INCLUDES, TREAT_INCLUDES_WITHOUT_HREF};
    private Treatment currentTreatment;
    private boolean needTreatIncludeWithoutHref;

    private URI initialBaseURI;
    private URI currentBaseURI;
    private URI stackedBaseURI;
    private final Deque<URI> basesURIDeque = new ArrayDeque<URI>();

    private String language;
    private String currentLang;
    private final Deque<String> langDeque = new ArrayDeque<String>();

    private URI sourceURI;
    private URI hrefURI;
    private final Deque<String> xincludeDeque = new ArrayDeque<String>();

    private Exception currentException;
    private XdmNode sourceNode;

    public XIncludeContext(final XIncProcConfiguration configuration)
    {
        this.configuration = configuration;
        this.currentTreatment = Treatment.TREAT_ALL_INCLUDES;
    }

    public XIncProcConfiguration getConfiguration()
    {
        return configuration;
    }

    public static XIncludeContext newContext(final XIncludeContext contextToCopy)
    {
        final XIncludeContext newContext = new XIncludeContext(contextToCopy.getConfiguration());
        newContext.currentBaseURI = contextToCopy.currentBaseURI;
        newContext.stackedBaseURI = contextToCopy.stackedBaseURI;
        newContext.basesURIDeque.addAll(contextToCopy.basesURIDeque);
        newContext.language = contextToCopy.language;
        newContext.langDeque.addAll(contextToCopy.langDeque);
        newContext.xincludeDeque.addAll(contextToCopy.xincludeDeque);
        newContext.currentTreatment = contextToCopy.currentTreatment;
        newContext.sourceNode = contextToCopy.sourceNode;
        return newContext;
    }

    public void updateContextWithElementAttributes(final AttributesImpl attributes)
            throws XIncludeFatalException
    {
        final int baseAttIdx = attributes.getIndex(NamespaceSupport.XMLNS,
                XIncProcConfiguration.XMLBASE_QNAME.getLocalPart());
        if (baseAttIdx >= 0)
        {
            try
            {
                this.currentBaseURI = new URI(attributes.getValue(baseAttIdx));
                addBaseURIPath(currentBaseURI);
            }
            catch (final URISyntaxException e)
            {
                throw new XIncludeFatalException("Invalid base URI");
            }
        }
        else
        {
            this.currentBaseURI = null;
        }
        final int langAttIdx = attributes.getIndex(NamespaceSupport.XMLNS,
                XIncProcConfiguration.XMLLANG_QNAME.getLocalPart());
        if (langAttIdx >= 0)
        {
            this.currentLang = attributes.getValue(langAttIdx);
            this.langDeque.addLast(this.currentLang);
        }
        else
        {
            this.currentLang = null;
        }
    }

    public void updateContextWhenEndElement()
    {
        if (currentBaseURI != null)
        {
            removeBaseURIPath(currentBaseURI);
            currentBaseURI = null;
        }
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

    public void addInInclusionChain(final URI path, final String pointer)
            throws XIncludeFatalException
    {
        final String xincludePath = path.toASCIIString() + ((pointer!=null)?("#" + pointer):"");
        if (xincludeDeque.contains(xincludePath))
        {
            throw new XIncludeFatalException("Inclusion Loop on path: " + xincludePath);
        }
        xincludeDeque.addLast(xincludePath);
    }

    public void removeFromInclusionChain()
    {
        xincludeDeque.pollLast();
    }

    public URI getInitialBaseURI() {
        return initialBaseURI;
    }

    public void setInitialBaseURI(final URI initialBaseURI) {
        this.initialBaseURI = initialBaseURI;
        if (!this.basesURIDeque.isEmpty())
        {
            this.stackedBaseURI = XIncProcUtils.computeBase(getBaseURIPaths());
        }
        this.currentBaseURI = null;
        this.basesURIDeque.clear();
    }

    public void addBaseURIPath(final URI basePath)
    {
        basesURIDeque.addLast(basePath);
    }

    public List<URI> getBaseURIPaths()
    {
        return new ArrayList<URI>(basesURIDeque);
    }

    public void removeBaseURIPath(final URI basePath)
    {
        basesURIDeque.removeLastOccurrence(basePath);
    }

    public URI getCurrentBaseURI()
    {
        return currentBaseURI;
    }

    public URI getStackedBaseURI()
    {
        return stackedBaseURI;
    }

    public URI getHrefURI()
    {
        return hrefURI;
    }

    public void setHrefURI(final URI hrefURI)
    {
        this.hrefURI = hrefURI;
    }

    public String getCurrentLang()
    {
        return currentLang;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(final String language)
    {
        this.language = language;
    }

    public boolean isNeedTreatIncludeWithoutHref()
    {
        return needTreatIncludeWithoutHref;
    }

    public void setNeedTreatIncludeWithoutHref(final boolean needTreatIncludeWithoutHref)
    {
        this.needTreatIncludeWithoutHref = needTreatIncludeWithoutHref;
    }

    public XdmNode getSourceNode()
    {
        return sourceNode;
    }

    public void setSourceNode(final XdmNode sourceNode)
    {
        this.sourceNode = sourceNode;
    }

    public boolean isTreatAllIncludes()
    {
        return currentTreatment.equals(Treatment.TREAT_ALL_INCLUDES);
    }

    public boolean isTreatIncludeWithoutHref()
    {
        return currentTreatment.equals(Treatment.TREAT_INCLUDES_WITHOUT_HREF);
    }

    public void treatIncludesWithoutHref()
    {
        this.currentTreatment = Treatment.TREAT_INCLUDES_WITHOUT_HREF;
        this.needTreatIncludeWithoutHref = false;
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
        return "sourceURI:"+sourceURI+"\n,currentBase:"+currentBaseURI+",hrefURI:"+ hrefURI +"\n,stackedBase:"+stackedBaseURI+",lang:"+ getLanguage();
    }
}
