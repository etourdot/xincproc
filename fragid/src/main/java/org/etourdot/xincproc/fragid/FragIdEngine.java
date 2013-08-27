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

package org.etourdot.xincproc.fragid;

import com.google.common.collect.Range;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import com.google.common.net.HttpHeaders;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.apache.commons.io.input.BOMInputStream;
import org.etourdot.xincproc.fragid.exceptions.FragIdException;
import org.etourdot.xincproc.fragid.grammar.FragIdLexer;
import org.etourdot.xincproc.fragid.grammar.FragIdParser;
import org.etourdot.xincproc.fragid.grammar.FragIdTree;
import org.etourdot.xincproc.fragid.model.FragId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 28/05/13
 * Time: 23:41
 */
public class FragIdEngine {
    public FragIdEngine()
    {
    }

    FragId getFragId(final String analysed)
            throws FragIdException
    {
        try
        {
            final CharStream input = new ANTLRStringStream(analysed);
            final FragIdLexer fragIdLexer = new FragIdLexer(input);
            final CommonTokenStream commonTokenStream = new CommonTokenStream(fragIdLexer);
            final FragIdParser fragIdParser = new FragIdParser(commonTokenStream);
            final FragIdParser.textfragment_return result = fragIdParser.textfragment();
            final CommonTree ast = (CommonTree) result.getTree();
            final CommonTreeNodeStream nodes = new CommonTreeNodeStream(ast);
            final FragIdTree fragIdTree = new FragIdTree(nodes);
            return fragIdTree.textfragment();
        }
        catch (final RecognitionException e)
        {
            throw new FragIdException("Unknown FragId expression", e);
        }
    }

    public String execute(final String fragIdStr, final URI source)
            throws FragIdException
    {
        try
        {
            final FragId fragId = getFragId(fragIdStr);
            return readTextWithFragId(fragId, source);
        }
        catch (FragIdException e)
        {
            final String message = e.getLocalizedMessage();
            LOG.error(message, e);
            throw e;
        }
    }

    String readTextWithFragId(final FragId fragId, final URI source)
            throws FragIdException
    {
        try
        {
            final URL url = source.toURL();
            final URLConnection urlConnection = url.openConnection();
            final InputStream urlInputStream = urlConnection.getInputStream();
            final byte[] inputBytes = ByteStreams.toByteArray(urlInputStream);
            urlInputStream.close();
            checkIntegrity(fragId, inputBytes, urlConnection.getContentLength());
            final BOMInputStream bomInputStream = new BOMInputStream(new ByteArrayInputStream(inputBytes));
            if (bomInputStream.hasBOM())
            {
                final InputStream inputStream = new BufferedInputStream(bomInputStream);
                final FragIdLineProcessor lineProcessor = new FragIdLineProcessor(fragId);
                return (String) CharStreams.readLines(new InputStreamReader(inputStream), lineProcessor);
            }
            else
            {
                final InputSupplier<ByteArrayInputStream> supplier = ByteStreams.newInputStreamSupplier(inputBytes);
                final Charset charset = (fragId.isCharsetPresent())? Charset.forName(fragId.getCharset()) : Charset.defaultCharset();
                final FragIdLineProcessor lineProcessor = new FragIdLineProcessor(fragId);
                return (String) CharStreams.readLines(CharStreams.newReaderSupplier(supplier, charset), lineProcessor);
            }
        }
        catch (final IOException e)
        {
            throw new FragIdException("Unable to read resource", e);
        }
    }

    private void checkIntegrity(final FragId fragId, final byte[] inputBytes, final int contentLength)
            throws FragIdException
    {
        if (fragId.isLengthPresent())
        {
            if (fragId.getLength() != contentLength)
            {
                throw new FragIdException("Integrity check length failed");
            }
        }
        if (fragId.isMd5Present())
        {
            try
            {
                final HashCode hashCode = ByteStreams.hash(ByteStreams.newInputStreamSupplier(inputBytes), Hashing.md5());
                if (!hashCode.toString().equals(fragId.getMd5()))
                {
                    throw new FragIdException("Integrity check md5 failed");
                }
            }
            catch (IOException e)
            {
                throw new FragIdException("Integrity check md5 failed");
            }
        }
    }

    private static final Logger LOG = LoggerFactory.getLogger(FragIdEngine.class);
}
