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
class EncodingUtils {
    private static final String DEFAULT_ENCODING = "UTF-8";

    public static Charset getCharset(final InputStream inputStream) throws IOException
    {
        final Charset resultCharset;
        final byte[] buffer = new byte[192];
        final int reading = ByteStreams.read(inputStream, buffer, 0, 192);
        if (reading < 4)
        {
            resultCharset = Charset.forName(DEFAULT_ENCODING);
        }
        else
        {
            final long computed = 0xFF000000 & (buffer[0] << 24) |
                    0x00FF0000 & (buffer[1] << 16) |
                    0x0000FF00 & (buffer[2] << 8) |
                    0x000000FF & buffer[3];
            if (computed == 0x0000FEFF)
            {
                resultCharset = Charset.forName("UCS-4");
            }
            else if (computed == 0xFFFE0000)
            {
                resultCharset = Charset.forName("UCS-4");
            }
            else if (computed == 0x0000003C)
            {
                resultCharset = Charset.forName("UCS-4BE");
            }
            else if (computed == 0x3C000000)
            {
                resultCharset = Charset.forName("UCS-4LE");
            }
            else if (computed == 0x003C003F)
            {
                resultCharset = Charset.forName("UTF-16BE");
            }
            else if (computed == 0x3C003F00)
            {
                resultCharset = Charset.forName("UTF-16LE");
            }
            else if (computed == 0x3C3F786D)
            {
                resultCharset = getXmlCharsetEncoding(buffer);
            }
            else if (computed == 0x4C6FA794)
            {
                resultCharset = getXmlCharsetEncoding(buffer);
            }
            else if ((computed & 0xFFFF0000) == 0xFEFF0000)
            {
                resultCharset = Charset.forName("UTF-16");
            }
            else if ((computed & 0xFFFF0000) == 0xFFFE0000)
            {
                resultCharset = Charset.forName("UTF-16");
            }
            else if ((computed & 0xFFFFFF00) == 0xEFBBBF00)
            {
                resultCharset = Charset.forName(DEFAULT_ENCODING);
            }
            else
            {
                resultCharset = Charset.forName(DEFAULT_ENCODING);
            }
        }
        return resultCharset;
    }

    private static Charset getXmlCharsetEncoding(final byte[] input)
    {
        try
        {
            final XMLInputFactory factory = XMLInputFactory.newInstance();
            final XMLStreamReader streamReader;
            streamReader = factory.createXMLStreamReader(new ByteArrayInputStream(input));
            final String guessEncoding = streamReader.getEncoding();
            if (guessEncoding == null)
            {
                return Charset.forName(DEFAULT_ENCODING);
            }
            else
            {
                return Charset.forName(guessEncoding);
            }
        }
        catch (final XMLStreamException e)
        {
            // Do nothing
        }
        return Charset.forName(DEFAULT_ENCODING);
    }


}
