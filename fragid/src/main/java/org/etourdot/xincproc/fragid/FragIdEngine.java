package org.etourdot.xincproc.fragid;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.etourdot.xincproc.fragid.grammar.FragIdLexer;
import org.etourdot.xincproc.fragid.grammar.FragIdParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

public class FragIdEngine
{
    public String execute(final String fragIdStr, final BufferedReader source) throws IOException
    {
        final FragId fragId = getFragId(fragIdStr);
        return com.google.common.io.CharStreams.readLines(source, new FragIdLineProcessor(fragId));
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

    private static final Logger LOG = LoggerFactory.getLogger(FragIdEngine.class);
}
