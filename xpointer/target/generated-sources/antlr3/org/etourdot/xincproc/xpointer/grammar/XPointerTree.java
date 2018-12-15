// $ANTLR 3.4 org/etourdot/xincproc/xpointer/grammar/XPointerTree.g 2018-12-15 17:34:39

    package org.etourdot.xincproc.xpointer.grammar;

    import org.etourdot.xincproc.xpointer.model.*;
    import org.etourdot.xincproc.xpointer.exceptions.*;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class XPointerTree extends AbstractXPointerTree {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CHILDSEQUENCE", "CIRC", "COLON", "COVERINGRANGE", "DIGIT", "ELEMENT", "ENTITY", "EQUAL", "ESCCIRC", "ESCLBRACE", "ESCRBRACE", "HERE", "LBRACE", "NCNAME", "NCNAMECHAR", "NCNAMESTARTCHAR", "ORIGIN", "POINT", "RANGE", "RANGEINSIDE", "RANGETO", "RBRACE", "S", "SPECIALCARS", "STARTPOINT", "STRINGRANGE", "XMLNS", "XPATH", "XPOINTER", "COVERINGRANG", "DATAS", "ELEMENTSCHEME", "FUNCTION", "LOCALNAME", "NAMESPACE", "OTHERSCHEME", "POINTER", "PREFIX", "QNAME", "SCHEMEBASED", "XMLNSSCHEME", "XPATHSCHEME", "XPOINTERSCHEME"
    };

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
    public static final int COVERINGRANG=33;
    public static final int DATAS=34;
    public static final int ELEMENTSCHEME=35;
    public static final int FUNCTION=36;
    public static final int LOCALNAME=37;
    public static final int NAMESPACE=38;
    public static final int OTHERSCHEME=39;
    public static final int POINTER=40;
    public static final int PREFIX=41;
    public static final int QNAME=42;
    public static final int SCHEMEBASED=43;
    public static final int XMLNSSCHEME=44;
    public static final int XPATHSCHEME=45;
    public static final int XPOINTERSCHEME=46;

    // delegates
    public AbstractXPointerTree[] getDelegates() {
        return new AbstractXPointerTree[] {};
    }

    // delegators


    public XPointerTree(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public XPointerTree(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
        this.state.ruleMemo = new HashMap[37+1];
         

    }

    public String[] getTokenNames() { return XPointerTree.tokenNames; }
    public String getGrammarFileName() { return "org/etourdot/xincproc/xpointer/grammar/XPointerTree.g"; }



    // $ANTLR start "pointer"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:18:1: pointer returns [Pointer xpointer] : ( ^( POINTER s= shorthand ) | ^( POINTER sch= schemebased ) | POINTER );
    public final Pointer pointer() throws RecognitionException {
        Pointer xpointer = null;

        int pointer_StartIndex = input.index();

        ShortHand s =null;

        List sch =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return xpointer; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:19:5: ( ^( POINTER s= shorthand ) | ^( POINTER sch= schemebased ) | POINTER )
            int alt1=3;
            switch ( input.LA(1) ) {
            case POINTER:
                {
                switch ( input.LA(2) ) {
                case DOWN:
                    {
                    switch ( input.LA(3) ) {
                    case NCNAME:
                        {
                        alt1=1;
                        }
                        break;
                    case ELEMENTSCHEME:
                    case OTHERSCHEME:
                    case XMLNSSCHEME:
                    case XPATHSCHEME:
                    case XPOINTERSCHEME:
                        {
                        alt1=2;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return xpointer;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 1, 2, input);

                        throw nvae;

                    }

                    }
                    break;
                case EOF:
                    {
                    alt1=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return xpointer;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;

                }

                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return xpointer;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }

            switch (alt1) {
                case 1 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:19:7: ^( POINTER s= shorthand )
                    {
                    match(input,POINTER,FOLLOW_POINTER_in_pointer104); if (state.failed) return xpointer;

                    match(input, Token.DOWN, null); if (state.failed) return xpointer;
                    pushFollow(FOLLOW_shorthand_in_pointer108);
                    s=shorthand();

                    state._fsp--;
                    if (state.failed) return xpointer;

                    match(input, Token.UP, null); if (state.failed) return xpointer;


                    if ( state.backtracking==0 ) {
                                xpointer = new Pointer(s);
                            }

                    }
                    break;
                case 2 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:23:4: ^( POINTER sch= schemebased )
                    {
                    match(input,POINTER,FOLLOW_POINTER_in_pointer125); if (state.failed) return xpointer;

                    match(input, Token.DOWN, null); if (state.failed) return xpointer;
                    pushFollow(FOLLOW_schemebased_in_pointer129);
                    sch=schemebased();

                    state._fsp--;
                    if (state.failed) return xpointer;

                    match(input, Token.UP, null); if (state.failed) return xpointer;


                    if ( state.backtracking==0 ) {
                    	        xpointer =  new Pointer(sch);
                    	    }

                    }
                    break;
                case 3 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:27:6: POINTER
                    {
                    match(input,POINTER,FOLLOW_POINTER_in_pointer144); if (state.failed) return xpointer;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 1, pointer_StartIndex); }

        }
        return xpointer;
    }
    // $ANTLR end "pointer"



    // $ANTLR start "shorthand"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:30:1: shorthand returns [ShortHand shortHand] : n= NCNAME ;
    public final ShortHand shorthand() throws RecognitionException {
        ShortHand shortHand = null;

        int shorthand_StartIndex = input.index();

        CommonTree n=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return shortHand; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:31:2: (n= NCNAME )
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:31:4: n= NCNAME
            {
            n=(CommonTree)match(input,NCNAME,FOLLOW_NCNAME_in_shorthand161); if (state.failed) return shortHand;

            if ( state.backtracking==0 ) {
            	        shortHand = PointerHelper.createShortHand((n!=null?n.getText():null));
            	    }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 2, shorthand_StartIndex); }

        }
        return shortHand;
    }
    // $ANTLR end "shorthand"


    protected static class schemebased_scope {
        List pointerParts;
    }
    protected Stack schemebased_stack = new Stack();



    // $ANTLR start "schemebased"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:37:1: schemebased returns [List parts] : ( pointerpart )+ ;
    public final List schemebased() throws RecognitionException {
        schemebased_stack.push(new schemebased_scope());
        List parts = null;

        int schemebased_StartIndex = input.index();


            ((schemebased_scope)schemebased_stack.peek()).pointerParts = new ArrayList();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return parts; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:44:2: ( ( pointerpart )+ )
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:44:4: ( pointerpart )+
            {
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:44:4: ( pointerpart )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                switch ( input.LA(1) ) {
                case ELEMENTSCHEME:
                case OTHERSCHEME:
                case XMLNSSCHEME:
                case XPATHSCHEME:
                case XPOINTERSCHEME:
                    {
                    alt2=1;
                    }
                    break;

                }

                switch (alt2) {
            	case 1 :
            	    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:44:4: pointerpart
            	    {
            	    pushFollow(FOLLOW_pointerpart_in_schemebased192);
            	    pointerpart();

            	    state._fsp--;
            	    if (state.failed) return parts;

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
            	    if (state.backtracking>0) {state.failed=true; return parts;}
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            if ( state.backtracking==0 ) {
            	        parts = ((schemebased_scope)schemebased_stack.peek()).pointerParts;
            	    }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 3, schemebased_StartIndex); }

            schemebased_stack.pop();
        }
        return parts;
    }
    // $ANTLR end "schemebased"



    // $ANTLR start "pointerpart"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:50:1: pointerpart : ( pointerpart_element | pointerpart_xpath | pointerpart_xpointer | pointerpart_xmlns | pointerpart_otherscheme );
    public final void pointerpart() throws RecognitionException {
        int pointerpart_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return ; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:51:2: ( pointerpart_element | pointerpart_xpath | pointerpart_xpointer | pointerpart_xmlns | pointerpart_otherscheme )
            int alt3=5;
            switch ( input.LA(1) ) {
            case ELEMENTSCHEME:
                {
                alt3=1;
                }
                break;
            case XPATHSCHEME:
                {
                alt3=2;
                }
                break;
            case XPOINTERSCHEME:
                {
                alt3=3;
                }
                break;
            case XMLNSSCHEME:
                {
                alt3=4;
                }
                break;
            case OTHERSCHEME:
                {
                alt3=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:51:4: pointerpart_element
                    {
                    pushFollow(FOLLOW_pointerpart_element_in_pointerpart211);
                    pointerpart_element();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:52:6: pointerpart_xpath
                    {
                    pushFollow(FOLLOW_pointerpart_xpath_in_pointerpart218);
                    pointerpart_xpath();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:53:4: pointerpart_xpointer
                    {
                    pushFollow(FOLLOW_pointerpart_xpointer_in_pointerpart223);
                    pointerpart_xpointer();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:54:5: pointerpart_xmlns
                    {
                    pushFollow(FOLLOW_pointerpart_xmlns_in_pointerpart229);
                    pointerpart_xmlns();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:55:4: pointerpart_otherscheme
                    {
                    pushFollow(FOLLOW_pointerpart_otherscheme_in_pointerpart234);
                    pointerpart_otherscheme();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 4, pointerpart_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "pointerpart"



    // $ANTLR start "pointerpart_element"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:58:1: pointerpart_element : ^( ELEMENTSCHEME (d1= elementschemedata )? ) ;
    public final void pointerpart_element() throws RecognitionException {
        int pointerpart_element_StartIndex = input.index();

        XPointerTree.elementschemedata_return d1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return ; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:59:5: ( ^( ELEMENTSCHEME (d1= elementschemedata )? ) )
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:59:9: ^( ELEMENTSCHEME (d1= elementschemedata )? )
            {
            match(input,ELEMENTSCHEME,FOLLOW_ELEMENTSCHEME_in_pointerpart_element251); if (state.failed) return ;

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return ;
                // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:59:27: (d1= elementschemedata )?
                int alt4=2;
                switch ( input.LA(1) ) {
                    case CHILDSEQUENCE:
                    case ELEMENT:
                        {
                        alt4=1;
                        }
                        break;
                }

                switch (alt4) {
                    case 1 :
                        // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:59:27: d1= elementschemedata
                        {
                        pushFollow(FOLLOW_elementschemedata_in_pointerpart_element255);
                        d1=elementschemedata();

                        state._fsp--;
                        if (state.failed) return ;

                        }
                        break;

                }


                match(input, Token.UP, null); if (state.failed) return ;
            }


            if ( state.backtracking==0 ) {
                        ElementScheme elementScheme = PointerHelper.createElementScheme((d1!=null?d1.name:null), (d1!=null?d1.data:null));
                        if (elementScheme != null) {
                            ((schemebased_scope)schemebased_stack.peek()).pointerParts.add(elementScheme);
                         }
                    }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 5, pointerpart_element_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "pointerpart_element"



    // $ANTLR start "pointerpart_xpath"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:68:1: pointerpart_xpath : ^( XPATHSCHEME d2= xpathschemedata ) ;
    public final void pointerpart_xpath() throws RecognitionException {
        int pointerpart_xpath_StartIndex = input.index();

        String d2 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return ; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:69:5: ( ^( XPATHSCHEME d2= xpathschemedata ) )
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:69:9: ^( XPATHSCHEME d2= xpathschemedata )
            {
            match(input,XPATHSCHEME,FOLLOW_XPATHSCHEME_in_pointerpart_xpath287); if (state.failed) return ;

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return ;
                pushFollow(FOLLOW_xpathschemedata_in_pointerpart_xpath291);
                d2=xpathschemedata();

                state._fsp--;
                if (state.failed) return ;

                match(input, Token.UP, null); if (state.failed) return ;
            }


            if ( state.backtracking==0 ) {
                        ((schemebased_scope)schemebased_stack.peek()).pointerParts.add(PointerHelper.createXPathScheme(d2));
                    }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 6, pointerpart_xpath_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "pointerpart_xpath"



    // $ANTLR start "pointerpart_xpointer"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:75:1: pointerpart_xpointer : ^( XPOINTERSCHEME d3= xpathschemedata ) ;
    public final void pointerpart_xpointer() throws RecognitionException {
        int pointerpart_xpointer_StartIndex = input.index();

        String d3 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return ; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:76:5: ( ^( XPOINTERSCHEME d3= xpathschemedata ) )
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:76:9: ^( XPOINTERSCHEME d3= xpathschemedata )
            {
            match(input,XPOINTERSCHEME,FOLLOW_XPOINTERSCHEME_in_pointerpart_xpointer322); if (state.failed) return ;

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return ;
                pushFollow(FOLLOW_xpathschemedata_in_pointerpart_xpointer326);
                d3=xpathschemedata();

                state._fsp--;
                if (state.failed) return ;

                match(input, Token.UP, null); if (state.failed) return ;
            }


            if ( state.backtracking==0 ) {
                        ((schemebased_scope)schemebased_stack.peek()).pointerParts.add(PointerHelper.createXPointerScheme(d3));
                    }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 7, pointerpart_xpointer_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "pointerpart_xpointer"



    // $ANTLR start "pointerpart_xmlns"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:82:1: pointerpart_xmlns : ^( XMLNSSCHEME d4= xmlnsschemedata ) ;
    public final void pointerpart_xmlns() throws RecognitionException {
        int pointerpart_xmlns_StartIndex = input.index();

        XPointerTree.xmlnsschemedata_return d4 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return ; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:83:5: ( ^( XMLNSSCHEME d4= xmlnsschemedata ) )
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:83:9: ^( XMLNSSCHEME d4= xmlnsschemedata )
            {
            match(input,XMLNSSCHEME,FOLLOW_XMLNSSCHEME_in_pointerpart_xmlns357); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_xmlnsschemedata_in_pointerpart_xmlns361);
            d4=xmlnsschemedata();

            state._fsp--;
            if (state.failed) return ;

            match(input, Token.UP, null); if (state.failed) return ;


            if ( state.backtracking==0 ) {
                        XmlNsScheme xmlnsScheme = PointerHelper.createXmlNsScheme((d4!=null?d4.prefix:null), (d4!=null?d4.namespace:null));
                        if (xmlnsScheme != null)
                        {
                            ((schemebased_scope)schemebased_stack.peek()).pointerParts.add(xmlnsScheme);
                        }
                    }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 8, pointerpart_xmlns_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "pointerpart_xmlns"



    // $ANTLR start "pointerpart_otherscheme"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:93:1: pointerpart_otherscheme : ^( OTHERSCHEME q= qname s= schemedata ) ;
    public final void pointerpart_otherscheme() throws RecognitionException {
        int pointerpart_otherscheme_StartIndex = input.index();

        XPointerTree.qname_return q =null;

        String s =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return ; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:94:5: ( ^( OTHERSCHEME q= qname s= schemedata ) )
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:94:9: ^( OTHERSCHEME q= qname s= schemedata )
            {
            match(input,OTHERSCHEME,FOLLOW_OTHERSCHEME_in_pointerpart_otherscheme392); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_qname_in_pointerpart_otherscheme396);
            q=qname();

            state._fsp--;
            if (state.failed) return ;

            pushFollow(FOLLOW_schemedata_in_pointerpart_otherscheme400);
            s=schemedata();

            state._fsp--;
            if (state.failed) return ;

            match(input, Token.UP, null); if (state.failed) return ;


            if ( state.backtracking==0 ) {
                         emitErrorMessage("Warning: '" + (q!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(q.start),input.getTreeAdaptor().getTokenStopIndex(q.start))):null) + "' scheme is not supported");
                    }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 9, pointerpart_otherscheme_StartIndex); }

        }
        return ;
    }
    // $ANTLR end "pointerpart_otherscheme"


    public static class elementschemedata_return extends TreeRuleReturnScope {
        public String name;
        public String data;
    };


    // $ANTLR start "elementschemedata"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:100:1: elementschemedata returns [String name, String data] : ( ^( ELEMENT n= NCNAME ) | ^( ELEMENT n= NCNAME ) ^( CHILDSEQUENCE c1= childsequence ) | ^( CHILDSEQUENCE c2= childsequence ) );
    public final XPointerTree.elementschemedata_return elementschemedata() throws RecognitionException {
        XPointerTree.elementschemedata_return retval = new XPointerTree.elementschemedata_return();
        retval.start = input.LT(1);

        int elementschemedata_StartIndex = input.index();

        CommonTree n=null;
        XPointerTree.childsequence_return c1 =null;

        XPointerTree.childsequence_return c2 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:101:2: ( ^( ELEMENT n= NCNAME ) | ^( ELEMENT n= NCNAME ) ^( CHILDSEQUENCE c1= childsequence ) | ^( CHILDSEQUENCE c2= childsequence ) )
            int alt5=3;
            switch ( input.LA(1) ) {
            case ELEMENT:
                {
                switch ( input.LA(2) ) {
                case DOWN:
                    {
                    switch ( input.LA(3) ) {
                    case NCNAME:
                        {
                        switch ( input.LA(4) ) {
                        case UP:
                            {
                            switch ( input.LA(5) ) {
                            case CHILDSEQUENCE:
                                {
                                alt5=2;
                                }
                                break;
                            case EOF:
                            case UP:
                                {
                                alt5=1;
                                }
                                break;
                            default:
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 5, 5, input);

                                throw nvae;

                            }

                            }
                            break;
                        default:
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 5, 4, input);

                            throw nvae;

                        }

                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 3, input);

                        throw nvae;

                    }

                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;

                }

                }
                break;
            case CHILDSEQUENCE:
                {
                alt5=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:101:4: ^( ELEMENT n= NCNAME )
                    {
                    match(input,ELEMENT,FOLLOW_ELEMENT_in_elementschemedata430); if (state.failed) return retval;

                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    n=(CommonTree)match(input,NCNAME,FOLLOW_NCNAME_in_elementschemedata434); if (state.failed) return retval;

                    match(input, Token.UP, null); if (state.failed) return retval;


                    if ( state.backtracking==0 ) {
                    	        retval.name = (n!=null?n.getText():null);
                    	        retval.data = "";
                    	    }

                    }
                    break;
                case 2 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:106:6: ^( ELEMENT n= NCNAME ) ^( CHILDSEQUENCE c1= childsequence )
                    {
                    match(input,ELEMENT,FOLLOW_ELEMENT_in_elementschemedata450); if (state.failed) return retval;

                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    n=(CommonTree)match(input,NCNAME,FOLLOW_NCNAME_in_elementschemedata454); if (state.failed) return retval;

                    match(input, Token.UP, null); if (state.failed) return retval;


                    match(input,CHILDSEQUENCE,FOLLOW_CHILDSEQUENCE_in_elementschemedata458); if (state.failed) return retval;

                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    pushFollow(FOLLOW_childsequence_in_elementschemedata462);
                    c1=childsequence();

                    state._fsp--;
                    if (state.failed) return retval;

                    match(input, Token.UP, null); if (state.failed) return retval;


                    if ( state.backtracking==0 ) {
                    	        retval.name = (n!=null?n.getText():null);
                    	        retval.data = ((c1!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(c1.start),input.getTreeAdaptor().getTokenStopIndex(c1.start))):null)!=null)?(c1!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(c1.start),input.getTreeAdaptor().getTokenStopIndex(c1.start))):null):"";
                    	    }

                    }
                    break;
                case 3 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:111:5: ^( CHILDSEQUENCE c2= childsequence )
                    {
                    match(input,CHILDSEQUENCE,FOLLOW_CHILDSEQUENCE_in_elementschemedata477); if (state.failed) return retval;

                    match(input, Token.DOWN, null); if (state.failed) return retval;
                    pushFollow(FOLLOW_childsequence_in_elementschemedata481);
                    c2=childsequence();

                    state._fsp--;
                    if (state.failed) return retval;

                    match(input, Token.UP, null); if (state.failed) return retval;


                    if ( state.backtracking==0 ) {
                    	        retval.name = "";
                    	        retval.data = ((c2!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(c2.start),input.getTreeAdaptor().getTokenStopIndex(c2.start))):null)!=null)?(c2!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(c2.start),input.getTreeAdaptor().getTokenStopIndex(c2.start))):null):"";
                    	    }

                    }
                    break;

            }
        }
        catch (RecognitionException e) {

                    reportElementSchemeDataError(e);
                    recover(input,e);
            	
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 10, elementschemedata_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "elementschemedata"


    public static class childsequence_return extends TreeRuleReturnScope {
    };


    // $ANTLR start "childsequence"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:122:1: childsequence : CHILDSEQUENCE ;
    public final XPointerTree.childsequence_return childsequence() throws RecognitionException {
        XPointerTree.childsequence_return retval = new XPointerTree.childsequence_return();
        retval.start = input.LT(1);

        int childsequence_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:123:2: ( CHILDSEQUENCE )
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:123:4: CHILDSEQUENCE
            {
            match(input,CHILDSEQUENCE,FOLLOW_CHILDSEQUENCE_in_childsequence506); if (state.failed) return retval;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 11, childsequence_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "childsequence"



    // $ANTLR start "schemedata"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:126:1: schemedata returns [String datas] : (e1= escapeddatas )* ;
    public final String schemedata() throws RecognitionException {
        String datas = null;

        int schemedata_StartIndex = input.index();

        XPointerTree.escapeddatas_return e1 =null;


         StringBuilder builder = new StringBuilder(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return datas; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:129:2: ( (e1= escapeddatas )* )
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:129:4: (e1= escapeddatas )*
            {
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:129:4: (e1= escapeddatas )*
            loop6:
            do {
                int alt6=2;
                switch ( input.LA(1) ) {
                case CHILDSEQUENCE:
                case COLON:
                case COVERINGRANGE:
                case DIGIT:
                case ELEMENT:
                case ENTITY:
                case EQUAL:
                case ESCCIRC:
                case ESCLBRACE:
                case ESCRBRACE:
                case HERE:
                case NCNAME:
                case NCNAMECHAR:
                case NCNAMESTARTCHAR:
                case ORIGIN:
                case POINT:
                case RANGE:
                case RANGEINSIDE:
                case RANGETO:
                case SPECIALCARS:
                case STARTPOINT:
                case STRINGRANGE:
                case XMLNS:
                case XPATH:
                case XPOINTER:
                case COVERINGRANG:
                case DATAS:
                case ELEMENTSCHEME:
                case FUNCTION:
                case LOCALNAME:
                case NAMESPACE:
                case OTHERSCHEME:
                case POINTER:
                case PREFIX:
                case QNAME:
                case SCHEMEBASED:
                case XMLNSSCHEME:
                case XPATHSCHEME:
                case XPOINTERSCHEME:
                    {
                    alt6=1;
                    }
                    break;

                }

                switch (alt6) {
            	case 1 :
            	    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:129:5: e1= escapeddatas
            	    {
            	    pushFollow(FOLLOW_escapeddatas_in_schemedata534);
            	    e1=escapeddatas();

            	    state._fsp--;
            	    if (state.failed) return datas;

            	    if ( state.backtracking==0 ) { builder.append((e1!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(e1.start),input.getTreeAdaptor().getTokenStopIndex(e1.start))):null));}

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) { datas = builder.toString(); }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 12, schemedata_StartIndex); }

        }
        return datas;
    }
    // $ANTLR end "schemedata"


    public static class xmlnsschemedata_return extends TreeRuleReturnScope {
        public String prefix;
        public String namespace;
    };


    // $ANTLR start "xmlnsschemedata"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:132:1: xmlnsschemedata returns [String prefix, String namespace] : ^( PREFIX n= NCNAME ) ^( NAMESPACE (e= escapeddatas )* ) ;
    public final XPointerTree.xmlnsschemedata_return xmlnsschemedata() throws RecognitionException {
        XPointerTree.xmlnsschemedata_return retval = new XPointerTree.xmlnsschemedata_return();
        retval.start = input.LT(1);

        int xmlnsschemedata_StartIndex = input.index();

        CommonTree n=null;
        XPointerTree.escapeddatas_return e =null;


         StringBuilder builder = new StringBuilder(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:135:2: ( ^( PREFIX n= NCNAME ) ^( NAMESPACE (e= escapeddatas )* ) )
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:135:4: ^( PREFIX n= NCNAME ) ^( NAMESPACE (e= escapeddatas )* )
            {
            match(input,PREFIX,FOLLOW_PREFIX_in_xmlnsschemedata564); if (state.failed) return retval;

            match(input, Token.DOWN, null); if (state.failed) return retval;
            n=(CommonTree)match(input,NCNAME,FOLLOW_NCNAME_in_xmlnsschemedata568); if (state.failed) return retval;

            match(input, Token.UP, null); if (state.failed) return retval;


            if ( state.backtracking==0 ) { retval.prefix = (n!=null?n.getText():null); }

            match(input,NAMESPACE,FOLLOW_NAMESPACE_in_xmlnsschemedata574); if (state.failed) return retval;

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return retval;
                // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:135:58: (e= escapeddatas )*
                loop7:
                do {
                    int alt7=2;
                    switch ( input.LA(1) ) {
                    case CHILDSEQUENCE:
                    case COLON:
                    case COVERINGRANGE:
                    case DIGIT:
                    case ELEMENT:
                    case ENTITY:
                    case EQUAL:
                    case ESCCIRC:
                    case ESCLBRACE:
                    case ESCRBRACE:
                    case HERE:
                    case NCNAME:
                    case NCNAMECHAR:
                    case NCNAMESTARTCHAR:
                    case ORIGIN:
                    case POINT:
                    case RANGE:
                    case RANGEINSIDE:
                    case RANGETO:
                    case SPECIALCARS:
                    case STARTPOINT:
                    case STRINGRANGE:
                    case XMLNS:
                    case XPATH:
                    case XPOINTER:
                    case COVERINGRANG:
                    case DATAS:
                    case ELEMENTSCHEME:
                    case FUNCTION:
                    case LOCALNAME:
                    case NAMESPACE:
                    case OTHERSCHEME:
                    case POINTER:
                    case PREFIX:
                    case QNAME:
                    case SCHEMEBASED:
                    case XMLNSSCHEME:
                    case XPATHSCHEME:
                    case XPOINTERSCHEME:
                        {
                        alt7=1;
                        }
                        break;

                    }

                    switch (alt7) {
                	case 1 :
                	    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:135:59: e= escapeddatas
                	    {
                	    pushFollow(FOLLOW_escapeddatas_in_xmlnsschemedata579);
                	    e=escapeddatas();

                	    state._fsp--;
                	    if (state.failed) return retval;

                	    if ( state.backtracking==0 ) { builder.append((e!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(e.start),input.getTreeAdaptor().getTokenStopIndex(e.start))):null)); }

                	    }
                	    break;

                	default :
                	    break loop7;
                    }
                } while (true);


                match(input, Token.UP, null); if (state.failed) return retval;
            }


            }

            if ( state.backtracking==0 ) { retval.namespace = builder.toString(); }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 13, xmlnsschemedata_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "xmlnsschemedata"



    // $ANTLR start "xpathschemedata"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:139:1: xpathschemedata returns [String xpathdatas] : (d= xpathescapeddata )* ;
    public final String xpathschemedata() throws RecognitionException {
        String xpathdatas = null;

        int xpathschemedata_StartIndex = input.index();

        XPointerTree.xpathescapeddata_return d =null;


         StringBuilder builder = new StringBuilder(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return xpathdatas; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:142:2: ( (d= xpathescapeddata )* )
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:142:4: (d= xpathescapeddata )*
            {
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:142:4: (d= xpathescapeddata )*
            loop8:
            do {
                int alt8=2;
                switch ( input.LA(1) ) {
                case CHILDSEQUENCE:
                case COLON:
                case COVERINGRANGE:
                case DIGIT:
                case ELEMENT:
                case ENTITY:
                case EQUAL:
                case ESCCIRC:
                case ESCLBRACE:
                case ESCRBRACE:
                case HERE:
                case LBRACE:
                case NCNAME:
                case NCNAMECHAR:
                case NCNAMESTARTCHAR:
                case ORIGIN:
                case POINT:
                case RANGE:
                case RANGEINSIDE:
                case RANGETO:
                case RBRACE:
                case S:
                case SPECIALCARS:
                case STARTPOINT:
                case STRINGRANGE:
                case XMLNS:
                case XPATH:
                case XPOINTER:
                case COVERINGRANG:
                case DATAS:
                case ELEMENTSCHEME:
                case FUNCTION:
                case LOCALNAME:
                case NAMESPACE:
                case OTHERSCHEME:
                case POINTER:
                case PREFIX:
                case QNAME:
                case SCHEMEBASED:
                case XMLNSSCHEME:
                case XPATHSCHEME:
                case XPOINTERSCHEME:
                    {
                    alt8=1;
                    }
                    break;

                }

                switch (alt8) {
            	case 1 :
            	    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:142:5: d= xpathescapeddata
            	    {
            	    pushFollow(FOLLOW_xpathescapeddata_in_xpathschemedata613);
            	    d=xpathescapeddata();

            	    state._fsp--;
            	    if (state.failed) return xpathdatas;

            	    if ( state.backtracking==0 ) { builder.append((d!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(d.start),input.getTreeAdaptor().getTokenStopIndex(d.start))):null)); }

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) { xpathdatas = builder.toString(); }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 14, xpathschemedata_StartIndex); }

        }
        return xpathdatas;
    }
    // $ANTLR end "xpathschemedata"


    public static class xpathescapeddata_return extends TreeRuleReturnScope {
    };


    // $ANTLR start "xpathescapeddata"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:145:1: xpathescapeddata : ( ESCCIRC | ESCLBRACE | ESCRBRACE |~ ( CIRC ) );
    public final XPointerTree.xpathescapeddata_return xpathescapeddata() throws RecognitionException {
        XPointerTree.xpathescapeddata_return retval = new XPointerTree.xpathescapeddata_return();
        retval.start = input.LT(1);

        int xpathescapeddata_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:146:5: ( ESCCIRC | ESCLBRACE | ESCRBRACE |~ ( CIRC ) )
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:
            {
            if ( input.LA(1)==CHILDSEQUENCE||(input.LA(1) >= COLON && input.LA(1) <= XPOINTERSCHEME) ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 15, xpathescapeddata_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "xpathescapeddata"


    public static class escapeddatas_return extends TreeRuleReturnScope {
    };


    // $ANTLR start "escapeddatas"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:152:1: escapeddatas : ~ ( LBRACE | RBRACE | CIRC | S ) ;
    public final XPointerTree.escapeddatas_return escapeddatas() throws RecognitionException {
        XPointerTree.escapeddatas_return retval = new XPointerTree.escapeddatas_return();
        retval.start = input.LT(1);

        int escapeddatas_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:153:5: (~ ( LBRACE | RBRACE | CIRC | S ) )
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:
            {
            if ( input.LA(1)==CHILDSEQUENCE||(input.LA(1) >= COLON && input.LA(1) <= HERE)||(input.LA(1) >= NCNAME && input.LA(1) <= RANGETO)||(input.LA(1) >= SPECIALCARS && input.LA(1) <= XPOINTERSCHEME) ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 16, escapeddatas_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "escapeddatas"


    public static class qname_return extends TreeRuleReturnScope {
    };


    // $ANTLR start "qname"
    // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:156:1: qname : ^( QNAME ( NCNAME )? ) ;
    public final XPointerTree.qname_return qname() throws RecognitionException {
        XPointerTree.qname_return retval = new XPointerTree.qname_return();
        retval.start = input.LT(1);

        int qname_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:157:5: ( ^( QNAME ( NCNAME )? ) )
            // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:157:7: ^( QNAME ( NCNAME )? )
            {
            match(input,QNAME,FOLLOW_QNAME_in_qname708); if (state.failed) return retval;

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return retval;
                // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:157:15: ( NCNAME )?
                int alt9=2;
                switch ( input.LA(1) ) {
                    case NCNAME:
                        {
                        alt9=1;
                        }
                        break;
                }

                switch (alt9) {
                    case 1 :
                        // org/etourdot/xincproc/xpointer/grammar/XPointerTree.g:157:15: NCNAME
                        {
                        match(input,NCNAME,FOLLOW_NCNAME_in_qname710); if (state.failed) return retval;

                        }
                        break;

                }


                match(input, Token.UP, null); if (state.failed) return retval;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 17, qname_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "qname"

    // Delegated rules


 

    public static final BitSet FOLLOW_POINTER_in_pointer104 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_shorthand_in_pointer108 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_POINTER_in_pointer125 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_schemebased_in_pointer129 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_POINTER_in_pointer144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NCNAME_in_shorthand161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointerpart_in_schemebased192 = new BitSet(new long[]{0x0000708800000002L});
    public static final BitSet FOLLOW_pointerpart_element_in_pointerpart211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointerpart_xpath_in_pointerpart218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointerpart_xpointer_in_pointerpart223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointerpart_xmlns_in_pointerpart229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointerpart_otherscheme_in_pointerpart234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELEMENTSCHEME_in_pointerpart_element251 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_elementschemedata_in_pointerpart_element255 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_XPATHSCHEME_in_pointerpart_xpath287 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_xpathschemedata_in_pointerpart_xpath291 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_XPOINTERSCHEME_in_pointerpart_xpointer322 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_xpathschemedata_in_pointerpart_xpointer326 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_XMLNSSCHEME_in_pointerpart_xmlns357 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_xmlnsschemedata_in_pointerpart_xmlns361 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OTHERSCHEME_in_pointerpart_otherscheme392 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qname_in_pointerpart_otherscheme396 = new BitSet(new long[]{0x00007FFFF9FEFFD8L});
    public static final BitSet FOLLOW_schemedata_in_pointerpart_otherscheme400 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ELEMENT_in_elementschemedata430 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NCNAME_in_elementschemedata434 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ELEMENT_in_elementschemedata450 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NCNAME_in_elementschemedata454 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CHILDSEQUENCE_in_elementschemedata458 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_childsequence_in_elementschemedata462 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CHILDSEQUENCE_in_elementschemedata477 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_childsequence_in_elementschemedata481 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CHILDSEQUENCE_in_childsequence506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_escapeddatas_in_schemedata534 = new BitSet(new long[]{0x00007FFFF9FEFFD2L});
    public static final BitSet FOLLOW_PREFIX_in_xmlnsschemedata564 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NCNAME_in_xmlnsschemedata568 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NAMESPACE_in_xmlnsschemedata574 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_escapeddatas_in_xmlnsschemedata579 = new BitSet(new long[]{0x00007FFFF9FEFFD8L});
    public static final BitSet FOLLOW_xpathescapeddata_in_xpathschemedata613 = new BitSet(new long[]{0x00007FFFFFFFFFD2L});
    public static final BitSet FOLLOW_QNAME_in_qname708 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NCNAME_in_qname710 = new BitSet(new long[]{0x0000000000000008L});

}