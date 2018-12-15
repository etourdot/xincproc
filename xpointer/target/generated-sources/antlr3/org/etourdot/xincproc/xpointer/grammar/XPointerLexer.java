// $ANTLR 3.4 org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g 2018-12-15 17:51:14

    package org.etourdot.xincproc.xpointer.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class XPointerLexer extends AbstractXPointerLexer {
    public static final int EOF=-1;
    public static final int CHILDSEQUENCE=4;
    public static final int CIRC=5;
    public static final int COLON=6;
    public static final int COVERINGRANGE=7;
    public static final int DIGIT=8;
    public static final int ELEMENT=9;
    public static final int ENTITY=10;
    public static final int EQUAL=11;
    public static final int ESCCIRC=12;
    public static final int ESCLBRACE=13;
    public static final int ESCRBRACE=14;
    public static final int HERE=15;
    public static final int LBRACE=16;
    public static final int NCNAME=17;
    public static final int NCNAMECHAR=18;
    public static final int NCNAMESTARTCHAR=19;
    public static final int ORIGIN=20;
    public static final int POINT=21;
    public static final int RANGE=22;
    public static final int RANGEINSIDE=23;
    public static final int RANGETO=24;
    public static final int RBRACE=25;
    public static final int S=26;
    public static final int SPECIALCARS=27;
    public static final int STARTPOINT=28;
    public static final int STRINGRANGE=29;
    public static final int XMLNS=30;
    public static final int XPATH=31;
    public static final int XPOINTER=32;

    // delegates
    // delegators
    public AbstractXPointerLexer[] getDelegates() {
        return new AbstractXPointerLexer[] {};
    }

    public XPointerLexer() {} 
    public XPointerLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public XPointerLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g"; }

    // $ANTLR start "ESCCIRC"
    public final void mESCCIRC() throws RecognitionException {
        try {
            int _type = ESCCIRC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:12:13: ( '^^' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:12:17: '^^'
            {
            match("^^"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ESCCIRC"

    // $ANTLR start "ESCLBRACE"
    public final void mESCLBRACE() throws RecognitionException {
        try {
            int _type = ESCLBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:13:13: ( '^(' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:13:17: '^('
            {
            match("^("); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ESCLBRACE"

    // $ANTLR start "ESCRBRACE"
    public final void mESCRBRACE() throws RecognitionException {
        try {
            int _type = ESCRBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:14:13: ( '^)' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:14:17: '^)'
            {
            match("^)"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ESCRBRACE"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:15:13: ( ':' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:15:17: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:16:13: ( '=' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:16:17: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "CIRC"
    public final void mCIRC() throws RecognitionException {
        try {
            int _type = CIRC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:17:13: ( '^' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:17:17: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CIRC"

    // $ANTLR start "LBRACE"
    public final void mLBRACE() throws RecognitionException {
        try {
            int _type = LBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:18:13: ( '(' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:18:17: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LBRACE"

    // $ANTLR start "RBRACE"
    public final void mRBRACE() throws RecognitionException {
        try {
            int _type = RBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:19:13: ( ')' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:19:17: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RBRACE"

    // $ANTLR start "XPOINTER"
    public final void mXPOINTER() throws RecognitionException {
        try {
            int _type = XPOINTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:20:13: ( 'xpointer' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:20:17: 'xpointer'
            {
            match("xpointer"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "XPOINTER"

    // $ANTLR start "XPATH"
    public final void mXPATH() throws RecognitionException {
        try {
            int _type = XPATH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:21:11: ( 'xpath' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:21:15: 'xpath'
            {
            match("xpath"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "XPATH"

    // $ANTLR start "ELEMENT"
    public final void mELEMENT() throws RecognitionException {
        try {
            int _type = ELEMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:22:13: ( 'element' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:22:17: 'element'
            {
            match("element"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ELEMENT"

    // $ANTLR start "XMLNS"
    public final void mXMLNS() throws RecognitionException {
        try {
            int _type = XMLNS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:23:11: ( 'xmlns' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:23:15: 'xmlns'
            {
            match("xmlns"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "XMLNS"

    // $ANTLR start "STRINGRANGE"
    public final void mSTRINGRANGE() throws RecognitionException {
        try {
            int _type = STRINGRANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:24:13: ( 'string-range' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:24:15: 'string-range'
            {
            match("string-range"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRINGRANGE"

    // $ANTLR start "RANGETO"
    public final void mRANGETO() throws RecognitionException {
        try {
            int _type = RANGETO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:25:13: ( 'range-to' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:25:17: 'range-to'
            {
            match("range-to"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RANGETO"

    // $ANTLR start "POINT"
    public final void mPOINT() throws RecognitionException {
        try {
            int _type = POINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:26:13: ( 'point' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:26:17: 'point'
            {
            match("point"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "POINT"

    // $ANTLR start "RANGE"
    public final void mRANGE() throws RecognitionException {
        try {
            int _type = RANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:27:13: ( 'range' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:27:17: 'range'
            {
            match("range"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RANGE"

    // $ANTLR start "COVERINGRANGE"
    public final void mCOVERINGRANGE() throws RecognitionException {
        try {
            int _type = COVERINGRANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:28:14: ( 'covering-range' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:28:17: 'covering-range'
            {
            match("covering-range"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COVERINGRANGE"

    // $ANTLR start "RANGEINSIDE"
    public final void mRANGEINSIDE() throws RecognitionException {
        try {
            int _type = RANGEINSIDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:29:13: ( 'range-inside' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:29:17: 'range-inside'
            {
            match("range-inside"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RANGEINSIDE"

    // $ANTLR start "STARTPOINT"
    public final void mSTARTPOINT() throws RecognitionException {
        try {
            int _type = STARTPOINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:30:13: ( 'start-point' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:30:17: 'start-point'
            {
            match("start-point"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STARTPOINT"

    // $ANTLR start "HERE"
    public final void mHERE() throws RecognitionException {
        try {
            int _type = HERE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:31:13: ( 'here' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:31:17: 'here'
            {
            match("here"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HERE"

    // $ANTLR start "ORIGIN"
    public final void mORIGIN() throws RecognitionException {
        try {
            int _type = ORIGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:32:13: ( 'origin' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:32:17: 'origin'
            {
            match("origin"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ORIGIN"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            int _type = DIGIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:34:11: ( '0' .. '9' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "NCNAMESTARTCHAR"
    public final void mNCNAMESTARTCHAR() throws RecognitionException {
        try {
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:36:10: ( 'A' .. 'Z' | '_' | 'a' .. 'z' | '\\u00C0' .. '\\u00D6' | '\\u00D8' .. '\\u00F6' | '\\u00F8' .. '\\u02FF' | '\\u0370' .. '\\u037D' | '\\u037F' .. '\\u1FFF' | '\\u200C' .. '\\u200D' | '\\u2070' .. '\\u218F' | '\\u2C00' .. '\\u2FEF' | '\\u3001' .. '\\uD7FF' | '\\uF900' .. '\\uFDCF' | '\\uFDF0' .. '\\uFFFD' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u02FF')||(input.LA(1) >= '\u0370' && input.LA(1) <= '\u037D')||(input.LA(1) >= '\u037F' && input.LA(1) <= '\u1FFF')||(input.LA(1) >= '\u200C' && input.LA(1) <= '\u200D')||(input.LA(1) >= '\u2070' && input.LA(1) <= '\u218F')||(input.LA(1) >= '\u2C00' && input.LA(1) <= '\u2FEF')||(input.LA(1) >= '\u3001' && input.LA(1) <= '\uD7FF')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFDCF')||(input.LA(1) >= '\uFDF0' && input.LA(1) <= '\uFFFD') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NCNAMESTARTCHAR"

    // $ANTLR start "NCNAMECHAR"
    public final void mNCNAMECHAR() throws RecognitionException {
        try {
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:42:10: ( NCNAMESTARTCHAR | '-' | '.' | '0' .. '9' | '\\u00B7' | '\\u0300' .. '\\u036F' | '\\u203F' .. '\\u2040' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:
            {
            if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||input.LA(1)=='\u00B7'||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u037D')||(input.LA(1) >= '\u037F' && input.LA(1) <= '\u1FFF')||(input.LA(1) >= '\u200C' && input.LA(1) <= '\u200D')||(input.LA(1) >= '\u203F' && input.LA(1) <= '\u2040')||(input.LA(1) >= '\u2070' && input.LA(1) <= '\u218F')||(input.LA(1) >= '\u2C00' && input.LA(1) <= '\u2FEF')||(input.LA(1) >= '\u3001' && input.LA(1) <= '\uD7FF')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFDCF')||(input.LA(1) >= '\uFDF0' && input.LA(1) <= '\uFFFD') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NCNAMECHAR"

    // $ANTLR start "SPECIALCARS"
    public final void mSPECIALCARS() throws RecognitionException {
        try {
            int _type = SPECIALCARS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:46:10: ( '\"' | '\\'' | '_' | '[' | ']' | '/' | ',' | '*' | '-' | '.' | '@' | '<' | '>' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:
            {
            if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='*'||(input.LA(1) >= ',' && input.LA(1) <= '/')||input.LA(1)=='<'||input.LA(1)=='>'||input.LA(1)=='@'||input.LA(1)=='['||input.LA(1)==']'||input.LA(1)=='_' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SPECIALCARS"

    // $ANTLR start "NCNAME"
    public final void mNCNAME() throws RecognitionException {
        try {
            int _type = NCNAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:49:12: ( NCNAMESTARTCHAR ( NCNAMECHAR )* )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:49:15: NCNAMESTARTCHAR ( NCNAMECHAR )*
            {
            mNCNAMESTARTCHAR(); 


            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:49:31: ( NCNAMECHAR )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '-' && LA1_0 <= '.')||(LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')||LA1_0=='\u00B7'||(LA1_0 >= '\u00C0' && LA1_0 <= '\u00D6')||(LA1_0 >= '\u00D8' && LA1_0 <= '\u00F6')||(LA1_0 >= '\u00F8' && LA1_0 <= '\u037D')||(LA1_0 >= '\u037F' && LA1_0 <= '\u1FFF')||(LA1_0 >= '\u200C' && LA1_0 <= '\u200D')||(LA1_0 >= '\u203F' && LA1_0 <= '\u2040')||(LA1_0 >= '\u2070' && LA1_0 <= '\u218F')||(LA1_0 >= '\u2C00' && LA1_0 <= '\u2FEF')||(LA1_0 >= '\u3001' && LA1_0 <= '\uD7FF')||(LA1_0 >= '\uF900' && LA1_0 <= '\uFDCF')||(LA1_0 >= '\uFDF0' && LA1_0 <= '\uFFFD')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:
            	    {
            	    if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||input.LA(1)=='\u00B7'||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u037D')||(input.LA(1) >= '\u037F' && input.LA(1) <= '\u1FFF')||(input.LA(1) >= '\u200C' && input.LA(1) <= '\u200D')||(input.LA(1) >= '\u203F' && input.LA(1) <= '\u2040')||(input.LA(1) >= '\u2070' && input.LA(1) <= '\u218F')||(input.LA(1) >= '\u2C00' && input.LA(1) <= '\u2FEF')||(input.LA(1) >= '\u3001' && input.LA(1) <= '\uD7FF')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFDCF')||(input.LA(1) >= '\uFDF0' && input.LA(1) <= '\uFFFD') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NCNAME"

    // $ANTLR start "CHILDSEQUENCE"
    public final void mCHILDSEQUENCE() throws RecognitionException {
        try {
            int _type = CHILDSEQUENCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:53:13: ( ( '/' '1' .. '9' ( DIGIT )* )+ )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:53:17: ( '/' '1' .. '9' ( DIGIT )* )+
            {
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:53:17: ( '/' '1' .. '9' ( DIGIT )* )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                switch ( input.LA(1) ) {
                case '/':
                    {
                    alt3=1;
                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:53:18: '/' '1' .. '9' ( DIGIT )*
            	    {
            	    match('/'); 

            	    matchRange('1','9'); 

            	    // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:53:31: ( DIGIT )*
            	    loop2:
            	    do {
            	        int alt2=2;
            	        switch ( input.LA(1) ) {
            	        case '0':
            	        case '1':
            	        case '2':
            	        case '3':
            	        case '4':
            	        case '5':
            	        case '6':
            	        case '7':
            	        case '8':
            	        case '9':
            	            {
            	            alt2=1;
            	            }
            	            break;

            	        }

            	        switch (alt2) {
            	    	case 1 :
            	    	    // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:
            	    	    {
            	    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	    	        input.consume();
            	    	    }
            	    	    else {
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        recover(mse);
            	    	        throw mse;
            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop2;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CHILDSEQUENCE"

    // $ANTLR start "ENTITY"
    public final void mENTITY() throws RecognitionException {
        try {
            int _type = ENTITY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:56:13: ( '&' ( 'A' .. 'Z' | '_' | 'a' .. 'z' | DIGIT )+ ';' )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:56:17: '&' ( 'A' .. 'Z' | '_' | 'a' .. 'z' | DIGIT )+ ';'
            {
            match('&'); 

            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:56:21: ( 'A' .. 'Z' | '_' | 'a' .. 'z' | DIGIT )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                switch ( input.LA(1) ) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case '_':
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt4=1;
                    }
                    break;

                }

                switch (alt4) {
            	case 1 :
            	    // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ENTITY"

    // $ANTLR start "S"
    public final void mS() throws RecognitionException {
        try {
            int _type = S;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:59:13: ( ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' )+ )
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:59:17: ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' )+
            {
            // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:59:17: ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                switch ( input.LA(1) ) {
                case '\t':
                case '\n':
                case '\r':
                case ' ':
                    {
                    alt5=1;
                    }
                    break;

                }

                switch (alt5) {
            	case 1 :
            	    // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:
            	    {
            	    if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S"

    public void mTokens() throws RecognitionException {
        // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:8: ( ESCCIRC | ESCLBRACE | ESCRBRACE | COLON | EQUAL | CIRC | LBRACE | RBRACE | XPOINTER | XPATH | ELEMENT | XMLNS | STRINGRANGE | RANGETO | POINT | RANGE | COVERINGRANGE | RANGEINSIDE | STARTPOINT | HERE | ORIGIN | DIGIT | SPECIALCARS | NCNAME | CHILDSEQUENCE | ENTITY | S )
        int alt6=27;
        alt6 = dfa6.predict(input);
        switch (alt6) {
            case 1 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:10: ESCCIRC
                {
                mESCCIRC(); 


                }
                break;
            case 2 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:18: ESCLBRACE
                {
                mESCLBRACE(); 


                }
                break;
            case 3 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:28: ESCRBRACE
                {
                mESCRBRACE(); 


                }
                break;
            case 4 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:38: COLON
                {
                mCOLON(); 


                }
                break;
            case 5 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:44: EQUAL
                {
                mEQUAL(); 


                }
                break;
            case 6 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:50: CIRC
                {
                mCIRC(); 


                }
                break;
            case 7 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:55: LBRACE
                {
                mLBRACE(); 


                }
                break;
            case 8 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:62: RBRACE
                {
                mRBRACE(); 


                }
                break;
            case 9 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:69: XPOINTER
                {
                mXPOINTER(); 


                }
                break;
            case 10 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:78: XPATH
                {
                mXPATH(); 


                }
                break;
            case 11 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:84: ELEMENT
                {
                mELEMENT(); 


                }
                break;
            case 12 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:92: XMLNS
                {
                mXMLNS(); 


                }
                break;
            case 13 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:98: STRINGRANGE
                {
                mSTRINGRANGE(); 


                }
                break;
            case 14 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:110: RANGETO
                {
                mRANGETO(); 


                }
                break;
            case 15 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:118: POINT
                {
                mPOINT(); 


                }
                break;
            case 16 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:124: RANGE
                {
                mRANGE(); 


                }
                break;
            case 17 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:130: COVERINGRANGE
                {
                mCOVERINGRANGE(); 


                }
                break;
            case 18 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:144: RANGEINSIDE
                {
                mRANGEINSIDE(); 


                }
                break;
            case 19 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:156: STARTPOINT
                {
                mSTARTPOINT(); 


                }
                break;
            case 20 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:167: HERE
                {
                mHERE(); 


                }
                break;
            case 21 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:172: ORIGIN
                {
                mORIGIN(); 


                }
                break;
            case 22 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:179: DIGIT
                {
                mDIGIT(); 


                }
                break;
            case 23 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:185: SPECIALCARS
                {
                mSPECIALCARS(); 


                }
                break;
            case 24 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:197: NCNAME
                {
                mNCNAME(); 


                }
                break;
            case 25 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:204: CHILDSEQUENCE
                {
                mCHILDSEQUENCE(); 


                }
                break;
            case 26 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:218: ENTITY
                {
                mENTITY(); 


                }
                break;
            case 27 :
                // org/etourdot/xincproc/xpointer/grammar/XPointerLexer.g:1:225: S
                {
                mS(); 


                }
                break;

        }

    }


    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA6_eotS =
        "\1\uffff\1\30\4\uffff\10\21\1\uffff\2\22\10\uffff\11\21\1\uffff"+
        "\24\21\1\102\2\21\1\105\1\106\3\21\1\113\1\114\1\21\1\uffff\2\21"+
        "\2\uffff\4\21\2\uffff\1\21\1\126\1\21\1\130\5\21\1\uffff\1\136\1"+
        "\uffff\2\21\1\141\2\21\1\uffff\2\21\1\uffff\7\21\1\155\2\21\1\160"+
        "\1\uffff\1\161\1\21\2\uffff\1\21\1\164\1\uffff";
    static final String DFA6_eofS =
        "\165\uffff";
    static final String DFA6_minS =
        "\1\11\1\50\4\uffff\1\155\1\154\1\164\1\141\2\157\1\145\1\162\1\uffff"+
        "\1\55\1\61\10\uffff\1\141\1\154\1\145\1\141\1\156\1\151\1\166\1"+
        "\162\1\151\1\uffff\1\151\1\164\1\156\1\155\1\151\1\162\1\147\1\156"+
        "\2\145\1\147\1\156\1\150\1\163\1\145\1\156\1\164\1\145\1\164\1\162"+
        "\1\55\1\151\1\164\2\55\1\156\1\147\3\55\1\151\1\uffff\1\156\1\145"+
        "\2\uffff\1\164\1\55\1\160\1\151\2\uffff\1\156\1\55\1\162\1\55\1"+
        "\162\2\157\1\156\1\147\1\uffff\1\55\1\uffff\1\141\1\151\1\55\1\163"+
        "\1\55\1\uffff\2\156\1\uffff\1\151\1\162\1\147\1\164\1\144\1\141"+
        "\1\145\1\55\1\145\1\156\1\55\1\uffff\1\55\1\147\2\uffff\1\145\1"+
        "\55\1\uffff";
    static final String DFA6_maxS =
        "\1\ufffd\1\136\4\uffff\1\160\1\154\1\164\1\141\2\157\1\145\1\162"+
        "\1\uffff\1\ufffd\1\71\10\uffff\1\157\1\154\1\145\1\162\1\156\1\151"+
        "\1\166\1\162\1\151\1\uffff\1\151\1\164\1\156\1\155\1\151\1\162\1"+
        "\147\1\156\2\145\1\147\1\156\1\150\1\163\1\145\1\156\1\164\1\145"+
        "\1\164\1\162\1\ufffd\1\151\1\164\2\ufffd\1\156\1\147\1\55\2\ufffd"+
        "\1\151\1\uffff\1\156\1\145\2\uffff\1\164\1\55\1\160\1\164\2\uffff"+
        "\1\156\1\ufffd\1\162\1\ufffd\1\162\2\157\1\156\1\147\1\uffff\1\ufffd"+
        "\1\uffff\1\141\1\151\1\ufffd\1\163\1\55\1\uffff\2\156\1\uffff\1"+
        "\151\1\162\1\147\1\164\1\144\1\141\1\145\1\ufffd\1\145\1\156\1\ufffd"+
        "\1\uffff\1\ufffd\1\147\2\uffff\1\145\1\ufffd\1\uffff";
    static final String DFA6_acceptS =
        "\2\uffff\1\4\1\5\1\7\1\10\10\uffff\1\26\2\uffff\1\30\1\27\1\32\1"+
        "\33\1\1\1\2\1\3\1\6\11\uffff\1\31\37\uffff\1\24\2\uffff\1\12\1\14"+
        "\4\uffff\1\20\1\17\11\uffff\1\25\1\uffff\1\13\5\uffff\1\11\2\uffff"+
        "\1\16\13\uffff\1\23\2\uffff\1\15\1\22\2\uffff\1\21";
    static final String DFA6_specialS =
        "\165\uffff}>";
    static final String[] DFA6_transitionS = {
            "\2\24\2\uffff\1\24\22\uffff\1\24\1\uffff\1\22\3\uffff\1\23\1"+
            "\22\1\4\1\5\1\22\1\uffff\3\22\1\20\12\16\1\2\1\uffff\1\22\1"+
            "\3\1\22\1\uffff\1\22\32\21\1\22\1\uffff\1\22\1\1\1\17\1\uffff"+
            "\2\21\1\13\1\21\1\7\2\21\1\14\6\21\1\15\1\12\1\21\1\11\1\10"+
            "\4\21\1\6\2\21\105\uffff\27\21\1\uffff\37\21\1\uffff\u0208\21"+
            "\160\uffff\16\21\1\uffff\u1c81\21\14\uffff\2\21\142\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            "\1\26\1\27\64\uffff\1\25",
            "",
            "",
            "",
            "",
            "\1\32\2\uffff\1\31",
            "\1\33",
            "\1\34",
            "\1\35",
            "\1\36",
            "\1\37",
            "\1\40",
            "\1\41",
            "",
            "\2\21\1\uffff\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21"+
            "\74\uffff\1\21\10\uffff\27\21\1\uffff\37\21\1\uffff\u0286\21"+
            "\1\uffff\u1c81\21\14\uffff\2\21\61\uffff\2\21\57\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            "\11\42",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\44\15\uffff\1\43",
            "\1\45",
            "\1\46",
            "\1\50\20\uffff\1\47",
            "\1\51",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "",
            "\1\56",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\2\21\1\uffff\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21"+
            "\74\uffff\1\21\10\uffff\27\21\1\uffff\37\21\1\uffff\u0286\21"+
            "\1\uffff\u1c81\21\14\uffff\2\21\61\uffff\2\21\57\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            "\1\103",
            "\1\104",
            "\2\21\1\uffff\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21"+
            "\74\uffff\1\21\10\uffff\27\21\1\uffff\37\21\1\uffff\u0286\21"+
            "\1\uffff\u1c81\21\14\uffff\2\21\61\uffff\2\21\57\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            "\2\21\1\uffff\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21"+
            "\74\uffff\1\21\10\uffff\27\21\1\uffff\37\21\1\uffff\u0286\21"+
            "\1\uffff\u1c81\21\14\uffff\2\21\61\uffff\2\21\57\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112\1\21\1\uffff\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff"+
            "\32\21\74\uffff\1\21\10\uffff\27\21\1\uffff\37\21\1\uffff\u0286"+
            "\21\1\uffff\u1c81\21\14\uffff\2\21\61\uffff\2\21\57\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            "\2\21\1\uffff\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21"+
            "\74\uffff\1\21\10\uffff\27\21\1\uffff\37\21\1\uffff\u0286\21"+
            "\1\uffff\u1c81\21\14\uffff\2\21\61\uffff\2\21\57\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            "\1\115",
            "",
            "\1\116",
            "\1\117",
            "",
            "",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\124\12\uffff\1\123",
            "",
            "",
            "\1\125",
            "\2\21\1\uffff\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21"+
            "\74\uffff\1\21\10\uffff\27\21\1\uffff\37\21\1\uffff\u0286\21"+
            "\1\uffff\u1c81\21\14\uffff\2\21\61\uffff\2\21\57\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            "\1\127",
            "\2\21\1\uffff\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21"+
            "\74\uffff\1\21\10\uffff\27\21\1\uffff\37\21\1\uffff\u0286\21"+
            "\1\uffff\u1c81\21\14\uffff\2\21\61\uffff\2\21\57\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "",
            "\2\21\1\uffff\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21"+
            "\74\uffff\1\21\10\uffff\27\21\1\uffff\37\21\1\uffff\u0286\21"+
            "\1\uffff\u1c81\21\14\uffff\2\21\61\uffff\2\21\57\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            "",
            "\1\137",
            "\1\140",
            "\2\21\1\uffff\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21"+
            "\74\uffff\1\21\10\uffff\27\21\1\uffff\37\21\1\uffff\u0286\21"+
            "\1\uffff\u1c81\21\14\uffff\2\21\61\uffff\2\21\57\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            "\1\142",
            "\1\143",
            "",
            "\1\144",
            "\1\145",
            "",
            "\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\2\21\1\uffff\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21"+
            "\74\uffff\1\21\10\uffff\27\21\1\uffff\37\21\1\uffff\u0286\21"+
            "\1\uffff\u1c81\21\14\uffff\2\21\61\uffff\2\21\57\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            "\1\156",
            "\1\157",
            "\2\21\1\uffff\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21"+
            "\74\uffff\1\21\10\uffff\27\21\1\uffff\37\21\1\uffff\u0286\21"+
            "\1\uffff\u1c81\21\14\uffff\2\21\61\uffff\2\21\57\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            "",
            "\2\21\1\uffff\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21"+
            "\74\uffff\1\21\10\uffff\27\21\1\uffff\37\21\1\uffff\u0286\21"+
            "\1\uffff\u1c81\21\14\uffff\2\21\61\uffff\2\21\57\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            "\1\162",
            "",
            "",
            "\1\163",
            "\2\21\1\uffff\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21"+
            "\74\uffff\1\21\10\uffff\27\21\1\uffff\37\21\1\uffff\u0286\21"+
            "\1\uffff\u1c81\21\14\uffff\2\21\61\uffff\2\21\57\uffff\u0120"+
            "\21\u0a70\uffff\u03f0\21\21\uffff\ua7ff\21\u2100\uffff\u04d0"+
            "\21\40\uffff\u020e\21",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( ESCCIRC | ESCLBRACE | ESCRBRACE | COLON | EQUAL | CIRC | LBRACE | RBRACE | XPOINTER | XPATH | ELEMENT | XMLNS | STRINGRANGE | RANGETO | POINT | RANGE | COVERINGRANGE | RANGEINSIDE | STARTPOINT | HERE | ORIGIN | DIGIT | SPECIALCARS | NCNAME | CHILDSEQUENCE | ENTITY | S );";
        }
    }
 

}