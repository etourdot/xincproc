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

package org.etourdot.xincproc.xinclude;

import com.google.common.base.Strings;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import com.google.common.net.HttpHeaders;
import org.apache.commons.io.input.BOMInputStream;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeResourceException;

import javax.xml.namespace.QName;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Stack;

/**
 * XIncProcUtils.
 */
public final class XIncProcUtils
{
    /**
     * The constant XINCLUDE_NAMESPACE_URI.
     */
    public static final String XINCLUDE_NAMESPACE_URI = "http://www.w3.org/2001/XInclude";
    /**
     * The constant FALLBACK_QNAME.
     */
    public static final QName FALLBACK_QNAME = new QName(XINCLUDE_NAMESPACE_URI, "fallback", "xi");
    /**
     * The constant XINCLUDE_QNAME.
     */
    public static final QName XINCLUDE_QNAME = new QName(XINCLUDE_NAMESPACE_URI, "include", "xi");

    private XIncProcUtils()
    {
    }

    /**
     * Return if element is in XInclude namespace
     *
     * @param qname of the element to test
     * @return true if element is in xinclude ns, false otherwise
     */
    public static boolean isXIncludeNamespace(final QName qname)
    {
        return qname.getNamespaceURI().equals(XINCLUDE_NAMESPACE_URI);
    }

    /**
     * Return if element is Xinclude or not
     *
     * @param qname of the element to test
     * @return the boolean
     * @return true if element is a xinclude element, false otherwise
     */
    public static boolean isXInclude(final QName qname)
    {
        return XINCLUDE_QNAME.getLocalPart().equals(qname.getLocalPart()) &&
               (Strings.isNullOrEmpty(qname.getNamespaceURI()) ||
               XINCLUDE_QNAME.getNamespaceURI().equals(qname.getNamespaceURI()));
    }

    /**
     * Return if element is Fallback or not
     *
     * @param qname of the element to test
     * @return if element is a fallback element, false otherwise
     */
    public static boolean isFallback(final QName qname)
    {
        return FALLBACK_QNAME.getLocalPart().equals(qname.getLocalPart()) &&
               (Strings.isNullOrEmpty(qname.getNamespaceURI()) ||
               FALLBACK_QNAME.getNamespaceURI().equals(qname.getNamespaceURI()));
    }

    /**
     * Resolve base.
     *
     * @param baseURI the base uRI
     * @param uris the uris
     * @return the uRI
     */
    public static URI resolveBase(final URI baseURI, final Iterable<URI> uris)
    {
        URI resolvedUri = baseURI;
        for (final URI uri : uris)
        {
            resolvedUri = baseURI.resolve(uri);
        }
        return resolvedUri;
    }

    /**
     * Resolve base.
     *
     * @param baseURI the base uRI
     * @param stack the stack
     * @return the uRI
     * @throws XIncludeFatalException the x include fatal exception
     */
    public static URI resolveBase(final URI baseURI, final Stack<URI> stack) throws XIncludeFatalException
    {
        final URI computedUri = XIncProcUtils.computeBase(stack);
        final URI resolvedUri = baseURI.resolve(computedUri);
        if (0 == resolvedUri.compareTo(baseURI))
        {
            throw new XIncludeFatalException("Inclusion loop error");
        }
        return resolvedUri;
    }

    /**
     * Read a text source from the URI
     *
     * @param source the source
     * @param encoding the encoding, if encoding is null an analyse of the source will be done to
     *                 find correct encoding
     * @param accept the accept
     * @param acceptLanguage the accept language
     * @return string
     * @throws XIncludeFatalException in case of fatal error
     * @throws XIncludeResourceException if source is unreadable
     */
    public static String readTextURI(final URI source, final String encoding,
                                     final String accept, final String acceptLanguage)
            throws XIncludeFatalException, XIncludeResourceException
    {
        try
        {
            final URL url = source.toURL();
            final URLConnection urlConnection = url.openConnection();
            if (null != accept)
            {
                urlConnection.setRequestProperty(HttpHeaders.ACCEPT, accept);
            }
            if (null != acceptLanguage)
            {
                urlConnection.setRequestProperty(HttpHeaders.ACCEPT_LANGUAGE, acceptLanguage);
            }
            final InputStream urlInputStream = urlConnection.getInputStream();
            final byte[] inputBytes = ByteStreams.toByteArray(urlInputStream);
            urlInputStream.close();
            final BOMInputStream bomInputStream = new BOMInputStream(new ByteArrayInputStream(inputBytes));
            if (bomInputStream.hasBOM())
            {
                final Readable reader = new InputStreamReader(new BufferedInputStream(bomInputStream));
                return CharStreams.toString(reader);
            }
            else
            {
                final InputSupplier<ByteArrayInputStream> supplier = ByteStreams.newInputStreamSupplier(inputBytes);
                final Charset charset = (null == encoding) ? EncodingUtils.getCharset(supplier.getInput()) : Charset.forName(encoding);
                return CharStreams.toString(CharStreams.newReaderSupplier(supplier, charset));
            }

        }
        catch (MalformedURLException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
        catch (UnsupportedEncodingException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
        catch (IOException e)
        {
            throw new XIncludeResourceException(e.getMessage());
        }
    }

    private static URI computeBase(final Stack<URI> stack)
    {
        assert !stack.isEmpty();
        final Iterator<URI> it = stack.iterator();
        URI resultURI = it.next();
        while (it.hasNext())
        {
            final URI uri = it.next();
            resultURI = resultURI.resolve(uri);
        }
        return resultURI;
    }
}
