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
import com.google.common.net.MediaType;
import org.etourdot.xincproc.xinclude.XIncProcConfiguration;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeRecoverableException;
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
    private XIncProcConfiguration configuration;

    XIncludeAttributes(final XIncProcConfiguration configuration, final Attributes attributes)
            throws XIncludeFatalException, XIncludeRecoverableException
    {
        this.configuration = configuration;
        fillAttributes(attributes);
        checkingAttributes();
    }

    XIncludeAttributes(final Attributes attributes)
            throws XIncludeFatalException, XIncludeRecoverableException
    {
        this(XIncProcConfiguration.newXIncProcConfiguration(), attributes);
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
        if (isHrefPresent())
        {
            if (getHref().toASCIIString().contains("#"))
            {
                throw new XIncludeFatalException("Fragment identifiers must not be used.");
            }
        }
        else
        {
            if (isXmlProcessing() && !isPointerForXmlProcessingPresent())
            {
                final String message;
                if (configuration.is10Supported())
                {
                    message = "If the href attribute is absent when parse=\"xml\", the xpointer attribute must be present.";
                }
                else
                {
                    message = "If the href attribute is absent when XML processing is specified, the xpointer or fragid attribute must be present.";
                }
                throw new XIncludeFatalException(message);
            }
        }
        if (isTextProcessing())
        {
            if (isXPointerPresent())
            {
                throw new XIncludeFatalException("The xpointer attribute must not be present when parse=\"text\"");
            }
            try
            {
                if (isEncodingPresent())
                {
                    Charset.forName(getEncoding());
                }
            }
            catch (final Exception ignored)
            {
                throw new XIncludeResourceException("Encoding attribute should be a valid encoding name");
            }
        }
        if (isAcceptPresent() && XIncludeAttributes.checkVal(getAccept()))
        {
            throw new XIncludeFatalException("Attribute \"Accept\" containing characters outside the range #x20 through #x7E");
        }
        if (isAcceptLanguagePresent() && XIncludeAttributes.checkVal(getAcceptLanguage()))
        {
            throw new XIncludeFatalException("Attribute \"AcceptLanguage\" containing characters outside the range #x20 through #x7E");
        }

    }

    private void fillAttributes(final Attributes attributes) throws XIncludeFatalException, XIncludeRecoverableException
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
        if (!isXmlProcessing() && !isTextProcessing())
        {
            if (configuration.is10Supported())
            {
                throw new XIncludeFatalException("Parse value must be \"xml\" or \"text\".");
            }
            else
            {
                throw new XIncludeRecoverableException("XIncProc recognize only XML and text media type for parse attribute.");
            }
        }
        this.xpointer = Optional.fromNullable(attributes.getValue(XIncludeConstants.ATT_XPOINTER.getLocalPart()));
        this.fragid = Optional.fromNullable(attributes.getValue(XIncludeConstants.ATT_FRAGID.getLocalPart()));
        if (isPointerForXmlProcessingPresent())
        {
            if (!getXPointer().equals(getFragid()))
            {
                throw new XIncludeRecoverableException("If both xpointer and fragid are specified, they should be the same.");
            }
        }
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

    boolean isXmlProcessing()
    {
        return !this.parse.isPresent() || isParseXml(getParse());
    }

    private boolean isParseXml(final String parse)
    {
        final boolean returnValue;
        if (XIncludeConstants.XML.equals(parse))
        {
            returnValue = true;
        }
        else if (configuration.is11Supported())
        {
            final MediaType mediaType = MediaType.parse(parse);
            returnValue = mediaType.is(MediaType.ANY_APPLICATION_TYPE) && mediaType.subtype().contains("xml");
        }
        else
        {
            returnValue = false;
        }
        return returnValue;
    }

    String getXPointer()
    {
        return this.xpointer.orNull();
    }

    boolean isXPointerPresent()
    {
        return this.xpointer.isPresent();
    }

    String getPointerForXmlProcessing()
    {
        final String pointer;
        if (isXPointerPresent())
        {
            pointer = getXPointer();
        }
        else
        {
            pointer = getFragid();
        }
        return pointer;
    }

    boolean isPointerForXmlProcessingPresent()
    {
        return isXPointerPresent() || (configuration.is11Supported() && isFragidPresent());
    }

    URI getBase()
    {
        return this.base.orNull();
    }

    boolean isBasePresent()
    {
        return this.base.isPresent();
    }

    String getFragid()
    {
        return this.fragid.orNull();
    }

    boolean isFragidPresent()
    {
        return this.fragid.isPresent();
    }

    String getParse()
    {
        return this.parse.orNull();
    }

    boolean isTextProcessing()
    {
        return this.parse.isPresent() && isParseText(getParse());
    }

    private boolean isParseText(final String parse)
    {
        final boolean returnValue;
        if (XIncludeConstants.TEXT.equals(parse))
        {
            returnValue = true;
        }
        else if (configuration.is11Supported())
        {
            final MediaType mediaType = MediaType.parse(parse);
            returnValue = mediaType.is(MediaType.ANY_TEXT_TYPE);
        }
        else
        {
            returnValue = false;
        }
        return returnValue;
    }

    private Optional<URI> href = Optional.absent();
    private Optional<String> parse = Optional.absent();
    private Optional<String> xpointer = Optional.absent();
    private Optional<String> encoding = Optional.absent();
    private Optional<String> accept = Optional.absent();
    private Optional<String> acceptLanguage = Optional.absent();
    private Optional<URI> base = Optional.absent();
    private Optional<String> fragid = Optional.absent();
}
