parser grammar XPointerParser;

options {
    language    =   Java;
    output      =   AST;
    superClass  =   AbstractXPointerParser;
    ASTLabelType=   CommonTree;
    tokenVocab  =   XPointerLexer;
    backtrack = true;
    memoize=true;
}

tokens {
    POINTER;
    QNAME;
    ELEMENTSCHEME;
    XPATHSCHEME;
    XMLNSSCHEME;
    XPOINTERSCHEME;
    OTHERSCHEME;
    PREFIX;
    NAMESPACE;
    DATAS;
    LOCALNAME;
    FUNCTION;
}

@header {
    package org.etourdot.xincproc.xpointer;
    import org.etourdot.xincproc.xpointer.exceptions.*;

}


// Parser rules
pointer
    :  	shorthand   -> ^(POINTER shorthand)
	|	schemebased -> ^(POINTER schemebased)
	;
shorthand
	:	NCNAME
	;
schemebased
	:	pointerpart (S? pointerpart)*   -> pointerpart+
	;
pointerpart
	:	ELEMENT LBRACE elementschemedata RBRACE     -> ^(ELEMENTSCHEME elementschemedata)
	|	XPATH LBRACE schemedata RBRACE              -> ^(XPATHSCHEME schemedata)
	| 	XPOINTER LBRACE xpointerschemedata RBRACE   -> ^(XPOINTERSCHEME xpointerschemedata)
	|	XMLNS LBRACE xmlnsschemedata RBRACE         -> ^(XMLNSSCHEME xmlnsschemedata)
	|	otherscheme_pointerpart
	;
	    catch[RecognitionException e] {
            log.debug("ici0");
            recover(input,e);
        }
otherscheme_pointerpart
	:   qname LBRACE schemedata RBRACE              -> ^(OTHERSCHEME qname schemedata)
	;
    catch[RecognitionException e] {
        log.debug("ici1");
        throw e;
    }
elementschemedata
	:	NCNAME          -> ^(DATAS ^(ELEMENT NCNAME))
	|   (NCNAME CHILDSEQUENCE) -> ^(DATAS ^(ELEMENT NCNAME) ^(CHILDSEQUENCE CHILDSEQUENCE))
	| 	CHILDSEQUENCE	-> ^(DATAS ^(CHILDSEQUENCE CHILDSEQUENCE))
	;
schemedata
	:	escapeddata*	-> ^(DATAS escapeddata*)
	;
xmlnsschemedata
	:	NCNAME S? EQUAL S? escapednamespacename	-> ^(DATAS ^(PREFIX NCNAME) ^(NAMESPACE escapednamespacename))
	;
escapednamespacename
	:	escapeddata*	-> ^(DATAS escapeddata*)
	;
xpointerschemedata
	:	escapeddata*	-> ^(DATAS escapeddata*)
	;
escapeddata
	:	~(LBRACE|RBRACE|CIRC|S)
	|	LBRACE! schemedata RBRACE!
	;
qname
    :	(NCNAME COLON)? NCNAME	-> ^(QNAME NCNAME+)
	;
	catch[RecognitionException e] {
        log.debug("ici2");
	    throw e;
    }
function
	:	STRINGRANGE -> ^(FUNCTION STRINGRANGE)
	;