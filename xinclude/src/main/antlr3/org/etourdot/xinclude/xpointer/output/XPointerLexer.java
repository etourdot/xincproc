// $ANTLR 3.2 Sep 23, 2009 12:02:23 D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g 2011-05-15 12:05:27

	package org.etourdot.xinclude.xpointer;
	import javax.xml.namespace.QName;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class XPointerLexer extends Lexer {
    public static final int CIRC=12;
    public static final int RBRACE=14;
    public static final int NCNAMECHAR=17;
    public static final int LBRACE=13;
    public static final int NCNAMESTARTCHAR=16;
    public static final int ESCCIRC=8;
    public static final int ESCLBRACE=9;
    public static final int S=19;
    public static final int EOF=-1;
    public static final int XMLNS=7;
    public static final int COLON=11;
    public static final int NCNAME=20;
    public static final int NORMALCHAR=18;
    public static final int XPATH=5;
    public static final int ESCRBRACE=10;
    public static final int XPOINTER=4;
    public static final int EQUAL=15;
    public static final int ELEMENT=6;
    public static final int FNNAME=21;

    // delegates
    // delegators

    public XPointerLexer() {;} 
    public XPointerLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public XPointerLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g"; }

    // $ANTLR start "ESCCIRC"
    public final void mESCCIRC() throws RecognitionException {
        try {
            int _type = ESCCIRC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:50:10: ( '^^' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:50:13: '^^'
            {
            match("^^"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ESCCIRC"

    // $ANTLR start "ESCLBRACE"
    public final void mESCLBRACE() throws RecognitionException {
        try {
            int _type = ESCLBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:51:11: ( '^(' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:51:14: '^('
            {
            match("^("); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ESCLBRACE"

    // $ANTLR start "ESCRBRACE"
    public final void mESCRBRACE() throws RecognitionException {
        try {
            int _type = ESCRBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:52:12: ( '^)' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:52:15: '^)'
            {
            match("^)"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ESCRBRACE"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:53:15: ( ':' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:53:17: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "CIRC"
    public final void mCIRC() throws RecognitionException {
        try {
            int _type = CIRC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:54:7: ( '^' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:54:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CIRC"

    // $ANTLR start "LBRACE"
    public final void mLBRACE() throws RecognitionException {
        try {
            int _type = LBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:55:9: ( '(' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:55:11: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACE"

    // $ANTLR start "RBRACE"
    public final void mRBRACE() throws RecognitionException {
        try {
            int _type = RBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:56:9: ( ')' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:56:12: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACE"

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:57:8: ( '=' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:57:11: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "XPOINTER"
    public final void mXPOINTER() throws RecognitionException {
        try {
            int _type = XPOINTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:58:10: ( 'xpointer' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:58:12: 'xpointer'
            {
            match("xpointer"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XPOINTER"

    // $ANTLR start "XPATH"
    public final void mXPATH() throws RecognitionException {
        try {
            int _type = XPATH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:59:8: ( 'xpath' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:59:10: 'xpath'
            {
            match("xpath"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XPATH"

    // $ANTLR start "ELEMENT"
    public final void mELEMENT() throws RecognitionException {
        try {
            int _type = ELEMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:60:10: ( 'element' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:60:12: 'element'
            {
            match("element"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELEMENT"

    // $ANTLR start "XMLNS"
    public final void mXMLNS() throws RecognitionException {
        try {
            int _type = XMLNS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:61:8: ( 'xmlns' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:61:10: 'xmlns'
            {
            match("xmlns"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XMLNS"

    // $ANTLR start "NCNAMESTARTCHAR"
    public final void mNCNAMESTARTCHAR() throws RecognitionException {
        try {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:64:3: ( 'A' .. 'Z' | '_' | 'a' .. 'z' | '\\u00C0' .. '\\u00D6' | '\\u00D8' .. '\\u00F6' | '\\u00F8' .. '\\u02FF' | '\\u0370' .. '\\u037D' | '\\u037F' .. '\\u1FFF' | '\\u200C' .. '\\u200D' | '\\u2070' .. '\\u218F' | '\\u2C00' .. '\\u2FEF' | '\\u3001' .. '\\uD7FF' | '\\uF900' .. '\\uFDCF' | '\\uFDF0' .. '\\uFFFD' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u02FF')||(input.LA(1)>='\u0370' && input.LA(1)<='\u037D')||(input.LA(1)>='\u037F' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u200C' && input.LA(1)<='\u200D')||(input.LA(1)>='\u2070' && input.LA(1)<='\u218F')||(input.LA(1)>='\u2C00' && input.LA(1)<='\u2FEF')||(input.LA(1)>='\u3001' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFDCF')||(input.LA(1)>='\uFDF0' && input.LA(1)<='\uFFFD') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "NCNAMESTARTCHAR"

    // $ANTLR start "NCNAMECHAR"
    public final void mNCNAMECHAR() throws RecognitionException {
        try {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:71:3: ( NCNAMESTARTCHAR | '-' | '.' | '0' .. '9' | '\\u00B7' | '\\u0300' .. '\\u036F' | '\\u203F' .. '\\u2040' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:
            {
            if ( (input.LA(1)>='-' && input.LA(1)<='.')||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||input.LA(1)=='\u00B7'||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u037D')||(input.LA(1)>='\u037F' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u200C' && input.LA(1)<='\u200D')||(input.LA(1)>='\u203F' && input.LA(1)<='\u2040')||(input.LA(1)>='\u2070' && input.LA(1)<='\u218F')||(input.LA(1)>='\u2C00' && input.LA(1)<='\u2FEF')||(input.LA(1)>='\u3001' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFDCF')||(input.LA(1)>='\uFDF0' && input.LA(1)<='\uFFFD') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "NCNAMECHAR"

    // $ANTLR start "NORMALCHAR"
    public final void mNORMALCHAR() throws RecognitionException {
        try {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:76:3: ( NCNAMECHAR | '\\u0020' .. '\\u0027' | '\\u002A' .. '\\u002F' | '\\u003B' .. '\\u003C' | '\\u003E' .. '\\u0040' | '\\u005B' .. '\\u005D' )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:
            {
            if ( (input.LA(1)>=' ' && input.LA(1)<='\'')||(input.LA(1)>='*' && input.LA(1)<='9')||(input.LA(1)>=';' && input.LA(1)<='<')||(input.LA(1)>='>' && input.LA(1)<=']')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||input.LA(1)=='\u00B7'||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u037D')||(input.LA(1)>='\u037F' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u200C' && input.LA(1)<='\u200D')||(input.LA(1)>='\u203F' && input.LA(1)<='\u2040')||(input.LA(1)>='\u2070' && input.LA(1)<='\u218F')||(input.LA(1)>='\u2C00' && input.LA(1)<='\u2FEF')||(input.LA(1)>='\u3001' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFDCF')||(input.LA(1)>='\uFDF0' && input.LA(1)<='\uFFFD') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "NORMALCHAR"

    // $ANTLR start "S"
    public final void mS() throws RecognitionException {
        try {
            int _type = S;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:80:4: ( ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' )+ )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:80:6: ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' )+
            {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:80:6: ( '\\u0009' | '\\u000A' | '\\u000D' | '\\u0020' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\t' && LA1_0<='\n')||LA1_0=='\r'||LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "S"

    // $ANTLR start "NCNAME"
    public final void mNCNAME() throws RecognitionException {
        try {
            int _type = NCNAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:81:9: ( NCNAMESTARTCHAR ( NCNAMECHAR )* )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:81:12: NCNAMESTARTCHAR ( NCNAMECHAR )*
            {
            mNCNAMESTARTCHAR(); 
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:81:28: ( NCNAMECHAR )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='-' && LA2_0<='.')||(LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')||LA2_0=='\u00B7'||(LA2_0>='\u00C0' && LA2_0<='\u00D6')||(LA2_0>='\u00D8' && LA2_0<='\u00F6')||(LA2_0>='\u00F8' && LA2_0<='\u037D')||(LA2_0>='\u037F' && LA2_0<='\u1FFF')||(LA2_0>='\u200C' && LA2_0<='\u200D')||(LA2_0>='\u203F' && LA2_0<='\u2040')||(LA2_0>='\u2070' && LA2_0<='\u218F')||(LA2_0>='\u2C00' && LA2_0<='\u2FEF')||(LA2_0>='\u3001' && LA2_0<='\uD7FF')||(LA2_0>='\uF900' && LA2_0<='\uFDCF')||(LA2_0>='\uFDF0' && LA2_0<='\uFFFD')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:81:28: NCNAMECHAR
            	    {
            	    mNCNAMECHAR(); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NCNAME"

    // $ANTLR start "FNNAME"
    public final void mFNNAME() throws RecognitionException {
        try {
            int _type = FNNAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:82:9: ( ( NORMALCHAR | ESCLBRACE | ESCRBRACE | ESCCIRC | COLON | EQUAL )* )
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:82:11: ( NORMALCHAR | ESCLBRACE | ESCRBRACE | ESCCIRC | COLON | EQUAL )*
            {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:82:11: ( NORMALCHAR | ESCLBRACE | ESCRBRACE | ESCCIRC | COLON | EQUAL )*
            loop3:
            do {
                int alt3=7;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=' ' && LA3_0<='\'')||(LA3_0>='*' && LA3_0<='9')||(LA3_0>=';' && LA3_0<='<')||(LA3_0>='>' && LA3_0<=']')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')||LA3_0=='\u00B7'||(LA3_0>='\u00C0' && LA3_0<='\u00D6')||(LA3_0>='\u00D8' && LA3_0<='\u00F6')||(LA3_0>='\u00F8' && LA3_0<='\u037D')||(LA3_0>='\u037F' && LA3_0<='\u1FFF')||(LA3_0>='\u200C' && LA3_0<='\u200D')||(LA3_0>='\u203F' && LA3_0<='\u2040')||(LA3_0>='\u2070' && LA3_0<='\u218F')||(LA3_0>='\u2C00' && LA3_0<='\u2FEF')||(LA3_0>='\u3001' && LA3_0<='\uD7FF')||(LA3_0>='\uF900' && LA3_0<='\uFDCF')||(LA3_0>='\uFDF0' && LA3_0<='\uFFFD')) ) {
                    alt3=1;
                }
                else if ( (LA3_0=='^') ) {
                    switch ( input.LA(2) ) {
                    case '(':
                        {
                        alt3=2;
                        }
                        break;
                    case ')':
                        {
                        alt3=3;
                        }
                        break;
                    case '^':
                        {
                        alt3=4;
                        }
                        break;

                    }

                }
                else if ( (LA3_0==':') ) {
                    alt3=5;
                }
                else if ( (LA3_0=='=') ) {
                    alt3=6;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:82:12: NORMALCHAR
            	    {
            	    mNORMALCHAR(); 

            	    }
            	    break;
            	case 2 :
            	    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:82:25: ESCLBRACE
            	    {
            	    mESCLBRACE(); 

            	    }
            	    break;
            	case 3 :
            	    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:82:37: ESCRBRACE
            	    {
            	    mESCRBRACE(); 

            	    }
            	    break;
            	case 4 :
            	    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:82:49: ESCCIRC
            	    {
            	    mESCCIRC(); 

            	    }
            	    break;
            	case 5 :
            	    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:82:59: COLON
            	    {
            	    mCOLON(); 

            	    }
            	    break;
            	case 6 :
            	    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:82:67: EQUAL
            	    {
            	    mEQUAL(); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FNNAME"

    public void mTokens() throws RecognitionException {
        // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:8: ( ESCCIRC | ESCLBRACE | ESCRBRACE | COLON | CIRC | LBRACE | RBRACE | EQUAL | XPOINTER | XPATH | ELEMENT | XMLNS | S | NCNAME | FNNAME )
        int alt4=15;
        alt4 = dfa4.predict(input);
        switch (alt4) {
            case 1 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:10: ESCCIRC
                {
                mESCCIRC(); 

                }
                break;
            case 2 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:18: ESCLBRACE
                {
                mESCLBRACE(); 

                }
                break;
            case 3 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:28: ESCRBRACE
                {
                mESCRBRACE(); 

                }
                break;
            case 4 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:38: COLON
                {
                mCOLON(); 

                }
                break;
            case 5 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:44: CIRC
                {
                mCIRC(); 

                }
                break;
            case 6 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:49: LBRACE
                {
                mLBRACE(); 

                }
                break;
            case 7 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:56: RBRACE
                {
                mRBRACE(); 

                }
                break;
            case 8 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:63: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 9 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:69: XPOINTER
                {
                mXPOINTER(); 

                }
                break;
            case 10 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:78: XPATH
                {
                mXPATH(); 

                }
                break;
            case 11 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:84: ELEMENT
                {
                mELEMENT(); 

                }
                break;
            case 12 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:92: XMLNS
                {
                mXMLNS(); 

                }
                break;
            case 13 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:98: S
                {
                mS(); 

                }
                break;
            case 14 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:100: NCNAME
                {
                mNCNAME(); 

                }
                break;
            case 15 :
                // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:1:107: FNNAME
                {
                mFNNAME(); 

                }
                break;

        }

    }


    protected DFA4 dfa4 = new DFA4(this);
    static final String DFA4_eotS =
        "\1\13\1\17\1\20\2\uffff\1\21\2\24\1\12\1\24\2\uffff\1\27\1\30\1"+
        "\31\3\uffff\2\24\1\uffff\2\24\3\uffff\11\24\1\47\1\50\2\24\2\uffff"+
        "\2\24\1\55\1\56\2\uffff";
    static final String DFA4_eofS =
        "\57\uffff";
    static final String DFA4_minS =
        "\1\11\1\50\1\40\2\uffff\5\40\2\uffff\3\40\3\uffff\2\40\1\uffff"+
        "\2\40\3\uffff\15\40\2\uffff\4\40\2\uffff";
    static final String DFA4_maxS =
        "\1\ufffd\1\136\1\ufffd\2\uffff\5\ufffd\2\uffff\3\ufffd\3\uffff"+
        "\2\ufffd\1\uffff\2\ufffd\3\uffff\15\ufffd\2\uffff\4\ufffd\2\uffff";
    static final String DFA4_acceptS =
        "\3\uffff\1\6\1\7\5\uffff\1\15\1\17\3\uffff\1\5\1\4\1\10\2\uffff"+
        "\1\16\2\uffff\1\1\1\2\1\3\15\uffff\1\12\1\14\4\uffff\1\13\1\11";
    static final String DFA4_specialS =
        "\57\uffff}>";
    static final String[] DFA4_transitionS = {
            "\2\12\2\uffff\1\12\22\uffff\1\10\7\uffff\1\3\1\4\20\uffff\1"+
            "\2\2\uffff\1\5\3\uffff\32\11\3\uffff\1\1\1\11\1\uffff\4\11\1"+
            "\7\22\11\1\6\2\11\105\uffff\27\11\1\uffff\37\11\1\uffff\u0208"+
            "\11\160\uffff\16\11\1\uffff\u1c81\11\14\uffff\2\11\142\uffff"+
            "\u0120\11\u0a70\uffff\u03f0\11\21\uffff\ua7ff\11\u2100\uffff"+
            "\u04d0\11\40\uffff\u020e\11",
            "\1\15\1\16\64\uffff\1\14",
            "\10\13\2\uffff\66\13\1\uffff\32\13\74\uffff\1\13\10\uffff"+
            "\27\13\1\uffff\37\13\1\uffff\u0286\13\1\uffff\u1c81\13\14\uffff"+
            "\2\13\61\uffff\2\13\57\uffff\u0120\13\u0a70\uffff\u03f0\13\21"+
            "\uffff\ua7ff\13\u2100\uffff\u04d0\13\40\uffff\u020e\13",
            "",
            "",
            "\10\13\2\uffff\66\13\1\uffff\32\13\74\uffff\1\13\10\uffff"+
            "\27\13\1\uffff\37\13\1\uffff\u0286\13\1\uffff\u1c81\13\14\uffff"+
            "\2\13\61\uffff\2\13\57\uffff\u0120\13\u0a70\uffff\u03f0\13\21"+
            "\uffff\ua7ff\13\u2100\uffff\u04d0\13\40\uffff\u020e\13",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\14\25\1\23\2\25\1\22\12\25\74\uffff\1\25\10\uffff\27"+
            "\25\1\uffff\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff"+
            "\2\25\61\uffff\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21"+
            "\uffff\ua7ff\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\13\25\1\26\16\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\1\10\7\13\2\uffff\66\13\1\uffff\32\13\74\uffff\1\13\10\uffff"+
            "\27\13\1\uffff\37\13\1\uffff\u0286\13\1\uffff\u1c81\13\14\uffff"+
            "\2\13\61\uffff\2\13\57\uffff\u0120\13\u0a70\uffff\u03f0\13\21"+
            "\uffff\ua7ff\13\u2100\uffff\u04d0\13\40\uffff\u020e\13",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\32\25\74\uffff\1\25\10\uffff\27\25\1\uffff\37\25\1\uffff"+
            "\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff\2\25\57\uffff"+
            "\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff\25\u2100\uffff"+
            "\u04d0\25\40\uffff\u020e\25",
            "",
            "",
            "\10\13\2\uffff\66\13\1\uffff\32\13\74\uffff\1\13\10\uffff"+
            "\27\13\1\uffff\37\13\1\uffff\u0286\13\1\uffff\u1c81\13\14\uffff"+
            "\2\13\61\uffff\2\13\57\uffff\u0120\13\u0a70\uffff\u03f0\13\21"+
            "\uffff\ua7ff\13\u2100\uffff\u04d0\13\40\uffff\u020e\13",
            "\10\13\2\uffff\66\13\1\uffff\32\13\74\uffff\1\13\10\uffff"+
            "\27\13\1\uffff\37\13\1\uffff\u0286\13\1\uffff\u1c81\13\14\uffff"+
            "\2\13\61\uffff\2\13\57\uffff\u0120\13\u0a70\uffff\u03f0\13\21"+
            "\uffff\ua7ff\13\u2100\uffff\u04d0\13\40\uffff\u020e\13",
            "\10\13\2\uffff\66\13\1\uffff\32\13\74\uffff\1\13\10\uffff"+
            "\27\13\1\uffff\37\13\1\uffff\u0286\13\1\uffff\u1c81\13\14\uffff"+
            "\2\13\61\uffff\2\13\57\uffff\u0120\13\u0a70\uffff\u03f0\13\21"+
            "\uffff\ua7ff\13\u2100\uffff\u04d0\13\40\uffff\u020e\13",
            "",
            "",
            "",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\1\33\15\25\1\32\13\25\74\uffff\1\25\10\uffff\27\25\1"+
            "\uffff\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25"+
            "\61\uffff\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff"+
            "\ua7ff\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\13\25\1\34\16\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\32\25\74\uffff\1\25\10\uffff\27\25\1\uffff\37\25\1\uffff"+
            "\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff\2\25\57\uffff"+
            "\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff\25\u2100\uffff"+
            "\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\4\25\1\35\25\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "",
            "",
            "",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\10\25\1\36\21\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\23\25\1\37\6\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\15\25\1\40\14\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\14\25\1\41\15\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\15\25\1\42\14\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\7\25\1\43\22\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\22\25\1\44\7\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\4\25\1\45\25\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\23\25\1\46\6\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\32\25\74\uffff\1\25\10\uffff\27\25\1\uffff\37\25\1\uffff"+
            "\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff\2\25\57\uffff"+
            "\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff\25\u2100\uffff"+
            "\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\32\25\74\uffff\1\25\10\uffff\27\25\1\uffff\37\25\1\uffff"+
            "\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff\2\25\57\uffff"+
            "\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff\25\u2100\uffff"+
            "\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\15\25\1\51\14\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\4\25\1\52\25\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "",
            "",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\23\25\1\53\6\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\21\25\1\54\10\25\74\uffff\1\25\10\uffff\27\25\1\uffff"+
            "\37\25\1\uffff\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff"+
            "\2\25\57\uffff\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff"+
            "\25\u2100\uffff\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\32\25\74\uffff\1\25\10\uffff\27\25\1\uffff\37\25\1\uffff"+
            "\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff\2\25\57\uffff"+
            "\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff\25\u2100\uffff"+
            "\u04d0\25\40\uffff\u020e\25",
            "\10\13\2\uffff\3\13\2\25\1\13\12\25\7\13\32\25\4\13\1\25\1"+
            "\uffff\32\25\74\uffff\1\25\10\uffff\27\25\1\uffff\37\25\1\uffff"+
            "\u0286\25\1\uffff\u1c81\25\14\uffff\2\25\61\uffff\2\25\57\uffff"+
            "\u0120\25\u0a70\uffff\u03f0\25\21\uffff\ua7ff\25\u2100\uffff"+
            "\u04d0\25\40\uffff\u020e\25",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( ESCCIRC | ESCLBRACE | ESCRBRACE | COLON | CIRC | LBRACE | RBRACE | EQUAL | XPOINTER | XPATH | ELEMENT | XMLNS | S | NCNAME | FNNAME );";
        }
    }
 

}