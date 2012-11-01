import java.io.*;
import org.antlr.runtime.*;
import org.antlr.runtime.debug.DebugEventSocketProxy;

import org.etourdot.xinclude.xpointer.*;


public class __Test__ {

    public static void main(String args[]) throws Exception {
        XPointerLexer lex = new XPointerLexer(new ANTLRFileStream("D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\output\\__Test___input.txt", "UTF8"));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        XPointerParser g = new XPointerParser(tokens, 49100, null);
        try {
            g.pointer();
        } catch (RecognitionException e) {
            e.printStackTrace();
        }
    }
}