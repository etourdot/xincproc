/*
 * This file is part of the XIncProc framework.
 * Copyright (C) 2010 - 2013 Emmanuel Tourdot
 *
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this software.
 * If not, see <http://www.gnu.org/licenses/>.
 */

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

    XIncludeAttributes(final Attributes attributes)
            throws XIncludeFatalException, XIncludeResourceException
    {
        fillAttributes(attributes);
        checkingAttributes();
    }

    private void checkingAttributes() throws XIncludeFatalException, XIncludeResourceException
    {
        if (this.href.isPresent())
        {
            if (getHref().toASCIIString().contains("#"))
            {
                throw new XIncludeFatalException("Fragment identifiers must not be used.");
            }
        }
        else
        {
            if (this.parse.isPresent() && isXmlParse() && Strings.isNullOrEmpty(getXPointer()))
            {
                throw new XIncludeFatalException("If the href attribute is absent when parse=\"xml\", the xpointer attribute must be present.");
            }
        }
        if (isTextParse())
        {
            if (this.xpointer.isPresent())
            {
                throw new XIncludeFatalException("The xpointer attribute must not be present when parse=\"text\"");
            }
            try
            {
                if (this.encoding.isPresent())
                {
                    Charset.forName(getEncoding());
                }
            }
            catch (final Exception ignored)
            {
                throw new XIncludeResourceException("Encoding attribute should be a valid encoding name");
            }
        }
        if (this.accept.isPresent() && XIncludeAttributes.checkVal(getAccept()))
        {
            throw new XIncludeFatalException("Attribute \"Accept\" containing characters outside the range #x20 through #x7E");
        }
        if (this.acceptLanguage.isPresent() && XIncludeAttributes.checkVal(getAcceptLanguage()))
        {
            throw new XIncludeFatalException("Attribute \"AcceptLanguage\" containing characters outside the range #x20 through #x7E");
        }

    }

    private static boolean checkVal(final String val)
    {
        final byte[] bytes = val.getBytes();
        for (final byte aByte : bytes)
        {
            if ((32 > aByte) || (126 < aByte))
            {
                return true;
            }
        }
        return false;
    }

    private void fillAttributes(final Attributes attributes) throws XIncludeFatalException
    {
        final Optional<String> hrefAtt = Optional.fromNullable(attributes.getValue(XIncludeConstants.ATT_HREF.getLocalPart()));
        try
        {
            if (hrefAtt.isPresent() && !Strings.isNullOrEmpty(hrefAtt.get()))
            {
                this.href = Optional.of(new URI(hrefAtt.get()));
            }
            else
            {
                this.href = Optional.absent();
            }
        }
        catch (URISyntaxException ignored)
        {
            throw new XIncludeFatalException("Href must be a valid URI");
        }
        this.parse = Optional.fromNullable(attributes.getValue(XIncludeConstants.ATT_PARSE.getLocalPart()));
        if (this.parse.isPresent() && !XIncludeAttributes.VALID_PARSE.contains(this.parse.get()))
        {
            throw new XIncludeFatalException("Parse value must be \"xml\" or \"text\".");
        }
        this.xpointer = Optional.fromNullable(attributes.getValue(XIncludeConstants.ATT_XPOINTER.getLocalPart()));
        this.encoding = Optional.fromNullable(attributes.getValue(XIncludeConstants.ATT_ENCODING.getLocalPart()));
        this.accept = Optional.fromNullable(attributes.getValue(XIncludeConstants.ATT_ACCEPT.getLocalPart()));
        this.acceptLanguage = Optional.fromNullable(attributes.getValue(XIncludeConstants.ATT_ACCEPT_LANGUAGE.getLocalPart()));
        final Optional<String> baseAtt = Optional.fromNullable(attributes.getValue(XIncProcConfiguration.XMLBASE_QNAME.getNamespaceURI()
                , XIncProcConfiguration.XMLBASE_QNAME.getLocalPart()));
        try
        {
            if (baseAtt.isPresent())
            {
                this.base = Optional.of(new URI(baseAtt.get()));
            }
            else
            {
                this.base = Optional.absent();
            }
        }
        catch (URISyntaxException ignored)
        {
            throw new XIncludeFatalException("Base must be a valid URI");
        }
    }

    public String getAccept()
    {
        return this.accept.orNull();
    }

    public boolean isAcceptPresent()
    {
        return this.accept.isPresent();
    }

    public String getAcceptLanguage()
    {
        return this.acceptLanguage.orNull();
    }

    public boolean isAcceptLanguagePresent()
    {
        return this.acceptLanguage.isPresent();
    }

    public String getEncoding()
    {
        return this.encoding.orNull();
    }

    public boolean isEncodingPresent()
    {
        return this.encoding.isPresent();
    }

    public URI getHref()
    {
        return this.href.orNull();
    }

    public boolean isHrefPresent()
    {
        return this.href.isPresent();
    }

    String getParse()
    {
        return this.parse.orNull();
    }

    public boolean isXmlParse()
    {
        return !this.parse.isPresent() || XIncludeConstants.XML.equals(getParse());
    }

    boolean isTextParse()
    {
        return this.parse.isPresent() && XIncludeConstants.TEXT.equals(getParse());
    }

    public String getXPointer()
    {
        return this.xpointer.orNull();
    }

    public boolean isXPointerPresent()
    {
        return this.xpointer.isPresent();
    }

    public URI getBase()
    {
        return this.base.orNull();
    }

    public boolean isBasePresent()
    {
        return this.base.isPresent();
    }

    private static final ImmutableList<String> VALID_PARSE = ImmutableList.of(XIncludeConstants.TEXT, XIncludeConstants.XML);
}
