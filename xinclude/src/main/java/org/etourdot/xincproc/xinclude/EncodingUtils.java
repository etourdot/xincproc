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

package org.etourdot.xincproc.xinclude;

import com.google.common.io.ByteStreams;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 07/02/13
 * Time: 23:11
 */
final class EncodingUtils {
    private static final String DEFAULT_ENCODING = "UTF-8";

    private EncodingUtils()
    {
    }

    public static Charset getCharset(final InputStream inputStream) throws IOException
    {
        final Charset resultCharset;
        final byte[] buffer = new byte[192];
        final int reading = ByteStreams.read(inputStream, buffer, 0, 192);
        if (4 > reading)
        {
            resultCharset = Charset.forName(EncodingUtils.DEFAULT_ENCODING);
        }
        else
        {
            final long computed = (0xFF000000 & (buffer[0] << 24)) |
                    (0x00FF0000 & (buffer[1] << 16)) |
                    (0x0000FF00 & (buffer[2] << 8)) |
                    (0x000000FF & buffer[3]);
            if (0x0000FEFFL == computed)
            {
                resultCharset = Charset.forName("UCS-4");
            }
            else if (0xFFFE0000L == computed)
            {
                resultCharset = Charset.forName("UCS-4");
            }
            else if (0x0000003CL == computed)
            {
                resultCharset = Charset.forName("UCS-4BE");
            }
            else if (0x3C000000L == computed)
            {
                resultCharset = Charset.forName("UCS-4LE");
            }
            else if (0x003C003FL == computed)
            {
                resultCharset = Charset.forName("UTF-16BE");
            }
            else if (0x3C003F00L == computed)
            {
                resultCharset = Charset.forName("UTF-16LE");
            }
            else if (0x3C3F786DL == computed)
            {
                resultCharset = EncodingUtils.getXmlCharsetEncoding(buffer);
            }
            else if (0x4C6FA794L == computed)
            {
                resultCharset = EncodingUtils.getXmlCharsetEncoding(buffer);
            }
            else if (0xFEFF0000L == (computed & 0xFFFF0000L))
            {
                resultCharset = Charset.forName("UTF-16");
            }
            else if (0xFFFE0000L == (computed & 0xFFFF0000L))
            {
                resultCharset = Charset.forName("UTF-16");
            }
            else if (0xEFBBBF00L == (computed & 0xFFFFFF00L))
            {
                resultCharset = Charset.forName(EncodingUtils.DEFAULT_ENCODING);
            }
            else
            {
                resultCharset = Charset.forName(EncodingUtils.DEFAULT_ENCODING);
            }
        }
        return resultCharset;
    }

    private static Charset getXmlCharsetEncoding(final byte... input)
    {
        try
        {
            final XMLInputFactory factory = XMLInputFactory.newInstance();
            final XMLStreamReader streamReader = factory.createXMLStreamReader(new ByteArrayInputStream(input));
            final String guessEncoding = streamReader.getEncoding();
            if (null == guessEncoding)
            {
                return Charset.forName(EncodingUtils.DEFAULT_ENCODING);
            }
            else
            {
                return Charset.forName(guessEncoding);
            }
        }
        catch (final XMLStreamException ignored)
        {
            // Do nothing
        }
        return Charset.forName(EncodingUtils.DEFAULT_ENCODING);
    }


}
