/*
 * This file is part of the XIncProc framework.
 * Copyright (C) 2011 - 2013 Emmanuel Tourdot
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
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeResourceException;
import org.xml.sax.Attributes;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/**
 * Internal class storing and checking attributes of a xinclude element
 */
class XIncludeAttributes
{
    private static final ImmutableList<String> VALID_PARSE = ImmutableList.of(XIncludeConstants.TEXT, XIncludeConstants.XML);

    XIncludeAttributes(final Attributes attributes)
            throws XIncludeFatalException, XIncludeResourceException
    {
        fillAttributes(attributes);
        checkingAttributes();
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
        final Optional<String> baseAtt = Optional.fromNullable(attributes.getValue(XIncludeConstants.XMLBASE_QNAME.getNamespaceURI()
                , XIncludeConstants.XMLBASE_QNAME.getLocalPart()));
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

    String getAccept()
    {
        return this.accept.orNull();
    }

    boolean isAcceptPresent()
    {
        return this.accept.isPresent();
    }

    String getAcceptLanguage()
    {
        return this.acceptLanguage.orNull();
    }

    boolean isAcceptLanguagePresent()
    {
        return this.acceptLanguage.isPresent();
    }

    String getEncoding()
    {
        return this.encoding.orNull();
    }

    boolean isEncodingPresent()
    {
        return this.encoding.isPresent();
    }

    URI getHref()
    {
        return this.href.orNull();
    }

    boolean isHrefPresent()
    {
        return this.href.isPresent();
    }

    boolean isXmlParse()
    {
        return !this.parse.isPresent() || XIncludeConstants.XML.equals(getParse());
    }

    String getXPointer()
    {
        return this.xpointer.orNull();
    }

    boolean isXPointerPresent()
    {
        return this.xpointer.isPresent();
    }

    URI getBase()
    {
        return this.base.orNull();
    }

    boolean isBasePresent()
    {
        return this.base.isPresent();
    }

    String getParse()
    {
        return this.parse.orNull();
    }

    boolean isTextParse()
    {
        return this.parse.isPresent() && XIncludeConstants.TEXT.equals(getParse());
    }

    private Optional<URI> href = Optional.absent();
    private Optional<String> parse = Optional.absent();
    private Optional<String> xpointer = Optional.absent();
    private Optional<String> encoding = Optional.absent();
    private Optional<String> accept = Optional.absent();
    private Optional<String> acceptLanguage = Optional.absent();
    private Optional<URI> base = Optional.absent();
}
