package org.etourdot.xincproc.xinclude.sax;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import org.etourdot.xincproc.xinclude.XIncProcConfiguration;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeResourceException;
import org.xml.sax.Attributes;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 27/12/12
 * Time: 23:19
 */
class XIncludeAttributes {
    private Optional<URI> href;
    private Optional<String> parse;
    private Optional<String> xpointer;
    private Optional<String> encoding;
    private Optional<String> accept;
    private Optional<String> acceptLanguage;
    private Optional<URI> base;

    public XIncludeAttributes(Attributes attributes)
            throws XIncludeFatalException, XIncludeResourceException
    {
        fillAttributes(attributes);
        checkAttributes();
    }

    private void checkAttributes() throws XIncludeFatalException, XIncludeResourceException
    {
        if (!href.isPresent())
        {
            if (parse.isPresent() && isXmlParse() && Strings.isNullOrEmpty(getXPointer()))
            {
                throw new XIncludeFatalException("If the href attribute is absent when parse=\"xml\", the xpointer attribute must be present.");
            }
        }
        else
        {
            if (getHref().toASCIIString().contains("#"))
            {
                throw new XIncludeFatalException("Fragment identifiers must not be used.");
            }
        }
        if (isTextParse())
        {
            if (xpointer.isPresent())
            {
                throw new XIncludeFatalException("The xpointer attribute must not be present when parse=\"text\"");
            }
            try
            {
                if (encoding.isPresent())
                {
                    Charset.forName(getEncoding());
                }
            }
            catch (final Exception e)
            {
                throw new XIncludeResourceException("Encoding attribute should be a valid encoding name");
            }
        }
        if (accept.isPresent() && checkVal(getAccept()))
        {
            throw new XIncludeFatalException("Attribute \"Accept\" containing characters outside the range #x20 through #x7E");
        }
        if (acceptLanguage.isPresent() && checkVal(getAcceptLanguage()))
        {
            throw new XIncludeFatalException("Attribute \"AcceptLanguage\" containing characters outside the range #x20 through #x7E");
        }

    }

    private static boolean checkVal(final String val)
    {
        final byte[] bytes = val.getBytes();
        for (byte aByte : bytes)
        {
            if (aByte < 32 || aByte > 126)
            {
                return true;
            }
        }
        return false;
    }

    private void fillAttributes(Attributes attributes) throws XIncludeFatalException
    {
        final Optional<String> hrefAtt = Optional.fromNullable(attributes.getValue(XIncProcConfiguration.ATT_HREF.getLocalPart()));
        try
        {
            if (hrefAtt.isPresent())
            {
                href = Optional.of(new URI(hrefAtt.get()));
            }
            else
            {
                href = Optional.absent();
            }
        }
        catch (URISyntaxException e)
        {
            throw new XIncludeFatalException("Href must be a valid URI");
        }
        parse = Optional.fromNullable(attributes.getValue(XIncProcConfiguration.ATT_PARSE.getLocalPart()));
        if (parse.isPresent() && !validParse.contains(parse.get()))
        {
            throw new XIncludeFatalException("Parse value must be \"xml\" or \"text\".");
        }
        xpointer = Optional.fromNullable(attributes.getValue(XIncProcConfiguration.ATT_XPOINTER.getLocalPart()));
        encoding = Optional.fromNullable(attributes.getValue(XIncProcConfiguration.ATT_ENCODING.getLocalPart()));
        accept = Optional.fromNullable(attributes.getValue(XIncProcConfiguration.ATT_ACCEPT.getLocalPart()));
        acceptLanguage = Optional.fromNullable(attributes.getValue(XIncProcConfiguration.ATT_ACCEPT_LANGUAGE.getLocalPart()));
        final  Optional<String> baseAtt = Optional.fromNullable(attributes.getValue(XIncProcConfiguration.XMLBASE_QNAME.getNamespaceURI()
                                            ,XIncProcConfiguration.XMLBASE_QNAME.getLocalPart()));
        try
        {
            if (baseAtt.isPresent())
            {
                base = Optional.of(new URI(baseAtt.get()));
            }
            else
            {
                base = Optional.absent();
            }
        }
        catch (URISyntaxException e)
        {
            throw new XIncludeFatalException("Base must be a valid URI");
        }
    }

    public String getAccept()
    {
        return accept.orNull();
    }

    public boolean isAcceptPresent()
    {
        return accept.isPresent();
    }

    public String getAcceptLanguage()
    {
        return acceptLanguage.orNull();
    }

    public boolean isAcceptLanguagePresent()
    {
        return acceptLanguage.isPresent();
    }

    public String getEncoding()
    {
        return encoding.orNull();
    }

    public boolean isEncodingPresent()
    {
        return encoding.isPresent();
    }

    public URI getHref()
    {
        return href.orNull();
    }

    public boolean isHrefPresent()
    {
        return href.isPresent();
    }

    public String getParse()
    {
        return parse.orNull();
    }

    public boolean isXmlParse()
    {
        return !parse.isPresent() || XIncProcConfiguration.XML.equals(getParse());
    }

    public boolean isTextParse()
    {
        return parse.isPresent() && XIncProcConfiguration.TEXT.equals(getParse());
    }

    public String getXPointer()
    {
        return xpointer.orNull();
    }

    public boolean isXPointerPresent()
    {
        return xpointer.isPresent();
    }

    public URI getBase()
    {
        return base.orNull();
    }

    public boolean isBasePresent()
    {
        return base.isPresent();
    }

    static final ImmutableList<String> validParse = ImmutableList.of(XIncProcConfiguration.TEXT, XIncProcConfiguration.XML);
}
