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

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeResourceException;
import org.xml.sax.Attributes;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Optional;

import static java.util.Optional.ofNullable;

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
        if (ofNullable(this.href).isPresent())
        {
            if (getHref().toASCIIString().contains("#"))
            {
                throw new XIncludeFatalException("Fragment identifiers must not be used.");
            }
        }
        else
        {
            if (ofNullable(this.parse).isPresent() && isXmlParse() && Strings.isNullOrEmpty(getXPointer()))
            {
                throw new XIncludeFatalException("If the href attribute is absent when parse=\"xml\", the xpointer attribute must be present.");
            }
        }
        if (isTextParse())
        {
            if (ofNullable(this.xpointer).isPresent())
            {
                throw new XIncludeFatalException("The xpointer attribute must not be present when parse=\"text\"");
            }
            try
            {
                if (ofNullable(this.encoding).isPresent())
                {
                    Charset.forName(getEncoding());
                }
            }
            catch (final Exception ignored)
            {
                throw new XIncludeResourceException("Encoding attribute should be a valid encoding name");
            }
        }
        if (ofNullable(this.accept).isPresent() && XIncludeAttributes.checkVal(getAccept()))
        {
            throw new XIncludeFatalException("Attribute \"Accept\" containing characters outside the range #x20 through #x7E");
        }
        if (ofNullable(this.acceptLanguage).isPresent() && XIncludeAttributes.checkVal(getAcceptLanguage()))
        {
            throw new XIncludeFatalException("Attribute \"AcceptLanguage\" containing characters outside the range #x20 through #x7E");
        }

    }

    private void fillAttributes(final Attributes attributes) throws XIncludeFatalException
    {
        final Optional<String> hrefAtt = ofNullable(attributes.getValue(XIncludeConstants.ATT_HREF.getLocalPart()));
        try
        {
            if (hrefAtt.isPresent() && !Strings.isNullOrEmpty(hrefAtt.get()))
            {
                this.href = new URI(hrefAtt.get());
            }
            else
            {
                this.href = null;
            }
        }
        catch (URISyntaxException ignored)
        {
            throw new XIncludeFatalException("Href must be a valid URI");
        }
        this.parse = attributes.getValue(XIncludeConstants.ATT_PARSE.getLocalPart());
        if (ofNullable(this.parse).isPresent() && !XIncludeAttributes.VALID_PARSE.contains(this.parse))
        {
            throw new XIncludeFatalException("Parse value must be \"xml\" or \"text\".");
        }
        this.xpointer = attributes.getValue(XIncludeConstants.ATT_XPOINTER.getLocalPart());
        this.encoding = attributes.getValue(XIncludeConstants.ATT_ENCODING.getLocalPart());
        this.accept = attributes.getValue(XIncludeConstants.ATT_ACCEPT.getLocalPart());
        this.acceptLanguage = attributes.getValue(XIncludeConstants.ATT_ACCEPT_LANGUAGE.getLocalPart());
        final Optional<String> baseAtt = ofNullable(attributes.getValue(XIncludeConstants.XMLBASE_QNAME.getNamespaceURI()
                , XIncludeConstants.XMLBASE_QNAME.getLocalPart()));
        try
        {
            if (baseAtt.isPresent())
            {
                this.base = new URI(baseAtt.get());
            }
            else
            {
                this.base = null;
            }
        }
        catch (URISyntaxException ignored)
        {
            throw new XIncludeFatalException("Base must be a valid URI");
        }
    }

    String getAccept()
    {
        return this.accept;
    }

    boolean isAcceptPresent()
    {
        return ofNullable(this.accept).isPresent();
    }

    String getAcceptLanguage()
    {
        return this.acceptLanguage;
    }

    boolean isAcceptLanguagePresent()
    {
        return ofNullable(this.acceptLanguage).isPresent();
    }

    String getEncoding()
    {
        return this.encoding;
    }

    boolean isEncodingPresent()
    {
        return ofNullable(this.encoding).isPresent();
    }

    URI getHref()
    {
        return this.href;
    }

    boolean isHrefPresent()
    {
        return ofNullable(this.href).isPresent();
    }

    boolean isXmlParse()
    {
        return !ofNullable(this.parse).isPresent() || XIncludeConstants.XML.equals(getParse());
    }

    String getXPointer()
    {
        return this.xpointer;
    }

    boolean isXPointerPresent()
    {
        return ofNullable(this.xpointer).isPresent();
    }

    URI getBase()
    {
        return this.base;
    }

    boolean isBasePresent()
    {
        return ofNullable(this.base).isPresent();
    }

    String getParse()
    {
        return this.parse;
    }

    boolean isTextParse()
    {
        return ofNullable(this.parse).isPresent() && XIncludeConstants.TEXT.equals(getParse());
    }

    private URI href;
    private String parse;
    private String xpointer;
    private String encoding;
    private String accept;
    private String acceptLanguage;
    private URI base;
}
