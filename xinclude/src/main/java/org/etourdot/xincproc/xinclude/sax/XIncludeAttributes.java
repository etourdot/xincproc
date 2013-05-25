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
import org.xml.sax.helpers.AttributesImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

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
        fillingAttributes(attributes);
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

    private void checkingAttributes() throws XIncludeFatalException, XIncludeRecoverableException
    {
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
        if (isPointerForXmlProcessingPresent())
        {
            if (isFragidPresent() && !getFragid().equals(getXPointer()))
            {
                throw new XIncludeRecoverableException("If both xpointer and fragid are specified, they should be the same.");
            }
        }
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

    private void fillingAttributes(final Attributes attributes) throws XIncludeFatalException, XIncludeRecoverableException
    {
        for (int i = 0; i < attributes.getLength(); i++)
        {
            final Attribute attribute = new Attribute(attributes.getLocalName(i), attributes.getQName(i),
                    attributes.getType(i), attributes.getURI(i), attributes.getValue(i));
            if (attribute.getLocalName().equals(XIncludeConstants.ATT_HREF.getLocalPart()))
            {
                final Optional<String> hrefAtt = Optional.fromNullable(attribute.getValue());
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
            }
            else if (attribute.getLocalName().equals(XIncludeConstants.ATT_PARSE.getLocalPart()))
            {
                this.parse = Optional.fromNullable(attribute.getValue());
            }
            else if (attribute.getLocalName().equals(XIncludeConstants.ATT_XPOINTER.getLocalPart()))
            {
                this.xpointer = Optional.fromNullable(attribute.getValue());
            }
            else if (attribute.getLocalName().equals(XIncludeConstants.ATT_FRAGID.getLocalPart()))
            {
                this.fragid = Optional.fromNullable(attribute.getValue());
            }
            else if (attribute.getLocalName().equals(XIncludeConstants.ATT_ENCODING.getLocalPart()))
            {
                this.encoding = Optional.fromNullable(attribute.getValue());
            }
            else if (attribute.getLocalName().equals(XIncludeConstants.ATT_ACCEPT.getLocalPart()))
            {
                this.accept = Optional.fromNullable(attribute.getValue());
            }
            else if (attribute.getLocalName().equals(XIncludeConstants.ATT_ACCEPT_LANGUAGE.getLocalPart()))
            {
                this.acceptLanguage = Optional.fromNullable(attribute.getValue());
            }
            else if (attribute.getLocalName().equals(XIncludeConstants.XMLBASE_QNAME.getLocalPart()) &&
                     attribute.getUri().equals(XIncludeConstants.XMLBASE_QNAME.getNamespaceURI()))
            {
                final Optional<String> baseAtt = Optional.fromNullable(attribute.getValue());
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
            else
            {
                if (!isOtherAttributesPresent())
                {
                    this.otherAttributes = Optional.of(new AttributesImpl());
                }
                this.otherAttributes.get().addAttribute(attribute.getUri(), attribute.getLocalName(), attribute.getQName(),
                        attribute.getType(), attribute.getValue());
            }
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
        if (XIncludeConstants.XML.equals(parse))
        {
            return true;
        }
        else if (configuration.is11Supported())
        {
            try
            {
                final MediaType mediaType = MediaType.parse(parse);
                return mediaType.is(MediaType.ANY_APPLICATION_TYPE) && mediaType.subtype().contains("xml");
            }
            catch (final IllegalArgumentException e)
            {
                return false;
            }
        }
        else
        {
            return false;
        }
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


    boolean isOtherAttributesPresent()
    {
        return this.otherAttributes.isPresent();
    }

    Attributes getOtherAttributes()
    {
        return this.otherAttributes.orNull();
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
        if (XIncludeConstants.TEXT.equals(parse))
        {
            return true;
        }
        else if (configuration.is11Supported())
        {
            try
            {
                final MediaType mediaType = MediaType.parse(parse);
                return mediaType.is(MediaType.ANY_TEXT_TYPE);
            }
            catch (final IllegalArgumentException e)
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    private Optional<URI> href = Optional.absent();
    private Optional<String> parse = Optional.absent();
    private Optional<String> xpointer = Optional.absent();
    private Optional<String> encoding = Optional.absent();
    private Optional<String> accept = Optional.absent();
    private Optional<String> acceptLanguage = Optional.absent();
    private Optional<URI> base = Optional.absent();
    private Optional<String> fragid = Optional.absent();
    private Optional<AttributesImpl> otherAttributes = Optional.absent();

    private class Attribute
    {
        private String localName;
        private String qName;
        private String type;
        private String uri;
        private String value;

        Attribute(final String localName, final String qName, final String type, final String uri, final String value)
        {
            this.localName = localName;
            this.qName = qName;
            this.type = type;
            this.uri = uri;
            this.value = value;
        }

        String getLocalName()
        {
            return localName;
        }

        String getQName()
        {
            return qName;
        }

        String getType()
        {
            return type;
        }

        String getUri()
        {
            return uri;
        }

        String getValue()
        {
            return value;
        }
    }
}
