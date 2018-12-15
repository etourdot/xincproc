// $ANTLR 3.4 org/etourdot/xincproc/xpointer/grammar/XPointerParser.g 2018-12-15 17:51:15

    package org.etourdot.xincproc.xpointer.grammar;
    import org.etourdot.xincproc.xpointer.exceptions.*;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class XPointerParser extends AbstractXPointerParser {
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
    public AbstractXPointerParser[] getDelegates() {
        return new AbstractXPointerParser[] {};
    }

    // delegators


    public XPointerParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public XPointerParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
        this.state.ruleMemo = new HashMap[58+1];
         

    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return XPointerParser.tokenNames; }
    public String getGrammarFileName() { return "org/etourdot/xincproc/xpointer/grammar/XPointerParser.g"; }


    public static class pointer_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pointer"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:36:1: pointer : ( NCNAME -> ( ^( POINTER NCNAME ) )? | pointerpart ( ( S )? pointerpart )* -> ( ^( POINTER ( pointerpart )* ) )? );
    public final XPointerParser.pointer_return pointer() throws XPointerException, RecognitionException {
        XPointerParser.pointer_return retval = new XPointerParser.pointer_return();
        retval.start = input.LT(1);

        int pointer_StartIndex = input.index();

        CommonTree root_0 = null;

        Token NCNAME1=null;
        Token S3=null;
        XPointerParser.pointerpart_return pointerpart2 =null;

        XPointerParser.pointerpart_return pointerpart4 =null;


        CommonTree NCNAME1_tree=null;
        CommonTree S3_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_NCNAME=new RewriteRuleTokenStream(adaptor,"token NCNAME");
        RewriteRuleSubtreeStream stream_pointerpart=new RewriteRuleSubtreeStream(adaptor,"rule pointerpart");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:38:5: ( NCNAME -> ( ^( POINTER NCNAME ) )? | pointerpart ( ( S )? pointerpart )* -> ( ^( POINTER ( pointerpart )* ) )? )
            int alt3=2;
            switch ( input.LA(1) ) {
            case NCNAME:
                {
                switch ( input.LA(2) ) {
                case COLON:
                case LBRACE:
                    {
                    alt3=2;
                    }
                    break;
                case EOF:
                    {
                    alt3=1;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;

                }

                }
                break;
            case ELEMENT:
            case XMLNS:
            case XPATH:
            case XPOINTER:
                {
                alt3=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:38:9: NCNAME
                    {
                    NCNAME1=(Token)match(input,NCNAME,FOLLOW_NCNAME_in_pointer240); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NCNAME.add(NCNAME1);


                    // AST REWRITE
                    // elements: NCNAME
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 38:18: -> ( ^( POINTER NCNAME ) )?
                    {
                        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:38:21: ( ^( POINTER NCNAME ) )?
                        if ( stream_NCNAME.hasNext() ) {
                            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:38:21: ^( POINTER NCNAME )
                            {
                            CommonTree root_1 = (CommonTree)adaptor.nil();
                            root_1 = (CommonTree)adaptor.becomeRoot(
                            (CommonTree)adaptor.create(POINTER, "POINTER")
                            , root_1);

                            adaptor.addChild(root_1, 
                            stream_NCNAME.nextNode()
                            );

                            adaptor.addChild(root_0, root_1);
                            }

                        }
                        stream_NCNAME.reset();

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:39:4: pointerpart ( ( S )? pointerpart )*
                    {
                    pushFollow(FOLLOW_pointerpart_in_pointer256);
                    pointerpart2=pointerpart();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_pointerpart.add(pointerpart2.getTree());

                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:39:17: ( ( S )? pointerpart )*
                    loop2:
                    do {
                        int alt2=2;
                        switch ( input.LA(1) ) {
                        case ELEMENT:
                        case NCNAME:
                        case S:
                        case XMLNS:
                        case XPATH:
                        case XPOINTER:
                            {
                            alt2=1;
                            }
                            break;

                        }

                        switch (alt2) {
                    	case 1 :
                    	    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:39:18: ( S )? pointerpart
                    	    {
                    	    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:39:18: ( S )?
                    	    int alt1=2;
                    	    switch ( input.LA(1) ) {
                    	        case S:
                    	            {
                    	            alt1=1;
                    	            }
                    	            break;
                    	    }

                    	    switch (alt1) {
                    	        case 1 :
                    	            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:39:18: S
                    	            {
                    	            S3=(Token)match(input,S,FOLLOW_S_in_pointer260); if (state.failed) return retval; 
                    	            if ( state.backtracking==0 ) stream_S.add(S3);


                    	            }
                    	            break;

                    	    }


                    	    pushFollow(FOLLOW_pointerpart_in_pointer263);
                    	    pointerpart4=pointerpart();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_pointerpart.add(pointerpart4.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    // AST REWRITE
                    // elements: pointerpart
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 39:35: -> ( ^( POINTER ( pointerpart )* ) )?
                    {
                        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:39:38: ( ^( POINTER ( pointerpart )* ) )?
                        if ( stream_pointerpart.hasNext() ) {
                            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:39:38: ^( POINTER ( pointerpart )* )
                            {
                            CommonTree root_1 = (CommonTree)adaptor.nil();
                            root_1 = (CommonTree)adaptor.becomeRoot(
                            (CommonTree)adaptor.create(POINTER, "POINTER")
                            , root_1);

                            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:39:48: ( pointerpart )*
                            while ( stream_pointerpart.hasNext() ) {
                                adaptor.addChild(root_1, stream_pointerpart.nextTree());

                            }
                            stream_pointerpart.reset();

                            adaptor.addChild(root_0, root_1);
                            }

                        }
                        stream_pointerpart.reset();

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException e) {

                    emitErrorMessage("Error: invalid xpointer expression '" + input + "'");
                    recover(input,e);
                
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 1, pointer_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "pointer"


    public static class pointerpart_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pointerpart"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:46:1: pointerpart : ( pointerpart_element | pointerpart_xpath | pointerpart_xpointer | pointerpart_xmlns | pointerpart_otherscheme );
    public final XPointerParser.pointerpart_return pointerpart() throws XPointerException, RecognitionException {
        XPointerParser.pointerpart_return retval = new XPointerParser.pointerpart_return();
        retval.start = input.LT(1);

        int pointerpart_StartIndex = input.index();

        CommonTree root_0 = null;

        XPointerParser.pointerpart_element_return pointerpart_element5 =null;

        XPointerParser.pointerpart_xpath_return pointerpart_xpath6 =null;

        XPointerParser.pointerpart_xpointer_return pointerpart_xpointer7 =null;

        XPointerParser.pointerpart_xmlns_return pointerpart_xmlns8 =null;

        XPointerParser.pointerpart_otherscheme_return pointerpart_otherscheme9 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:48:2: ( pointerpart_element | pointerpart_xpath | pointerpart_xpointer | pointerpart_xmlns | pointerpart_otherscheme )
            int alt4=5;
            switch ( input.LA(1) ) {
            case ELEMENT:
                {
                alt4=1;
                }
                break;
            case XPATH:
                {
                alt4=2;
                }
                break;
            case XPOINTER:
                {
                alt4=3;
                }
                break;
            case XMLNS:
                {
                alt4=4;
                }
                break;
            case NCNAME:
                {
                alt4=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:48:4: pointerpart_element
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_pointerpart_element_in_pointerpart300);
                    pointerpart_element5=pointerpart_element();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pointerpart_element5.getTree());

                    }
                    break;
                case 2 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:49:6: pointerpart_xpath
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_pointerpart_xpath_in_pointerpart307);
                    pointerpart_xpath6=pointerpart_xpath();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pointerpart_xpath6.getTree());

                    }
                    break;
                case 3 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:50:6: pointerpart_xpointer
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_pointerpart_xpointer_in_pointerpart314);
                    pointerpart_xpointer7=pointerpart_xpointer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pointerpart_xpointer7.getTree());

                    }
                    break;
                case 4 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:51:6: pointerpart_xmlns
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_pointerpart_xmlns_in_pointerpart321);
                    pointerpart_xmlns8=pointerpart_xmlns();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pointerpart_xmlns8.getTree());

                    }
                    break;
                case 5 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:52:6: pointerpart_otherscheme
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_pointerpart_otherscheme_in_pointerpart328);
                    pointerpart_otherscheme9=pointerpart_otherscheme();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pointerpart_otherscheme9.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException e) {

                    emitErrorMessage("Error: invalid pointerpart expression '" + input + "'");
                    recover(input,e);
                
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 2, pointerpart_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "pointerpart"


    public static class pointerpart_element_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pointerpart_element"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:59:1: pointerpart_element : ELEMENT LBRACE elementschemedata RBRACE -> ( ^( ELEMENTSCHEME ( elementschemedata )? ) )? ;
    public final XPointerParser.pointerpart_element_return pointerpart_element() throws XPointerException, RecognitionException {
        XPointerParser.pointerpart_element_return retval = new XPointerParser.pointerpart_element_return();
        retval.start = input.LT(1);

        int pointerpart_element_StartIndex = input.index();

        CommonTree root_0 = null;

        Token ELEMENT10=null;
        Token LBRACE11=null;
        Token RBRACE13=null;
        XPointerParser.elementschemedata_return elementschemedata12 =null;


        CommonTree ELEMENT10_tree=null;
        CommonTree LBRACE11_tree=null;
        CommonTree RBRACE13_tree=null;
        RewriteRuleTokenStream stream_ELEMENT=new RewriteRuleTokenStream(adaptor,"token ELEMENT");
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_elementschemedata=new RewriteRuleSubtreeStream(adaptor,"rule elementschemedata");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:61:5: ( ELEMENT LBRACE elementschemedata RBRACE -> ( ^( ELEMENTSCHEME ( elementschemedata )? ) )? )
            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:61:9: ELEMENT LBRACE elementschemedata RBRACE
            {
            ELEMENT10=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_pointerpart_element361); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ELEMENT.add(ELEMENT10);


            LBRACE11=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_pointerpart_element363); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LBRACE.add(LBRACE11);


            pushFollow(FOLLOW_elementschemedata_in_pointerpart_element365);
            elementschemedata12=elementschemedata();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_elementschemedata.add(elementschemedata12.getTree());

            RBRACE13=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_pointerpart_element367); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RBRACE.add(RBRACE13);


            // AST REWRITE
            // elements: elementschemedata
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 61:53: -> ( ^( ELEMENTSCHEME ( elementschemedata )? ) )?
            {
                // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:61:56: ( ^( ELEMENTSCHEME ( elementschemedata )? ) )?
                if ( stream_elementschemedata.hasNext() ) {
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:61:56: ^( ELEMENTSCHEME ( elementschemedata )? )
                    {
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    root_1 = (CommonTree)adaptor.becomeRoot(
                    (CommonTree)adaptor.create(ELEMENTSCHEME, "ELEMENTSCHEME")
                    , root_1);

                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:61:72: ( elementschemedata )?
                    if ( stream_elementschemedata.hasNext() ) {
                        adaptor.addChild(root_1, stream_elementschemedata.nextTree());

                    }
                    stream_elementschemedata.reset();

                    adaptor.addChild(root_0, root_1);
                    }

                }
                stream_elementschemedata.reset();

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException e) {

                    emitErrorMessage("Error: invalid element expression '" + input + "'");
                    consumeUntil(input, RBRACE);
                    throw new XPointerException(e);
                
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 3, pointerpart_element_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "pointerpart_element"


    public static class pointerpart_xpath_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pointerpart_xpath"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:69:1: pointerpart_xpath : XPATH LBRACE xpathschemedata RBRACE -> ^( XPATHSCHEME xpathschemedata ) ;
    public final XPointerParser.pointerpart_xpath_return pointerpart_xpath() throws RecognitionException {
        XPointerParser.pointerpart_xpath_return retval = new XPointerParser.pointerpart_xpath_return();
        retval.start = input.LT(1);

        int pointerpart_xpath_StartIndex = input.index();

        CommonTree root_0 = null;

        Token XPATH14=null;
        Token LBRACE15=null;
        Token RBRACE17=null;
        XPointerParser.xpathschemedata_return xpathschemedata16 =null;


        CommonTree XPATH14_tree=null;
        CommonTree LBRACE15_tree=null;
        CommonTree RBRACE17_tree=null;
        RewriteRuleTokenStream stream_XPATH=new RewriteRuleTokenStream(adaptor,"token XPATH");
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_xpathschemedata=new RewriteRuleSubtreeStream(adaptor,"rule xpathschemedata");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:70:5: ( XPATH LBRACE xpathschemedata RBRACE -> ^( XPATHSCHEME xpathschemedata ) )
            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:70:9: XPATH LBRACE xpathschemedata RBRACE
            {
            XPATH14=(Token)match(input,XPATH,FOLLOW_XPATH_in_pointerpart_xpath409); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_XPATH.add(XPATH14);


            LBRACE15=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_pointerpart_xpath411); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LBRACE.add(LBRACE15);


            pushFollow(FOLLOW_xpathschemedata_in_pointerpart_xpath413);
            xpathschemedata16=xpathschemedata();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_xpathschemedata.add(xpathschemedata16.getTree());

            RBRACE17=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_pointerpart_xpath415); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RBRACE.add(RBRACE17);


            // AST REWRITE
            // elements: xpathschemedata
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 70:53: -> ^( XPATHSCHEME xpathschemedata )
            {
                // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:70:56: ^( XPATHSCHEME xpathschemedata )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(XPATHSCHEME, "XPATHSCHEME")
                , root_1);

                adaptor.addChild(root_1, stream_xpathschemedata.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 4, pointerpart_xpath_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "pointerpart_xpath"


    public static class pointerpart_xpointer_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pointerpart_xpointer"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:73:1: pointerpart_xpointer : XPOINTER LBRACE xpathschemedata RBRACE -> ^( XPOINTERSCHEME xpathschemedata ) ;
    public final XPointerParser.pointerpart_xpointer_return pointerpart_xpointer() throws RecognitionException {
        XPointerParser.pointerpart_xpointer_return retval = new XPointerParser.pointerpart_xpointer_return();
        retval.start = input.LT(1);

        int pointerpart_xpointer_StartIndex = input.index();

        CommonTree root_0 = null;

        Token XPOINTER18=null;
        Token LBRACE19=null;
        Token RBRACE21=null;
        XPointerParser.xpathschemedata_return xpathschemedata20 =null;


        CommonTree XPOINTER18_tree=null;
        CommonTree LBRACE19_tree=null;
        CommonTree RBRACE21_tree=null;
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_XPOINTER=new RewriteRuleTokenStream(adaptor,"token XPOINTER");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_xpathschemedata=new RewriteRuleSubtreeStream(adaptor,"rule xpathschemedata");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:74:5: ( XPOINTER LBRACE xpathschemedata RBRACE -> ^( XPOINTERSCHEME xpathschemedata ) )
            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:74:9: XPOINTER LBRACE xpathschemedata RBRACE
            {
            XPOINTER18=(Token)match(input,XPOINTER,FOLLOW_XPOINTER_in_pointerpart_xpointer450); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_XPOINTER.add(XPOINTER18);


            LBRACE19=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_pointerpart_xpointer452); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LBRACE.add(LBRACE19);


            pushFollow(FOLLOW_xpathschemedata_in_pointerpart_xpointer454);
            xpathschemedata20=xpathschemedata();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_xpathschemedata.add(xpathschemedata20.getTree());

            RBRACE21=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_pointerpart_xpointer456); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RBRACE.add(RBRACE21);


            // AST REWRITE
            // elements: xpathschemedata
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 74:53: -> ^( XPOINTERSCHEME xpathschemedata )
            {
                // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:74:56: ^( XPOINTERSCHEME xpathschemedata )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(XPOINTERSCHEME, "XPOINTERSCHEME")
                , root_1);

                adaptor.addChild(root_1, stream_xpathschemedata.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (XPointerSchemeException e) {

                    consumeUntil(input, RBRACE);
                
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 5, pointerpart_xpointer_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "pointerpart_xpointer"


    public static class pointerpart_xmlns_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pointerpart_xmlns"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:80:1: pointerpart_xmlns : XMLNS LBRACE xmlnsschemedata RBRACE -> ^( XMLNSSCHEME xmlnsschemedata ) ;
    public final XPointerParser.pointerpart_xmlns_return pointerpart_xmlns() throws RecognitionException {
        XPointerParser.pointerpart_xmlns_return retval = new XPointerParser.pointerpart_xmlns_return();
        retval.start = input.LT(1);

        int pointerpart_xmlns_StartIndex = input.index();

        CommonTree root_0 = null;

        Token XMLNS22=null;
        Token LBRACE23=null;
        Token RBRACE25=null;
        XPointerParser.xmlnsschemedata_return xmlnsschemedata24 =null;


        CommonTree XMLNS22_tree=null;
        CommonTree LBRACE23_tree=null;
        CommonTree RBRACE25_tree=null;
        RewriteRuleTokenStream stream_XMLNS=new RewriteRuleTokenStream(adaptor,"token XMLNS");
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_xmlnsschemedata=new RewriteRuleSubtreeStream(adaptor,"rule xmlnsschemedata");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:81:5: ( XMLNS LBRACE xmlnsschemedata RBRACE -> ^( XMLNSSCHEME xmlnsschemedata ) )
            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:81:9: XMLNS LBRACE xmlnsschemedata RBRACE
            {
            XMLNS22=(Token)match(input,XMLNS,FOLLOW_XMLNS_in_pointerpart_xmlns497); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_XMLNS.add(XMLNS22);


            LBRACE23=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_pointerpart_xmlns499); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LBRACE.add(LBRACE23);


            pushFollow(FOLLOW_xmlnsschemedata_in_pointerpart_xmlns501);
            xmlnsschemedata24=xmlnsschemedata();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_xmlnsschemedata.add(xmlnsschemedata24.getTree());

            RBRACE25=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_pointerpart_xmlns503); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RBRACE.add(RBRACE25);


            // AST REWRITE
            // elements: xmlnsschemedata
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 81:53: -> ^( XMLNSSCHEME xmlnsschemedata )
            {
                // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:81:56: ^( XMLNSSCHEME xmlnsschemedata )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(XMLNSSCHEME, "XMLNSSCHEME")
                , root_1);

                adaptor.addChild(root_1, stream_xmlnsschemedata.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 6, pointerpart_xmlns_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "pointerpart_xmlns"


    public static class pointerpart_otherscheme_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pointerpart_otherscheme"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:84:1: pointerpart_otherscheme : qname LBRACE schemedata RBRACE -> ^( OTHERSCHEME qname schemedata ) ;
    public final XPointerParser.pointerpart_otherscheme_return pointerpart_otherscheme() throws RecognitionException {
        XPointerParser.pointerpart_otherscheme_return retval = new XPointerParser.pointerpart_otherscheme_return();
        retval.start = input.LT(1);

        int pointerpart_otherscheme_StartIndex = input.index();

        CommonTree root_0 = null;

        Token LBRACE27=null;
        Token RBRACE29=null;
        XPointerParser.qname_return qname26 =null;

        XPointerParser.schemedata_return schemedata28 =null;


        CommonTree LBRACE27_tree=null;
        CommonTree RBRACE29_tree=null;
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_qname=new RewriteRuleSubtreeStream(adaptor,"rule qname");
        RewriteRuleSubtreeStream stream_schemedata=new RewriteRuleSubtreeStream(adaptor,"rule schemedata");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:85:5: ( qname LBRACE schemedata RBRACE -> ^( OTHERSCHEME qname schemedata ) )
            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:85:9: qname LBRACE schemedata RBRACE
            {
            pushFollow(FOLLOW_qname_in_pointerpart_otherscheme538);
            qname26=qname();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_qname.add(qname26.getTree());

            LBRACE27=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_pointerpart_otherscheme540); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LBRACE.add(LBRACE27);


            pushFollow(FOLLOW_schemedata_in_pointerpart_otherscheme542);
            schemedata28=schemedata();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_schemedata.add(schemedata28.getTree());

            RBRACE29=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_pointerpart_otherscheme544); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RBRACE.add(RBRACE29);


            // AST REWRITE
            // elements: schemedata, qname
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 85:53: -> ^( OTHERSCHEME qname schemedata )
            {
                // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:85:56: ^( OTHERSCHEME qname schemedata )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(OTHERSCHEME, "OTHERSCHEME")
                , root_1);

                adaptor.addChild(root_1, stream_qname.nextTree());

                adaptor.addChild(root_1, stream_schemedata.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (OtherSchemeException e) {

                    consumeUntil(input, RBRACE);
                    input.consume();
                
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 7, pointerpart_otherscheme_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "pointerpart_otherscheme"


    public static class elementschemedata_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "elementschemedata"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:92:1: elementschemedata : ( NCNAME -> ^( ELEMENT NCNAME ) | NCNAME CHILDSEQUENCE -> ^( ELEMENT NCNAME ) ^( CHILDSEQUENCE CHILDSEQUENCE ) | CHILDSEQUENCE -> ^( CHILDSEQUENCE CHILDSEQUENCE ) | ( escapeddata )* ->);
    public final XPointerParser.elementschemedata_return elementschemedata() throws RecognitionException {
        XPointerParser.elementschemedata_return retval = new XPointerParser.elementschemedata_return();
        retval.start = input.LT(1);

        int elementschemedata_StartIndex = input.index();

        CommonTree root_0 = null;

        Token NCNAME30=null;
        Token NCNAME31=null;
        Token CHILDSEQUENCE32=null;
        Token CHILDSEQUENCE33=null;
        XPointerParser.escapeddata_return escapeddata34 =null;


        CommonTree NCNAME30_tree=null;
        CommonTree NCNAME31_tree=null;
        CommonTree CHILDSEQUENCE32_tree=null;
        CommonTree CHILDSEQUENCE33_tree=null;
        RewriteRuleTokenStream stream_CHILDSEQUENCE=new RewriteRuleTokenStream(adaptor,"token CHILDSEQUENCE");
        RewriteRuleTokenStream stream_NCNAME=new RewriteRuleTokenStream(adaptor,"token NCNAME");
        RewriteRuleSubtreeStream stream_escapeddata=new RewriteRuleSubtreeStream(adaptor,"rule escapeddata");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:93:2: ( NCNAME -> ^( ELEMENT NCNAME ) | NCNAME CHILDSEQUENCE -> ^( ELEMENT NCNAME ) ^( CHILDSEQUENCE CHILDSEQUENCE ) | CHILDSEQUENCE -> ^( CHILDSEQUENCE CHILDSEQUENCE ) | ( escapeddata )* ->)
            int alt6=4;
            switch ( input.LA(1) ) {
            case NCNAME:
                {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==CHILDSEQUENCE) ) {
                    alt6=2;
                }
                else if ( (synpred8_XPointerParser()) ) {
                    alt6=1;
                }
                else if ( (true) ) {
                    alt6=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;

                }
                }
                break;
            case CHILDSEQUENCE:
                {
                int LA6_2 = input.LA(2);

                if ( (synpred10_XPointerParser()) ) {
                    alt6=3;
                }
                else if ( (true) ) {
                    alt6=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 2, input);

                    throw nvae;

                }
                }
                break;
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
                alt6=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:93:4: NCNAME
                    {
                    NCNAME30=(Token)match(input,NCNAME,FOLLOW_NCNAME_in_elementschemedata590); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NCNAME.add(NCNAME30);


                    // AST REWRITE
                    // elements: NCNAME
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 93:17: -> ^( ELEMENT NCNAME )
                    {
                        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:93:20: ^( ELEMENT NCNAME )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(ELEMENT, "ELEMENT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_NCNAME.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:94:6: NCNAME CHILDSEQUENCE
                    {
                    NCNAME31=(Token)match(input,NCNAME,FOLLOW_NCNAME_in_elementschemedata611); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NCNAME.add(NCNAME31);


                    CHILDSEQUENCE32=(Token)match(input,CHILDSEQUENCE,FOLLOW_CHILDSEQUENCE_in_elementschemedata613); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHILDSEQUENCE.add(CHILDSEQUENCE32);


                    // AST REWRITE
                    // elements: NCNAME, CHILDSEQUENCE, CHILDSEQUENCE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 94:27: -> ^( ELEMENT NCNAME ) ^( CHILDSEQUENCE CHILDSEQUENCE )
                    {
                        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:94:30: ^( ELEMENT NCNAME )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(ELEMENT, "ELEMENT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_NCNAME.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:94:48: ^( CHILDSEQUENCE CHILDSEQUENCE )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_CHILDSEQUENCE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_CHILDSEQUENCE.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:95:5: CHILDSEQUENCE
                    {
                    CHILDSEQUENCE33=(Token)match(input,CHILDSEQUENCE,FOLLOW_CHILDSEQUENCE_in_elementschemedata633); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHILDSEQUENCE.add(CHILDSEQUENCE33);


                    // AST REWRITE
                    // elements: CHILDSEQUENCE, CHILDSEQUENCE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 95:19: -> ^( CHILDSEQUENCE CHILDSEQUENCE )
                    {
                        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:95:22: ^( CHILDSEQUENCE CHILDSEQUENCE )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_CHILDSEQUENCE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_CHILDSEQUENCE.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:96:6: ( escapeddata )*
                    {
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:96:6: ( escapeddata )*
                    loop5:
                    do {
                        int alt5=2;
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
                            alt5=1;
                            }
                            break;

                        }

                        switch (alt5) {
                    	case 1 :
                    	    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:96:6: escapeddata
                    	    {
                    	    pushFollow(FOLLOW_escapeddata_in_elementschemedata648);
                    	    escapeddata34=escapeddata();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_escapeddata.add(escapeddata34.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {
                                emitErrorMessage("Error: bad element scheme data '" + input.toString(retval.start,input.LT(-1)) + "'");
                            }

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 99:11: ->
                    {
                        root_0 = null;
                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException e) {

                    emitErrorMessage("Error: invalid element scheme data expression '" + getTokenErrorDisplay(e.token) + "'");
                    consumeUntil(input, RBRACE);
                
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 8, elementschemedata_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "elementschemedata"


    public static class schemedata_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "schemedata"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:106:1: schemedata : ( escapeddata )* ;
    public final XPointerParser.schemedata_return schemedata() throws RecognitionException {
        XPointerParser.schemedata_return retval = new XPointerParser.schemedata_return();
        retval.start = input.LT(1);

        int schemedata_StartIndex = input.index();

        CommonTree root_0 = null;

        XPointerParser.escapeddata_return escapeddata35 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:107:2: ( ( escapeddata )* )
            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:107:4: ( escapeddata )*
            {
            root_0 = (CommonTree)adaptor.nil();


            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:107:4: ( escapeddata )*
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
                case LBRACE:
                case NCNAME:
                case NCNAMECHAR:
                case NCNAMESTARTCHAR:
                case ORIGIN:
                case POINT:
                case RANGE:
                case RANGEINSIDE:
                case RANGETO:
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
                    alt7=1;
                    }
                    break;

                }

                switch (alt7) {
            	case 1 :
            	    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:107:4: escapeddata
            	    {
            	    pushFollow(FOLLOW_escapeddata_in_schemedata678);
            	    escapeddata35=escapeddata();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, escapeddata35.getTree());

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 9, schemedata_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "schemedata"


    public static class xmlnsschemedata_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "xmlnsschemedata"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:110:1: xmlnsschemedata : NCNAME ( S )? EQUAL ( S )? escapednamespacename -> ^( PREFIX NCNAME ) ^( NAMESPACE escapednamespacename ) ;
    public final XPointerParser.xmlnsschemedata_return xmlnsschemedata() throws RecognitionException {
        XPointerParser.xmlnsschemedata_return retval = new XPointerParser.xmlnsschemedata_return();
        retval.start = input.LT(1);

        int xmlnsschemedata_StartIndex = input.index();

        CommonTree root_0 = null;

        Token NCNAME36=null;
        Token S37=null;
        Token EQUAL38=null;
        Token S39=null;
        XPointerParser.escapednamespacename_return escapednamespacename40 =null;


        CommonTree NCNAME36_tree=null;
        CommonTree S37_tree=null;
        CommonTree EQUAL38_tree=null;
        CommonTree S39_tree=null;
        RewriteRuleTokenStream stream_S=new RewriteRuleTokenStream(adaptor,"token S");
        RewriteRuleTokenStream stream_EQUAL=new RewriteRuleTokenStream(adaptor,"token EQUAL");
        RewriteRuleTokenStream stream_NCNAME=new RewriteRuleTokenStream(adaptor,"token NCNAME");
        RewriteRuleSubtreeStream stream_escapednamespacename=new RewriteRuleSubtreeStream(adaptor,"rule escapednamespacename");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:111:2: ( NCNAME ( S )? EQUAL ( S )? escapednamespacename -> ^( PREFIX NCNAME ) ^( NAMESPACE escapednamespacename ) )
            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:111:4: NCNAME ( S )? EQUAL ( S )? escapednamespacename
            {
            NCNAME36=(Token)match(input,NCNAME,FOLLOW_NCNAME_in_xmlnsschemedata690); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NCNAME.add(NCNAME36);


            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:111:11: ( S )?
            int alt8=2;
            switch ( input.LA(1) ) {
                case S:
                    {
                    alt8=1;
                    }
                    break;
            }

            switch (alt8) {
                case 1 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:111:11: S
                    {
                    S37=(Token)match(input,S,FOLLOW_S_in_xmlnsschemedata692); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_S.add(S37);


                    }
                    break;

            }


            EQUAL38=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_xmlnsschemedata695); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EQUAL.add(EQUAL38);


            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:111:20: ( S )?
            int alt9=2;
            switch ( input.LA(1) ) {
                case S:
                    {
                    int LA9_1 = input.LA(2);

                    if ( (synpred14_XPointerParser()) ) {
                        alt9=1;
                    }
                    }
                    break;
            }

            switch (alt9) {
                case 1 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:111:20: S
                    {
                    S39=(Token)match(input,S,FOLLOW_S_in_xmlnsschemedata697); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_S.add(S39);


                    }
                    break;

            }


            pushFollow(FOLLOW_escapednamespacename_in_xmlnsschemedata700);
            escapednamespacename40=escapednamespacename();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_escapednamespacename.add(escapednamespacename40.getTree());

            // AST REWRITE
            // elements: escapednamespacename, NCNAME
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 111:44: -> ^( PREFIX NCNAME ) ^( NAMESPACE escapednamespacename )
            {
                // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:111:47: ^( PREFIX NCNAME )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(PREFIX, "PREFIX")
                , root_1);

                adaptor.addChild(root_1, 
                stream_NCNAME.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

                // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:111:64: ^( NAMESPACE escapednamespacename )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(NAMESPACE, "NAMESPACE")
                , root_1);

                adaptor.addChild(root_1, stream_escapednamespacename.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 10, xmlnsschemedata_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "xmlnsschemedata"


    public static class escapednamespacename_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "escapednamespacename"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:114:1: escapednamespacename : ( escapeddata )* ;
    public final XPointerParser.escapednamespacename_return escapednamespacename() throws RecognitionException {
        XPointerParser.escapednamespacename_return retval = new XPointerParser.escapednamespacename_return();
        retval.start = input.LT(1);

        int escapednamespacename_StartIndex = input.index();

        CommonTree root_0 = null;

        XPointerParser.escapeddata_return escapeddata41 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:115:2: ( ( escapeddata )* )
            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:115:4: ( escapeddata )*
            {
            root_0 = (CommonTree)adaptor.nil();


            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:115:4: ( escapeddata )*
            loop10:
            do {
                int alt10=2;
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
                    alt10=1;
                    }
                    break;

                }

                switch (alt10) {
            	case 1 :
            	    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:115:4: escapeddata
            	    {
            	    pushFollow(FOLLOW_escapeddata_in_escapednamespacename725);
            	    escapeddata41=escapeddata();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, escapeddata41.getTree());

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 11, escapednamespacename_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "escapednamespacename"


    public static class xpathschemedata_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "xpathschemedata"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:118:1: xpathschemedata : ( xpathescapeddata )* ;
    public final XPointerParser.xpathschemedata_return xpathschemedata() throws RecognitionException {
        XPointerParser.xpathschemedata_return retval = new XPointerParser.xpathschemedata_return();
        retval.start = input.LT(1);

        int xpathschemedata_StartIndex = input.index();

        CommonTree root_0 = null;

        XPointerParser.xpathescapeddata_return xpathescapeddata42 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:119:2: ( ( xpathescapeddata )* )
            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:119:4: ( xpathescapeddata )*
            {
            root_0 = (CommonTree)adaptor.nil();


            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:119:4: ( xpathescapeddata )*
            loop11:
            do {
                int alt11=2;
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
                    alt11=1;
                    }
                    break;

                }

                switch (alt11) {
            	case 1 :
            	    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:119:4: xpathescapeddata
            	    {
            	    pushFollow(FOLLOW_xpathescapeddata_in_xpathschemedata737);
            	    xpathescapeddata42=xpathescapeddata();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, xpathescapeddata42.getTree());

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (XPointerSchemeException e) {

                    throw e;
                
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 12, xpathschemedata_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "xpathschemedata"


    public static class escapeddata_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "escapeddata"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:125:1: escapeddata : ( ESCCIRC | ESCLBRACE | ESCRBRACE | LBRACE schemedata RBRACE |~ ( LBRACE | RBRACE | CIRC ) );
    public final XPointerParser.escapeddata_return escapeddata() throws RecognitionException {
        XPointerParser.escapeddata_return retval = new XPointerParser.escapeddata_return();
        retval.start = input.LT(1);

        int escapeddata_StartIndex = input.index();

        CommonTree root_0 = null;

        Token ESCCIRC43=null;
        Token ESCLBRACE44=null;
        Token ESCRBRACE45=null;
        Token LBRACE46=null;
        Token RBRACE48=null;
        Token set49=null;
        XPointerParser.schemedata_return schemedata47 =null;


        CommonTree ESCCIRC43_tree=null;
        CommonTree ESCLBRACE44_tree=null;
        CommonTree ESCRBRACE45_tree=null;
        CommonTree LBRACE46_tree=null;
        CommonTree RBRACE48_tree=null;
        CommonTree set49_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:126:2: ( ESCCIRC | ESCLBRACE | ESCRBRACE | LBRACE schemedata RBRACE |~ ( LBRACE | RBRACE | CIRC ) )
            int alt12=5;
            switch ( input.LA(1) ) {
            case ESCCIRC:
                {
                int LA12_1 = input.LA(2);

                if ( (synpred17_XPointerParser()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 1, input);

                    throw nvae;

                }
                }
                break;
            case ESCLBRACE:
                {
                int LA12_2 = input.LA(2);

                if ( (synpred18_XPointerParser()) ) {
                    alt12=2;
                }
                else if ( (true) ) {
                    alt12=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 2, input);

                    throw nvae;

                }
                }
                break;
            case ESCRBRACE:
                {
                int LA12_3 = input.LA(2);

                if ( (synpred19_XPointerParser()) ) {
                    alt12=3;
                }
                else if ( (true) ) {
                    alt12=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 3, input);

                    throw nvae;

                }
                }
                break;
            case LBRACE:
                {
                alt12=4;
                }
                break;
            case CHILDSEQUENCE:
            case COLON:
            case COVERINGRANGE:
            case DIGIT:
            case ELEMENT:
            case ENTITY:
            case EQUAL:
            case HERE:
            case NCNAME:
            case NCNAMECHAR:
            case NCNAMESTARTCHAR:
            case ORIGIN:
            case POINT:
            case RANGE:
            case RANGEINSIDE:
            case RANGETO:
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
                alt12=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }

            switch (alt12) {
                case 1 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:126:4: ESCCIRC
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ESCCIRC43=(Token)match(input,ESCCIRC,FOLLOW_ESCCIRC_in_escapeddata758); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ESCCIRC43_tree = 
                    (CommonTree)adaptor.create(ESCCIRC43)
                    ;
                    adaptor.addChild(root_0, ESCCIRC43_tree);
                    }

                    }
                    break;
                case 2 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:127:6: ESCLBRACE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ESCLBRACE44=(Token)match(input,ESCLBRACE,FOLLOW_ESCLBRACE_in_escapeddata765); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ESCLBRACE44_tree = 
                    (CommonTree)adaptor.create(ESCLBRACE44)
                    ;
                    adaptor.addChild(root_0, ESCLBRACE44_tree);
                    }

                    }
                    break;
                case 3 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:128:6: ESCRBRACE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ESCRBRACE45=(Token)match(input,ESCRBRACE,FOLLOW_ESCRBRACE_in_escapeddata772); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ESCRBRACE45_tree = 
                    (CommonTree)adaptor.create(ESCRBRACE45)
                    ;
                    adaptor.addChild(root_0, ESCRBRACE45_tree);
                    }

                    }
                    break;
                case 4 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:129:4: LBRACE schemedata RBRACE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    LBRACE46=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_escapeddata777); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LBRACE46_tree = 
                    (CommonTree)adaptor.create(LBRACE46)
                    ;
                    adaptor.addChild(root_0, LBRACE46_tree);
                    }

                    pushFollow(FOLLOW_schemedata_in_escapeddata779);
                    schemedata47=schemedata();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, schemedata47.getTree());

                    RBRACE48=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_escapeddata781); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RBRACE48_tree = 
                    (CommonTree)adaptor.create(RBRACE48)
                    ;
                    adaptor.addChild(root_0, RBRACE48_tree);
                    }

                    }
                    break;
                case 5 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:130:6: ~ ( LBRACE | RBRACE | CIRC )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    set49=(Token)input.LT(1);

                    if ( input.LA(1)==CHILDSEQUENCE||(input.LA(1) >= COLON && input.LA(1) <= HERE)||(input.LA(1) >= NCNAME && input.LA(1) <= RANGETO)||(input.LA(1) >= S && input.LA(1) <= XPOINTERSCHEME) ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(set49)
                        );
                        state.errorRecovery=false;
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 13, escapeddata_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "escapeddata"


    public static class xpathescapeddata_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "xpathescapeddata"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:133:1: xpathescapeddata : ( ESCCIRC | ESCLBRACE | ESCRBRACE | LBRACE xpathschemedata RBRACE | function |~ ( LBRACE | RBRACE | CIRC ) );
    public final XPointerParser.xpathescapeddata_return xpathescapeddata() throws RecognitionException {
        XPointerParser.xpathescapeddata_return retval = new XPointerParser.xpathescapeddata_return();
        retval.start = input.LT(1);

        int xpathescapeddata_StartIndex = input.index();

        CommonTree root_0 = null;

        Token ESCCIRC50=null;
        Token ESCLBRACE51=null;
        Token ESCRBRACE52=null;
        Token LBRACE53=null;
        Token RBRACE55=null;
        Token set57=null;
        XPointerParser.xpathschemedata_return xpathschemedata54 =null;

        XPointerParser.function_return function56 =null;


        CommonTree ESCCIRC50_tree=null;
        CommonTree ESCLBRACE51_tree=null;
        CommonTree ESCRBRACE52_tree=null;
        CommonTree LBRACE53_tree=null;
        CommonTree RBRACE55_tree=null;
        CommonTree set57_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:134:5: ( ESCCIRC | ESCLBRACE | ESCRBRACE | LBRACE xpathschemedata RBRACE | function |~ ( LBRACE | RBRACE | CIRC ) )
            int alt13=6;
            switch ( input.LA(1) ) {
            case ESCCIRC:
                {
                int LA13_1 = input.LA(2);

                if ( (synpred23_XPointerParser()) ) {
                    alt13=1;
                }
                else if ( (true) ) {
                    alt13=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;

                }
                }
                break;
            case ESCLBRACE:
                {
                int LA13_2 = input.LA(2);

                if ( (synpred24_XPointerParser()) ) {
                    alt13=2;
                }
                else if ( (true) ) {
                    alt13=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 2, input);

                    throw nvae;

                }
                }
                break;
            case ESCRBRACE:
                {
                int LA13_3 = input.LA(2);

                if ( (synpred25_XPointerParser()) ) {
                    alt13=3;
                }
                else if ( (true) ) {
                    alt13=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 3, input);

                    throw nvae;

                }
                }
                break;
            case LBRACE:
                {
                alt13=4;
                }
                break;
            case STRINGRANGE:
                {
                int LA13_5 = input.LA(2);

                if ( (synpred27_XPointerParser()) ) {
                    alt13=5;
                }
                else if ( (true) ) {
                    alt13=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 5, input);

                    throw nvae;

                }
                }
                break;
            case RANGETO:
                {
                int LA13_6 = input.LA(2);

                if ( (synpred27_XPointerParser()) ) {
                    alt13=5;
                }
                else if ( (true) ) {
                    alt13=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 6, input);

                    throw nvae;

                }
                }
                break;
            case POINT:
                {
                int LA13_7 = input.LA(2);

                if ( (synpred27_XPointerParser()) ) {
                    alt13=5;
                }
                else if ( (true) ) {
                    alt13=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 7, input);

                    throw nvae;

                }
                }
                break;
            case RANGE:
                {
                int LA13_8 = input.LA(2);

                if ( (synpred27_XPointerParser()) ) {
                    alt13=5;
                }
                else if ( (true) ) {
                    alt13=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 8, input);

                    throw nvae;

                }
                }
                break;
            case COVERINGRANG:
                {
                int LA13_9 = input.LA(2);

                if ( (synpred27_XPointerParser()) ) {
                    alt13=5;
                }
                else if ( (true) ) {
                    alt13=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 9, input);

                    throw nvae;

                }
                }
                break;
            case RANGEINSIDE:
                {
                int LA13_10 = input.LA(2);

                if ( (synpred27_XPointerParser()) ) {
                    alt13=5;
                }
                else if ( (true) ) {
                    alt13=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 10, input);

                    throw nvae;

                }
                }
                break;
            case STARTPOINT:
                {
                int LA13_11 = input.LA(2);

                if ( (synpred27_XPointerParser()) ) {
                    alt13=5;
                }
                else if ( (true) ) {
                    alt13=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 11, input);

                    throw nvae;

                }
                }
                break;
            case HERE:
                {
                int LA13_12 = input.LA(2);

                if ( (synpred27_XPointerParser()) ) {
                    alt13=5;
                }
                else if ( (true) ) {
                    alt13=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 12, input);

                    throw nvae;

                }
                }
                break;
            case ORIGIN:
                {
                int LA13_13 = input.LA(2);

                if ( (synpred27_XPointerParser()) ) {
                    alt13=5;
                }
                else if ( (true) ) {
                    alt13=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 13, input);

                    throw nvae;

                }
                }
                break;
            case CHILDSEQUENCE:
            case COLON:
            case COVERINGRANGE:
            case DIGIT:
            case ELEMENT:
            case ENTITY:
            case EQUAL:
            case NCNAME:
            case NCNAMECHAR:
            case NCNAMESTARTCHAR:
            case S:
            case SPECIALCARS:
            case XMLNS:
            case XPATH:
            case XPOINTER:
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
                alt13=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
                case 1 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:134:7: ESCCIRC
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ESCCIRC50=(Token)match(input,ESCCIRC,FOLLOW_ESCCIRC_in_xpathescapeddata809); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ESCCIRC50_tree = 
                    (CommonTree)adaptor.create(ESCCIRC50)
                    ;
                    adaptor.addChild(root_0, ESCCIRC50_tree);
                    }

                    }
                    break;
                case 2 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:135:9: ESCLBRACE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ESCLBRACE51=(Token)match(input,ESCLBRACE,FOLLOW_ESCLBRACE_in_xpathescapeddata819); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ESCLBRACE51_tree = 
                    (CommonTree)adaptor.create(ESCLBRACE51)
                    ;
                    adaptor.addChild(root_0, ESCLBRACE51_tree);
                    }

                    }
                    break;
                case 3 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:136:9: ESCRBRACE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ESCRBRACE52=(Token)match(input,ESCRBRACE,FOLLOW_ESCRBRACE_in_xpathescapeddata829); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ESCRBRACE52_tree = 
                    (CommonTree)adaptor.create(ESCRBRACE52)
                    ;
                    adaptor.addChild(root_0, ESCRBRACE52_tree);
                    }

                    }
                    break;
                case 4 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:137:7: LBRACE xpathschemedata RBRACE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    LBRACE53=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_xpathescapeddata837); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LBRACE53_tree = 
                    (CommonTree)adaptor.create(LBRACE53)
                    ;
                    adaptor.addChild(root_0, LBRACE53_tree);
                    }

                    pushFollow(FOLLOW_xpathschemedata_in_xpathescapeddata839);
                    xpathschemedata54=xpathschemedata();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, xpathschemedata54.getTree());

                    RBRACE55=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_xpathescapeddata841); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RBRACE55_tree = 
                    (CommonTree)adaptor.create(RBRACE55)
                    ;
                    adaptor.addChild(root_0, RBRACE55_tree);
                    }

                    }
                    break;
                case 5 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:138:9: function
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_function_in_xpathescapeddata851);
                    function56=function();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, function56.getTree());

                    }
                    break;
                case 6 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:139:9: ~ ( LBRACE | RBRACE | CIRC )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    set57=(Token)input.LT(1);

                    if ( input.LA(1)==CHILDSEQUENCE||(input.LA(1) >= COLON && input.LA(1) <= HERE)||(input.LA(1) >= NCNAME && input.LA(1) <= RANGETO)||(input.LA(1) >= S && input.LA(1) <= XPOINTERSCHEME) ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(set57)
                        );
                        state.errorRecovery=false;
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (XPointerSchemeException e) {

                    throw e;
                
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 14, xpathescapeddata_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "xpathescapeddata"


    public static class qname_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "qname"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:145:1: qname : ( ( NCNAME COLON )? NCNAME -> ^( QNAME ( NCNAME )+ ) | NCNAME COLON ( ELEMENT | XPATH | XPOINTER | XMLNS ) );
    public final XPointerParser.qname_return qname() throws RecognitionException {
        XPointerParser.qname_return retval = new XPointerParser.qname_return();
        retval.start = input.LT(1);

        int qname_StartIndex = input.index();

        CommonTree root_0 = null;

        Token NCNAME58=null;
        Token COLON59=null;
        Token NCNAME60=null;
        Token NCNAME61=null;
        Token COLON62=null;
        Token set63=null;

        CommonTree NCNAME58_tree=null;
        CommonTree COLON59_tree=null;
        CommonTree NCNAME60_tree=null;
        CommonTree NCNAME61_tree=null;
        CommonTree COLON62_tree=null;
        CommonTree set63_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_NCNAME=new RewriteRuleTokenStream(adaptor,"token NCNAME");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:146:5: ( ( NCNAME COLON )? NCNAME -> ^( QNAME ( NCNAME )+ ) | NCNAME COLON ( ELEMENT | XPATH | XPOINTER | XMLNS ) )
            int alt15=2;
            switch ( input.LA(1) ) {
            case NCNAME:
                {
                switch ( input.LA(2) ) {
                case COLON:
                    {
                    switch ( input.LA(3) ) {
                    case ELEMENT:
                    case XMLNS:
                    case XPATH:
                    case XPOINTER:
                        {
                        alt15=2;
                        }
                        break;
                    case NCNAME:
                        {
                        alt15=1;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 15, 2, input);

                        throw nvae;

                    }

                    }
                    break;
                case LBRACE:
                    {
                    alt15=1;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;

                }

                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }

            switch (alt15) {
                case 1 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:146:7: ( NCNAME COLON )? NCNAME
                    {
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:146:7: ( NCNAME COLON )?
                    int alt14=2;
                    switch ( input.LA(1) ) {
                        case NCNAME:
                            {
                            switch ( input.LA(2) ) {
                                case COLON:
                                    {
                                    alt14=1;
                                    }
                                    break;
                            }

                            }
                            break;
                    }

                    switch (alt14) {
                        case 1 :
                            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:146:8: NCNAME COLON
                            {
                            NCNAME58=(Token)match(input,NCNAME,FOLLOW_NCNAME_in_qname895); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NCNAME.add(NCNAME58);


                            COLON59=(Token)match(input,COLON,FOLLOW_COLON_in_qname897); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COLON.add(COLON59);


                            }
                            break;

                    }


                    NCNAME60=(Token)match(input,NCNAME,FOLLOW_NCNAME_in_qname901); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NCNAME.add(NCNAME60);


                    // AST REWRITE
                    // elements: NCNAME
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 146:30: -> ^( QNAME ( NCNAME )+ )
                    {
                        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:146:33: ^( QNAME ( NCNAME )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(QNAME, "QNAME")
                        , root_1);

                        if ( !(stream_NCNAME.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_NCNAME.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_NCNAME.nextNode()
                            );

                        }
                        stream_NCNAME.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:147:9: NCNAME COLON ( ELEMENT | XPATH | XPOINTER | XMLNS )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    NCNAME61=(Token)match(input,NCNAME,FOLLOW_NCNAME_in_qname920); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NCNAME61_tree = 
                    (CommonTree)adaptor.create(NCNAME61)
                    ;
                    adaptor.addChild(root_0, NCNAME61_tree);
                    }

                    COLON62=(Token)match(input,COLON,FOLLOW_COLON_in_qname922); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON62_tree = 
                    (CommonTree)adaptor.create(COLON62)
                    ;
                    adaptor.addChild(root_0, COLON62_tree);
                    }

                    set63=(Token)input.LT(1);

                    if ( input.LA(1)==ELEMENT||(input.LA(1) >= XMLNS && input.LA(1) <= XPOINTER) ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(set63)
                        );
                        state.errorRecovery=false;
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    if ( state.backtracking==0 ) {
                                emitErrorMessage("Error: bad scheme '" + input.toString(retval.start,input.LT(-1)) + "'");
                                throw new OtherSchemeException(input);
                            }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (OtherSchemeException e) {

            	    throw e;
            	
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 15, qname_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "qname"


    public static class function_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "function"
    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:157:1: function : (f= STRINGRANGE |f= RANGETO |f= POINT |f= RANGE |f= COVERINGRANG |f= RANGEINSIDE |f= STARTPOINT |f= HERE |f= ORIGIN );
    public final XPointerParser.function_return function() throws RecognitionException {
        XPointerParser.function_return retval = new XPointerParser.function_return();
        retval.start = input.LT(1);

        int function_StartIndex = input.index();

        CommonTree root_0 = null;

        Token f=null;

        CommonTree f_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

            // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:162:2: (f= STRINGRANGE |f= RANGETO |f= POINT |f= RANGE |f= COVERINGRANG |f= RANGEINSIDE |f= STARTPOINT |f= HERE |f= ORIGIN )
            int alt16=9;
            switch ( input.LA(1) ) {
            case STRINGRANGE:
                {
                alt16=1;
                }
                break;
            case RANGETO:
                {
                alt16=2;
                }
                break;
            case POINT:
                {
                alt16=3;
                }
                break;
            case RANGE:
                {
                alt16=4;
                }
                break;
            case COVERINGRANG:
                {
                alt16=5;
                }
                break;
            case RANGEINSIDE:
                {
                alt16=6;
                }
                break;
            case STARTPOINT:
                {
                alt16=7;
                }
                break;
            case HERE:
                {
                alt16=8;
                }
                break;
            case ORIGIN:
                {
                alt16=9;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }

            switch (alt16) {
                case 1 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:162:4: f= STRINGRANGE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    f=(Token)match(input,STRINGRANGE,FOLLOW_STRINGRANGE_in_function966); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);
                    }

                    }
                    break;
                case 2 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:163:6: f= RANGETO
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    f=(Token)match(input,RANGETO,FOLLOW_RANGETO_in_function975); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);
                    }

                    }
                    break;
                case 3 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:164:9: f= POINT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    f=(Token)match(input,POINT,FOLLOW_POINT_in_function987); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);
                    }

                    }
                    break;
                case 4 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:165:9: f= RANGE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    f=(Token)match(input,RANGE,FOLLOW_RANGE_in_function999); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);
                    }

                    }
                    break;
                case 5 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:166:9: f= COVERINGRANG
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    f=(Token)match(input,COVERINGRANG,FOLLOW_COVERINGRANG_in_function1011); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);
                    }

                    }
                    break;
                case 6 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:167:9: f= RANGEINSIDE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    f=(Token)match(input,RANGEINSIDE,FOLLOW_RANGEINSIDE_in_function1023); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);
                    }

                    }
                    break;
                case 7 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:168:9: f= STARTPOINT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    f=(Token)match(input,STARTPOINT,FOLLOW_STARTPOINT_in_function1035); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);
                    }

                    }
                    break;
                case 8 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:169:9: f= HERE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    f=(Token)match(input,HERE,FOLLOW_HERE_in_function1047); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);
                    }

                    }
                    break;
                case 9 :
                    // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:170:9: f= ORIGIN
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    f=(Token)match(input,ORIGIN,FOLLOW_ORIGIN_in_function1059); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = 
                    (CommonTree)adaptor.create(f)
                    ;
                    adaptor.addChild(root_0, f_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
                emitErrorMessage("Error: xpointer function '" + (f!=null?f.getText():null) + "' is not supported");
                throw new XPointerSchemeException(input);
            }
        }
        catch (XPointerSchemeException e) {

                    throw e;
                
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 16, function_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "function"

    // $ANTLR start synpred8_XPointerParser
    public final void synpred8_XPointerParser_fragment() throws RecognitionException {
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:93:4: ( NCNAME )
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:93:4: NCNAME
        {
        match(input,NCNAME,FOLLOW_NCNAME_in_synpred8_XPointerParser590); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred8_XPointerParser

    // $ANTLR start synpred10_XPointerParser
    public final void synpred10_XPointerParser_fragment() throws RecognitionException {
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:95:5: ( CHILDSEQUENCE )
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:95:5: CHILDSEQUENCE
        {
        match(input,CHILDSEQUENCE,FOLLOW_CHILDSEQUENCE_in_synpred10_XPointerParser633); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred10_XPointerParser

    // $ANTLR start synpred14_XPointerParser
    public final void synpred14_XPointerParser_fragment() throws RecognitionException {
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:111:20: ( S )
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:111:20: S
        {
        match(input,S,FOLLOW_S_in_synpred14_XPointerParser697); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred14_XPointerParser

    // $ANTLR start synpred17_XPointerParser
    public final void synpred17_XPointerParser_fragment() throws RecognitionException {
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:126:4: ( ESCCIRC )
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:126:4: ESCCIRC
        {
        match(input,ESCCIRC,FOLLOW_ESCCIRC_in_synpred17_XPointerParser758); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred17_XPointerParser

    // $ANTLR start synpred18_XPointerParser
    public final void synpred18_XPointerParser_fragment() throws RecognitionException {
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:127:6: ( ESCLBRACE )
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:127:6: ESCLBRACE
        {
        match(input,ESCLBRACE,FOLLOW_ESCLBRACE_in_synpred18_XPointerParser765); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred18_XPointerParser

    // $ANTLR start synpred19_XPointerParser
    public final void synpred19_XPointerParser_fragment() throws RecognitionException {
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:128:6: ( ESCRBRACE )
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:128:6: ESCRBRACE
        {
        match(input,ESCRBRACE,FOLLOW_ESCRBRACE_in_synpred19_XPointerParser772); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred19_XPointerParser

    // $ANTLR start synpred23_XPointerParser
    public final void synpred23_XPointerParser_fragment() throws RecognitionException {
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:134:7: ( ESCCIRC )
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:134:7: ESCCIRC
        {
        match(input,ESCCIRC,FOLLOW_ESCCIRC_in_synpred23_XPointerParser809); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred23_XPointerParser

    // $ANTLR start synpred24_XPointerParser
    public final void synpred24_XPointerParser_fragment() throws RecognitionException {
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:135:9: ( ESCLBRACE )
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:135:9: ESCLBRACE
        {
        match(input,ESCLBRACE,FOLLOW_ESCLBRACE_in_synpred24_XPointerParser819); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred24_XPointerParser

    // $ANTLR start synpred25_XPointerParser
    public final void synpred25_XPointerParser_fragment() throws RecognitionException {
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:136:9: ( ESCRBRACE )
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:136:9: ESCRBRACE
        {
        match(input,ESCRBRACE,FOLLOW_ESCRBRACE_in_synpred25_XPointerParser829); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred25_XPointerParser

    // $ANTLR start synpred27_XPointerParser
    public final void synpred27_XPointerParser_fragment() throws RecognitionException {
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:138:9: ( function )
        // org/etourdot/xincproc/xpointer/grammar/XPointerParser.g:138:9: function
        {
        pushFollow(FOLLOW_function_in_synpred27_XPointerParser851);
        function();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred27_XPointerParser

    // Delegated rules

    public final boolean synpred17_XPointerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred17_XPointerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred24_XPointerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred24_XPointerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred18_XPointerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred18_XPointerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_XPointerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_XPointerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred14_XPointerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_XPointerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred19_XPointerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred19_XPointerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred27_XPointerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred27_XPointerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_XPointerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_XPointerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred25_XPointerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred25_XPointerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred23_XPointerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred23_XPointerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_NCNAME_in_pointer240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointerpart_in_pointer256 = new BitSet(new long[]{0x00000001C4020202L});
    public static final BitSet FOLLOW_S_in_pointer260 = new BitSet(new long[]{0x00000001C0020200L});
    public static final BitSet FOLLOW_pointerpart_in_pointer263 = new BitSet(new long[]{0x00000001C4020202L});
    public static final BitSet FOLLOW_pointerpart_element_in_pointerpart300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointerpart_xpath_in_pointerpart307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointerpart_xpointer_in_pointerpart314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointerpart_xmlns_in_pointerpart321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointerpart_otherscheme_in_pointerpart328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELEMENT_in_pointerpart_element361 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LBRACE_in_pointerpart_element363 = new BitSet(new long[]{0x00007FFFFFFFFFD0L});
    public static final BitSet FOLLOW_elementschemedata_in_pointerpart_element365 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_RBRACE_in_pointerpart_element367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XPATH_in_pointerpart_xpath409 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LBRACE_in_pointerpart_xpath411 = new BitSet(new long[]{0x00007FFFFFFFFFD0L});
    public static final BitSet FOLLOW_xpathschemedata_in_pointerpart_xpath413 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_RBRACE_in_pointerpart_xpath415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XPOINTER_in_pointerpart_xpointer450 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LBRACE_in_pointerpart_xpointer452 = new BitSet(new long[]{0x00007FFFFFFFFFD0L});
    public static final BitSet FOLLOW_xpathschemedata_in_pointerpart_xpointer454 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_RBRACE_in_pointerpart_xpointer456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XMLNS_in_pointerpart_xmlns497 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LBRACE_in_pointerpart_xmlns499 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_xmlnsschemedata_in_pointerpart_xmlns501 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_RBRACE_in_pointerpart_xmlns503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qname_in_pointerpart_otherscheme538 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LBRACE_in_pointerpart_otherscheme540 = new BitSet(new long[]{0x00007FFFFFFFFFD0L});
    public static final BitSet FOLLOW_schemedata_in_pointerpart_otherscheme542 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_RBRACE_in_pointerpart_otherscheme544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NCNAME_in_elementschemedata590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NCNAME_in_elementschemedata611 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CHILDSEQUENCE_in_elementschemedata613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHILDSEQUENCE_in_elementschemedata633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_escapeddata_in_elementschemedata648 = new BitSet(new long[]{0x00007FFFFDFFFFD2L});
    public static final BitSet FOLLOW_escapeddata_in_schemedata678 = new BitSet(new long[]{0x00007FFFFDFFFFD2L});
    public static final BitSet FOLLOW_NCNAME_in_xmlnsschemedata690 = new BitSet(new long[]{0x0000000004000800L});
    public static final BitSet FOLLOW_S_in_xmlnsschemedata692 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_EQUAL_in_xmlnsschemedata695 = new BitSet(new long[]{0x00007FFFFDFFFFD0L});
    public static final BitSet FOLLOW_S_in_xmlnsschemedata697 = new BitSet(new long[]{0x00007FFFFDFFFFD0L});
    public static final BitSet FOLLOW_escapednamespacename_in_xmlnsschemedata700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_escapeddata_in_escapednamespacename725 = new BitSet(new long[]{0x00007FFFFDFFFFD2L});
    public static final BitSet FOLLOW_xpathescapeddata_in_xpathschemedata737 = new BitSet(new long[]{0x00007FFFFDFFFFD2L});
    public static final BitSet FOLLOW_ESCCIRC_in_escapeddata758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESCLBRACE_in_escapeddata765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESCRBRACE_in_escapeddata772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_escapeddata777 = new BitSet(new long[]{0x00007FFFFFFFFFD0L});
    public static final BitSet FOLLOW_schemedata_in_escapeddata779 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_RBRACE_in_escapeddata781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_escapeddata788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESCCIRC_in_xpathescapeddata809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESCLBRACE_in_xpathescapeddata819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESCRBRACE_in_xpathescapeddata829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_xpathescapeddata837 = new BitSet(new long[]{0x00007FFFFFFFFFD0L});
    public static final BitSet FOLLOW_xpathschemedata_in_xpathescapeddata839 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_RBRACE_in_xpathescapeddata841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_xpathescapeddata851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_xpathescapeddata861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NCNAME_in_qname895 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_COLON_in_qname897 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_NCNAME_in_qname901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NCNAME_in_qname920 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_COLON_in_qname922 = new BitSet(new long[]{0x00000001C0000200L});
    public static final BitSet FOLLOW_set_in_qname924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRINGRANGE_in_function966 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RANGETO_in_function975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POINT_in_function987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RANGE_in_function999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COVERINGRANG_in_function1011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RANGEINSIDE_in_function1023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTPOINT_in_function1035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HERE_in_function1047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ORIGIN_in_function1059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NCNAME_in_synpred8_XPointerParser590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHILDSEQUENCE_in_synpred10_XPointerParser633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_S_in_synpred14_XPointerParser697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESCCIRC_in_synpred17_XPointerParser758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESCLBRACE_in_synpred18_XPointerParser765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESCRBRACE_in_synpred19_XPointerParser772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESCCIRC_in_synpred23_XPointerParser809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESCLBRACE_in_synpred24_XPointerParser819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESCRBRACE_in_synpred25_XPointerParser829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_synpred27_XPointerParser851 = new BitSet(new long[]{0x0000000000000002L});

}