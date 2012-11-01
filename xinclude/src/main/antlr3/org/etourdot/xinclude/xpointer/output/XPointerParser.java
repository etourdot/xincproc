// $ANTLR 3.2 Sep 23, 2009 12:02:23 D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g 2011-05-15 12:05:27

	package org.etourdot.xinclude.xpointer;
	
	import javax.xml.namespace.QName;
	import org.etourdot.xinclude.XIncProcConfiguration;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.debug.*;
import java.io.IOException;

import org.antlr.runtime.tree.*;

public class XPointerParser extends DebugParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "XPOINTER", "XPATH", "ELEMENT", "XMLNS", "ESCCIRC", "ESCLBRACE", "ESCRBRACE", "COLON", "CIRC", "LBRACE", "RBRACE", "EQUAL", "NCNAMESTARTCHAR", "NCNAMECHAR", "NORMALCHAR", "S", "NCNAME", "FNNAME"
    };
    public static final int CIRC=12;
    public static final int RBRACE=14;
    public static final int LBRACE=13;
    public static final int NCNAMECHAR=17;
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

    public static final String[] ruleNames = new String[] {
        "invalidRule", "fnname", "pointerpart", "shorthand", "ncname", 
        "schemebased", "schemedata", "pointer", "escapeddata", "xpathexpr", 
        "xmlnsexpr", "qname"
    };
     
        public int ruleLevel = 0;
        public int getRuleLevel() { return ruleLevel; }
        public void incRuleLevel() { ruleLevel++; }
        public void decRuleLevel() { ruleLevel--; }
        public XPointerParser(TokenStream input) {
            this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
        }
        public XPointerParser(TokenStream input, int port, RecognizerSharedState state) {
            super(input, state);
            DebugEventSocketProxy proxy =
                new DebugEventSocketProxy(this,port,adaptor);
            setDebugListener(proxy);
            setTokenStream(new DebugTokenStream(input,proxy));
            try {
                proxy.handshake();
            }
            catch (IOException ioe) {
                reportError(ioe);
            }
            TreeAdaptor adap = new CommonTreeAdaptor();
            setTreeAdaptor(adap);
            proxy.setTreeAdaptor(adap);
        }
    public XPointerParser(TokenStream input, DebugEventListener dbg) {
        super(input, dbg);

         
        TreeAdaptor adap = new CommonTreeAdaptor();
        setTreeAdaptor(adap);

    }
    protected boolean evalPredicate(boolean result, String predicate) {
        dbg.semanticPredicate(result, predicate);
        return result;
    }

    protected DebugTreeAdaptor adaptor;
    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = new DebugTreeAdaptor(dbg,adaptor);

    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }


    public String[] getTokenNames() { return XPointerParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g"; }


    	private XPointer xpointer;
    	
            public XPointerParser(TokenStream input, XPointerContext context) {
                	this(input);
                 	xpointer = new XPointer();
            	xpointer.setContext(context);
            }
            
            private XPointerContext getContext() {
            	return xpointer.getContext();
            }
            
            private XIncProcConfiguration getConfiguration() {
            	return getContext().getConfiguration();
           }
            
    	public XPointer getXPointer() {
    		return this.xpointer;
    	}
    	public String unescape(String s) {
    		return s.replaceAll("\\^\\)",")").replaceAll("\\^\\(","(").replaceAll("\\^\\^","^");
    	}


    public static class pointer_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pointer"
    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:85:1: pointer : ( shorthand | schemebased );
    public final XPointerParser.pointer_return pointer() throws RecognitionException {
        XPointerParser.pointer_return retval = new XPointerParser.pointer_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        XPointerParser.shorthand_return shorthand1 = null;

        XPointerParser.schemebased_return schemebased2 = null;



        try { dbg.enterRule(getGrammarFileName(), "pointer");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(85, 1);

        try {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:85:13: ( shorthand | schemebased )
            int alt1=2;
            try { dbg.enterDecision(1);

            int LA1_0 = input.LA(1);

            if ( (LA1_0==NCNAME) ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==EOF) ) {
                    alt1=1;
                }
                else if ( (LA1_1==COLON||LA1_1==LBRACE) ) {
                    alt1=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else if ( ((LA1_0>=XPOINTER && LA1_0<=XMLNS)) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(1);}

            switch (alt1) {
                case 1 :
                    dbg.enterAlt(1);

                    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:85:17: shorthand
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(85,17);
                    pushFollow(FOLLOW_shorthand_in_pointer603);
                    shorthand1=shorthand();

                    state._fsp--;

                    adaptor.addChild(root_0, shorthand1.getTree());

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:85:29: schemebased
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(85,29);
                    pushFollow(FOLLOW_schemebased_in_pointer607);
                    schemebased2=schemebased();

                    state._fsp--;

                    adaptor.addChild(root_0, schemebased2.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(85, 40);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "pointer");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "pointer"

    public static class shorthand_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "shorthand"
    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:87:1: shorthand : ncname ;
    public final XPointerParser.shorthand_return shorthand() throws RecognitionException {
        XPointerParser.shorthand_return retval = new XPointerParser.shorthand_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        XPointerParser.ncname_return ncname3 = null;



        try { dbg.enterRule(getGrammarFileName(), "shorthand");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(87, 1);

        try {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:87:11: ( ncname )
            dbg.enterAlt(1);

            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:87:13: ncname
            {
            root_0 = (CommonTree)adaptor.nil();

            dbg.location(87,13);
            pushFollow(FOLLOW_ncname_in_shorthand615);
            ncname3=ncname();

            state._fsp--;

            adaptor.addChild(root_0, ncname3.getTree());
            dbg.location(88,3);

            			xpointer.addPointerPart(getConfiguration().getXPointerEngine().createPointerPart((ncname3!=null?input.toString(ncname3.start,ncname3.stop):null), getContext()));
            		

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(91, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "shorthand");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "shorthand"

    public static class schemebased_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "schemebased"
    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:92:1: schemebased : pointerpart ( ( S )? pointerpart )* ;
    public final XPointerParser.schemebased_return schemebased() throws RecognitionException {
        XPointerParser.schemebased_return retval = new XPointerParser.schemebased_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token S5=null;
        XPointerParser.pointerpart_return pointerpart4 = null;

        XPointerParser.pointerpart_return pointerpart6 = null;


        CommonTree S5_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "schemebased");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(92, 1);

        try {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:92:13: ( pointerpart ( ( S )? pointerpart )* )
            dbg.enterAlt(1);

            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:92:15: pointerpart ( ( S )? pointerpart )*
            {
            root_0 = (CommonTree)adaptor.nil();

            dbg.location(92,15);
            pushFollow(FOLLOW_pointerpart_in_schemebased629);
            pointerpart4=pointerpart();

            state._fsp--;

            adaptor.addChild(root_0, pointerpart4.getTree());
            dbg.location(92,27);
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:92:27: ( ( S )? pointerpart )*
            try { dbg.enterSubRule(3);

            loop3:
            do {
                int alt3=2;
                try { dbg.enterDecision(3);

                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=XPOINTER && LA3_0<=XMLNS)||(LA3_0>=S && LA3_0<=NCNAME)) ) {
                    alt3=1;
                }


                } finally {dbg.exitDecision(3);}

                switch (alt3) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:92:28: ( S )? pointerpart
            	    {
            	    dbg.location(92,28);
            	    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:92:28: ( S )?
            	    int alt2=2;
            	    try { dbg.enterSubRule(2);
            	    try { dbg.enterDecision(2);

            	    int LA2_0 = input.LA(1);

            	    if ( (LA2_0==S) ) {
            	        alt2=1;
            	    }
            	    } finally {dbg.exitDecision(2);}

            	    switch (alt2) {
            	        case 1 :
            	            dbg.enterAlt(1);

            	            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:92:28: S
            	            {
            	            dbg.location(92,28);
            	            S5=(Token)match(input,S,FOLLOW_S_in_schemebased632); 
            	            S5_tree = (CommonTree)adaptor.create(S5);
            	            adaptor.addChild(root_0, S5_tree);


            	            }
            	            break;

            	    }
            	    } finally {dbg.exitSubRule(2);}

            	    dbg.location(92,31);
            	    pushFollow(FOLLOW_pointerpart_in_schemebased635);
            	    pointerpart6=pointerpart();

            	    state._fsp--;

            	    adaptor.addChild(root_0, pointerpart6.getTree());

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);
            } finally {dbg.exitSubRule(3);}


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(92, 44);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "schemebased");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "schemebased"

    public static class pointerpart_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pointerpart"
    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:93:1: pointerpart : ( ELEMENT LBRACE ncname RBRACE | ( XPATH | XPOINTER ) LBRACE xpathexpr RBRACE | XMLNS LBRACE xmlnsexpr RBRACE | qname LBRACE schemedata RBRACE );
    public final XPointerParser.pointerpart_return pointerpart() throws RecognitionException {
        XPointerParser.pointerpart_return retval = new XPointerParser.pointerpart_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ELEMENT7=null;
        Token LBRACE8=null;
        Token RBRACE10=null;
        Token set11=null;
        Token LBRACE12=null;
        Token RBRACE14=null;
        Token XMLNS15=null;
        Token LBRACE16=null;
        Token RBRACE18=null;
        Token LBRACE20=null;
        Token RBRACE22=null;
        XPointerParser.ncname_return ncname9 = null;

        XPointerParser.xpathexpr_return xpathexpr13 = null;

        XPointerParser.xmlnsexpr_return xmlnsexpr17 = null;

        XPointerParser.qname_return qname19 = null;

        XPointerParser.schemedata_return schemedata21 = null;


        CommonTree ELEMENT7_tree=null;
        CommonTree LBRACE8_tree=null;
        CommonTree RBRACE10_tree=null;
        CommonTree set11_tree=null;
        CommonTree LBRACE12_tree=null;
        CommonTree RBRACE14_tree=null;
        CommonTree XMLNS15_tree=null;
        CommonTree LBRACE16_tree=null;
        CommonTree RBRACE18_tree=null;
        CommonTree LBRACE20_tree=null;
        CommonTree RBRACE22_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "pointerpart");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(93, 1);

        try {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:93:13: ( ELEMENT LBRACE ncname RBRACE | ( XPATH | XPOINTER ) LBRACE xpathexpr RBRACE | XMLNS LBRACE xmlnsexpr RBRACE | qname LBRACE schemedata RBRACE )
            int alt4=4;
            try { dbg.enterDecision(4);

            switch ( input.LA(1) ) {
            case ELEMENT:
                {
                alt4=1;
                }
                break;
            case XPOINTER:
            case XPATH:
                {
                alt4=2;
                }
                break;
            case XMLNS:
                {
                alt4=3;
                }
                break;
            case NCNAME:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(4);}

            switch (alt4) {
                case 1 :
                    dbg.enterAlt(1);

                    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:93:15: ELEMENT LBRACE ncname RBRACE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(93,15);
                    ELEMENT7=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_pointerpart644); 
                    ELEMENT7_tree = (CommonTree)adaptor.create(ELEMENT7);
                    adaptor.addChild(root_0, ELEMENT7_tree);

                    dbg.location(93,29);
                    LBRACE8=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_pointerpart646); 
                    dbg.location(93,31);
                    pushFollow(FOLLOW_ncname_in_pointerpart649);
                    ncname9=ncname();

                    state._fsp--;

                    adaptor.addChild(root_0, ncname9.getTree());
                    dbg.location(93,44);
                    RBRACE10=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_pointerpart651); 
                    dbg.location(94,4);

                    				xpointer.addPointerPart(getConfiguration().getXPointerEngine().createPointerPart(new QName("element"), (ncname9!=null?input.toString(ncname9.start,ncname9.stop):null), getContext()));
                    			

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:98:4: ( XPATH | XPOINTER ) LBRACE xpathexpr RBRACE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(98,4);
                    set11=(Token)input.LT(1);
                    if ( (input.LA(1)>=XPOINTER && input.LA(1)<=XPATH) ) {
                        input.consume();
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(set11));
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        dbg.recognitionException(mse);
                        throw mse;
                    }

                    dbg.location(98,29);
                    LBRACE12=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_pointerpart675); 
                    dbg.location(98,31);
                    pushFollow(FOLLOW_xpathexpr_in_pointerpart678);
                    xpathexpr13=xpathexpr();

                    state._fsp--;

                    adaptor.addChild(root_0, xpathexpr13.getTree());
                    dbg.location(98,47);
                    RBRACE14=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_pointerpart680); 
                    dbg.location(99,4);

                    				StringBuilder builder = new StringBuilder();
                    				for (final Object aData : (xpathexpr13!=null?xpathexpr13.datas:null))
                    				{
                    					builder.append(((CommonTree)aData).toStringTree());
                    				}
                    				xpointer.addPointerPart(getConfiguration().getXPointerEngine().createPointerPart(new QName("xpath"), unescape(builder.toString()), getContext()));
                    			

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:108:4: XMLNS LBRACE xmlnsexpr RBRACE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(108,4);
                    XMLNS15=(Token)match(input,XMLNS,FOLLOW_XMLNS_in_pointerpart696); 
                    XMLNS15_tree = (CommonTree)adaptor.create(XMLNS15);
                    adaptor.addChild(root_0, XMLNS15_tree);

                    dbg.location(108,16);
                    LBRACE16=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_pointerpart698); 
                    dbg.location(108,18);
                    pushFollow(FOLLOW_xmlnsexpr_in_pointerpart701);
                    xmlnsexpr17=xmlnsexpr();

                    state._fsp--;

                    adaptor.addChild(root_0, xmlnsexpr17.getTree());
                    dbg.location(108,34);
                    RBRACE18=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_pointerpart703); 
                    dbg.location(109,4);

                    				getContext().addNamespace((xmlnsexpr17!=null?xmlnsexpr17.qname:null));
                    			

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:113:4: qname LBRACE schemedata RBRACE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(113,4);
                    pushFollow(FOLLOW_qname_in_pointerpart719);
                    qname19=qname();

                    state._fsp--;

                    adaptor.addChild(root_0, qname19.getTree());
                    dbg.location(113,16);
                    LBRACE20=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_pointerpart721); 
                    dbg.location(113,18);
                    pushFollow(FOLLOW_schemedata_in_pointerpart724);
                    schemedata21=schemedata();

                    state._fsp--;

                    adaptor.addChild(root_0, schemedata21.getTree());
                    dbg.location(113,35);
                    RBRACE22=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_pointerpart726); 
                    dbg.location(114,4);

                    				StringBuilder builder = new StringBuilder();
                    				for (final Object aData : (schemedata21!=null?schemedata21.datas:null))
                    				{
                    					builder.append(((CommonTree)aData).toStringTree());
                    				}
                    				xpointer.addPointerPart(getConfiguration().getXPointerEngine().createPointerPart((qname19!=null?qname19.qname:null), unescape(builder.toString()), getContext()));
                    			

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(122, 4);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "pointerpart");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "pointerpart"

    public static class schemedata_return extends ParserRuleReturnScope {
        public List datas;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "schemedata"
    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:123:1: schemedata returns [List datas] : (data+= escapeddata )+ ;
    public final XPointerParser.schemedata_return schemedata() throws RecognitionException {
        XPointerParser.schemedata_return retval = new XPointerParser.schemedata_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        List list_data=null;
        RuleReturnScope data = null;

        try { dbg.enterRule(getGrammarFileName(), "schemedata");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(123, 1);

        try {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:124:3: ( (data+= escapeddata )+ )
            dbg.enterAlt(1);

            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:124:5: (data+= escapeddata )+
            {
            root_0 = (CommonTree)adaptor.nil();

            dbg.location(124,5);
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:124:5: (data+= escapeddata )+
            int cnt5=0;
            try { dbg.enterSubRule(5);

            loop5:
            do {
                int alt5=2;
                try { dbg.enterDecision(5);

                int LA5_0 = input.LA(1);

                if ( (LA5_0==LBRACE||LA5_0==NCNAME) ) {
                    alt5=1;
                }


                } finally {dbg.exitDecision(5);}

                switch (alt5) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:124:6: data+= escapeddata
            	    {
            	    dbg.location(124,10);
            	    pushFollow(FOLLOW_escapeddata_in_schemedata751);
            	    data=escapeddata();

            	    state._fsp--;

            	    adaptor.addChild(root_0, data.getTree());
            	    if (list_data==null) list_data=new ArrayList();
            	    list_data.add(data.getTree());


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt5++;
            } while (true);
            } finally {dbg.exitSubRule(5);}

            dbg.location(124,26);
            retval.datas = list_data;

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(124, 43);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "schemedata");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "schemedata"

    public static class qname_return extends ParserRuleReturnScope {
        public QName qname;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "qname"
    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:125:1: qname returns [QName qname] : p= ncname ( COLON l= ncname )? ;
    public final XPointerParser.qname_return qname() throws RecognitionException {
        XPointerParser.qname_return retval = new XPointerParser.qname_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COLON23=null;
        XPointerParser.ncname_return p = null;

        XPointerParser.ncname_return l = null;


        CommonTree COLON23_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "qname");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(125, 1);

        try {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:126:3: (p= ncname ( COLON l= ncname )? )
            dbg.enterAlt(1);

            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:126:6: p= ncname ( COLON l= ncname )?
            {
            root_0 = (CommonTree)adaptor.nil();

            dbg.location(126,7);
            pushFollow(FOLLOW_ncname_in_qname770);
            p=ncname();

            state._fsp--;

            adaptor.addChild(root_0, p.getTree());
            dbg.location(126,15);
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:126:15: ( COLON l= ncname )?
            int alt6=2;
            try { dbg.enterSubRule(6);
            try { dbg.enterDecision(6);

            int LA6_0 = input.LA(1);

            if ( (LA6_0==COLON) ) {
                alt6=1;
            }
            } finally {dbg.exitDecision(6);}

            switch (alt6) {
                case 1 :
                    dbg.enterAlt(1);

                    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:126:16: COLON l= ncname
                    {
                    dbg.location(126,21);
                    COLON23=(Token)match(input,COLON,FOLLOW_COLON_in_qname773); 
                    dbg.location(126,24);
                    pushFollow(FOLLOW_ncname_in_qname778);
                    l=ncname();

                    state._fsp--;

                    adaptor.addChild(root_0, l.getTree());

                    }
                    break;

            }
            } finally {dbg.exitSubRule(6);}

            dbg.location(127,4);

            				if ((l!=null?input.toString(l.start,l.stop):null) != null) { retval.qname = new QName("",(p!=null?input.toString(p.start,p.stop):null),(l!=null?input.toString(l.start,l.stop):null)); }
            				else { retval.qname = new QName((p!=null?input.toString(p.start,p.stop):null));}
            			

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(131, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "qname");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "qname"

    public static class ncname_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ncname"
    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:132:1: ncname : NCNAME ;
    public final XPointerParser.ncname_return ncname() throws RecognitionException {
        XPointerParser.ncname_return retval = new XPointerParser.ncname_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NCNAME24=null;

        CommonTree NCNAME24_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "ncname");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(132, 1);

        try {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:132:10: ( NCNAME )
            dbg.enterAlt(1);

            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:132:13: NCNAME
            {
            root_0 = (CommonTree)adaptor.nil();

            dbg.location(132,13);
            NCNAME24=(Token)match(input,NCNAME,FOLLOW_NCNAME_in_ncname798); 
            NCNAME24_tree = (CommonTree)adaptor.create(NCNAME24);
            adaptor.addChild(root_0, NCNAME24_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(132, 19);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "ncname");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "ncname"

    public static class fnname_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fnname"
    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:133:1: fnname : ( FNNAME | LBRACE ( fnname )? RBRACE );
    public final XPointerParser.fnname_return fnname() throws RecognitionException {
        XPointerParser.fnname_return retval = new XPointerParser.fnname_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token FNNAME25=null;
        Token LBRACE26=null;
        Token RBRACE28=null;
        XPointerParser.fnname_return fnname27 = null;


        CommonTree FNNAME25_tree=null;
        CommonTree LBRACE26_tree=null;
        CommonTree RBRACE28_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "fnname");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(133, 1);

        try {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:133:9: ( FNNAME | LBRACE ( fnname )? RBRACE )
            int alt8=2;
            try { dbg.enterDecision(8);

            int LA8_0 = input.LA(1);

            if ( (LA8_0==FNNAME) ) {
                alt8=1;
            }
            else if ( (LA8_0==LBRACE) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(8);}

            switch (alt8) {
                case 1 :
                    dbg.enterAlt(1);

                    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:133:11: FNNAME
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(133,11);
                    FNNAME25=(Token)match(input,FNNAME,FOLLOW_FNNAME_in_fnname806); 
                    FNNAME25_tree = (CommonTree)adaptor.create(FNNAME25);
                    adaptor.addChild(root_0, FNNAME25_tree);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:133:20: LBRACE ( fnname )? RBRACE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(133,20);
                    LBRACE26=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_fnname810); 
                    LBRACE26_tree = (CommonTree)adaptor.create(LBRACE26);
                    adaptor.addChild(root_0, LBRACE26_tree);

                    dbg.location(133,27);
                    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:133:27: ( fnname )?
                    int alt7=2;
                    try { dbg.enterSubRule(7);
                    try { dbg.enterDecision(7);

                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==LBRACE||LA7_0==FNNAME) ) {
                        alt7=1;
                    }
                    } finally {dbg.exitDecision(7);}

                    switch (alt7) {
                        case 1 :
                            dbg.enterAlt(1);

                            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:133:27: fnname
                            {
                            dbg.location(133,27);
                            pushFollow(FOLLOW_fnname_in_fnname812);
                            fnname27=fnname();

                            state._fsp--;

                            adaptor.addChild(root_0, fnname27.getTree());

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(7);}

                    dbg.location(133,35);
                    RBRACE28=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_fnname815); 
                    RBRACE28_tree = (CommonTree)adaptor.create(RBRACE28);
                    adaptor.addChild(root_0, RBRACE28_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(133, 41);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "fnname");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "fnname"

    public static class escapeddata_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "escapeddata"
    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:134:1: escapeddata : ( ncname | LBRACE schemedata RBRACE );
    public final XPointerParser.escapeddata_return escapeddata() throws RecognitionException {
        XPointerParser.escapeddata_return retval = new XPointerParser.escapeddata_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LBRACE30=null;
        Token RBRACE32=null;
        XPointerParser.ncname_return ncname29 = null;

        XPointerParser.schemedata_return schemedata31 = null;


        CommonTree LBRACE30_tree=null;
        CommonTree RBRACE32_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "escapeddata");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(134, 1);

        try {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:134:14: ( ncname | LBRACE schemedata RBRACE )
            int alt9=2;
            try { dbg.enterDecision(9);

            int LA9_0 = input.LA(1);

            if ( (LA9_0==NCNAME) ) {
                alt9=1;
            }
            else if ( (LA9_0==LBRACE) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(9);}

            switch (alt9) {
                case 1 :
                    dbg.enterAlt(1);

                    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:134:16: ncname
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(134,16);
                    pushFollow(FOLLOW_ncname_in_escapeddata823);
                    ncname29=ncname();

                    state._fsp--;

                    adaptor.addChild(root_0, ncname29.getTree());

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:134:25: LBRACE schemedata RBRACE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(134,25);
                    LBRACE30=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_escapeddata827); 
                    LBRACE30_tree = (CommonTree)adaptor.create(LBRACE30);
                    adaptor.addChild(root_0, LBRACE30_tree);

                    dbg.location(134,32);
                    pushFollow(FOLLOW_schemedata_in_escapeddata829);
                    schemedata31=schemedata();

                    state._fsp--;

                    adaptor.addChild(root_0, schemedata31.getTree());
                    dbg.location(134,43);
                    RBRACE32=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_escapeddata831); 
                    RBRACE32_tree = (CommonTree)adaptor.create(RBRACE32);
                    adaptor.addChild(root_0, RBRACE32_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(134, 49);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "escapeddata");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "escapeddata"

    public static class xmlnsexpr_return extends ParserRuleReturnScope {
        public QName qname;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "xmlnsexpr"
    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:136:1: xmlnsexpr returns [QName qname] : p= ncname EQUAL u= ncname ;
    public final XPointerParser.xmlnsexpr_return xmlnsexpr() throws RecognitionException {
        XPointerParser.xmlnsexpr_return retval = new XPointerParser.xmlnsexpr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EQUAL33=null;
        XPointerParser.ncname_return p = null;

        XPointerParser.ncname_return u = null;


        CommonTree EQUAL33_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "xmlnsexpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(136, 1);

        try {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:137:4: (p= ncname EQUAL u= ncname )
            dbg.enterAlt(1);

            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:137:6: p= ncname EQUAL u= ncname
            {
            root_0 = (CommonTree)adaptor.nil();

            dbg.location(137,7);
            pushFollow(FOLLOW_ncname_in_xmlnsexpr847);
            p=ncname();

            state._fsp--;

            adaptor.addChild(root_0, p.getTree());
            dbg.location(137,15);
            EQUAL33=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_xmlnsexpr849); 
            EQUAL33_tree = (CommonTree)adaptor.create(EQUAL33);
            adaptor.addChild(root_0, EQUAL33_tree);

            dbg.location(137,22);
            pushFollow(FOLLOW_ncname_in_xmlnsexpr853);
            u=ncname();

            state._fsp--;

            adaptor.addChild(root_0, u.getTree());
            dbg.location(138,5);

            	 			retval.qname = new QName("", "", (p!=null?input.toString(p.start,p.stop):null));
            	 		

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(141, 4);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "xmlnsexpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "xmlnsexpr"

    public static class xpathexpr_return extends ParserRuleReturnScope {
        public List datas;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "xpathexpr"
    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:142:1: xpathexpr returns [List datas] : (data+= fnname )+ ;
    public final XPointerParser.xpathexpr_return xpathexpr() throws RecognitionException {
        XPointerParser.xpathexpr_return retval = new XPointerParser.xpathexpr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        List list_data=null;
        RuleReturnScope data = null;

        try { dbg.enterRule(getGrammarFileName(), "xpathexpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(142, 1);

        try {
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:143:3: ( (data+= fnname )+ )
            dbg.enterAlt(1);

            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:143:5: (data+= fnname )+
            {
            root_0 = (CommonTree)adaptor.nil();

            dbg.location(143,5);
            // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:143:5: (data+= fnname )+
            int cnt10=0;
            try { dbg.enterSubRule(10);

            loop10:
            do {
                int alt10=2;
                try { dbg.enterDecision(10);

                int LA10_0 = input.LA(1);

                if ( (LA10_0==LBRACE||LA10_0==FNNAME) ) {
                    alt10=1;
                }


                } finally {dbg.exitDecision(10);}

                switch (alt10) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // D:\\perso\\xincproc-legacy\\src\\main\\antlr3\\org\\etourdot\\xinclude\\xpointer\\XPointer.g:143:6: data+= fnname
            	    {
            	    dbg.location(143,10);
            	    pushFollow(FOLLOW_fnname_in_xpathexpr878);
            	    data=fnname();

            	    state._fsp--;

            	    adaptor.addChild(root_0, data.getTree());
            	    if (list_data==null) list_data=new ArrayList();
            	    list_data.add(data.getTree());


            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt10++;
            } while (true);
            } finally {dbg.exitSubRule(10);}

            dbg.location(143,21);
            retval.datas = list_data;

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(143, 38);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "xpathexpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "xpathexpr"

    // Delegated rules


 

    public static final BitSet FOLLOW_shorthand_in_pointer603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_schemebased_in_pointer607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ncname_in_shorthand615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointerpart_in_schemebased629 = new BitSet(new long[]{0x00000000001800F2L});
    public static final BitSet FOLLOW_S_in_schemebased632 = new BitSet(new long[]{0x00000000001800F0L});
    public static final BitSet FOLLOW_pointerpart_in_schemebased635 = new BitSet(new long[]{0x00000000001800F2L});
    public static final BitSet FOLLOW_ELEMENT_in_pointerpart644 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LBRACE_in_pointerpart646 = new BitSet(new long[]{0x00000000001800F0L});
    public static final BitSet FOLLOW_ncname_in_pointerpart649 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RBRACE_in_pointerpart651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_pointerpart667 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LBRACE_in_pointerpart675 = new BitSet(new long[]{0x0000000000202000L});
    public static final BitSet FOLLOW_xpathexpr_in_pointerpart678 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RBRACE_in_pointerpart680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XMLNS_in_pointerpart696 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LBRACE_in_pointerpart698 = new BitSet(new long[]{0x00000000001800F0L});
    public static final BitSet FOLLOW_xmlnsexpr_in_pointerpart701 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RBRACE_in_pointerpart703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qname_in_pointerpart719 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LBRACE_in_pointerpart721 = new BitSet(new long[]{0x00000000001820F0L});
    public static final BitSet FOLLOW_schemedata_in_pointerpart724 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RBRACE_in_pointerpart726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_escapeddata_in_schemedata751 = new BitSet(new long[]{0x00000000001820F2L});
    public static final BitSet FOLLOW_ncname_in_qname770 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_COLON_in_qname773 = new BitSet(new long[]{0x00000000001800F0L});
    public static final BitSet FOLLOW_ncname_in_qname778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NCNAME_in_ncname798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FNNAME_in_fnname806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_fnname810 = new BitSet(new long[]{0x0000000000206000L});
    public static final BitSet FOLLOW_fnname_in_fnname812 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RBRACE_in_fnname815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ncname_in_escapeddata823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_escapeddata827 = new BitSet(new long[]{0x00000000001820F0L});
    public static final BitSet FOLLOW_schemedata_in_escapeddata829 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RBRACE_in_escapeddata831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ncname_in_xmlnsexpr847 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_EQUAL_in_xmlnsexpr849 = new BitSet(new long[]{0x00000000001800F0L});
    public static final BitSet FOLLOW_ncname_in_xmlnsexpr853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fnname_in_xpathexpr878 = new BitSet(new long[]{0x0000000000202002L});

}