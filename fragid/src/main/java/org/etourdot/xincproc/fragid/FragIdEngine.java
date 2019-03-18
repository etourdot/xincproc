package org.etourdot.xincproc.fragid;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.input.BOMInputStream;
import org.etourdot.xincproc.fragid.exceptions.FragIdException;
import org.etourdot.xincproc.fragid.grammar.FragIdLexer;
import org.etourdot.xincproc.fragid.grammar.FragIdParser;
import org.etourdot.xincproc.fragid.model.FragId;
import org.etourdot.xincproc.fragid.model.INTEGRITY_TYPE;
import org.etourdot.xincproc.fragid.model.IntegrityCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class FragIdEngine
{
    public String execute(final String fragIdStr, final BufferedReader source) throws IOException
    {
        final FragId fragId = getFragId(fragIdStr);
        //return com.google.common.io.CharStreams.readLines(source, new FragIdLineProcessor(fragId));
        return readTextWithFragId(fragId, source);
    }

    public FragId getFragId(final String fragIdStr)
    {
        final CharStream input = CharStreams.fromString(fragIdStr);
        final FragIdLexer lexer = new FragIdLexer(input);
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final FragIdParser parser = new FragIdParser(tokens);
        parser.addErrorListener(new FragIdErrorListener());
        final ParseTree tree = parser.text_fragment();
        final ParseTreeWalker walker = new ParseTreeWalker();
        final FragIdEngineListener listener = new FragIdEngineListener();
        walker.walk(listener, tree);
        return listener.getFragId();
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
                return com.google.common.io.CharStreams.readLines(new InputStreamReader(inputStream), lineProcessor);
            }
            else
            {
                final BufferedInputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(inputBytes));
                final FragIdLineProcessor lineProcessor = new FragIdLineProcessor(fragId);
                return com.google.common.io.CharStreams.readLines(new InputStreamReader(inputStream), new FragIdLineProcessor(fragId));
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
        for (IntegrityCheck check : fragId.getIntegrityChecks())
        {
            if (check.getType() == INTEGRITY_TYPE.LENGTH)
            {
                if (Integer.valueOf(check.getValue()) != contentLength)
                {
                    throw new FragIdException("Integrity check length failed");
                }
            }
            if (check.getType() == INTEGRITY_TYPE.MD5)
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
    }

    private static final Logger LOG = LoggerFactory.getLogger(FragIdEngine.class);
}
