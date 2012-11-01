parser grammar XPointerParser;

options {
    language    =   Java;
    output      =   AST;
    superClass  =   AbstractXPointerParser;
    ASTLabelType=   CommonTree;
    tokenVocab  =   XPointerLexer;
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
}

// Parser rules
pointer
    :  	shorthand   -> ^(POINTER shorthand)
	|	schemebased -> ^(POINTER schemebased)
	;
shorthand
	:	ncname
	;
schemebased
	:	pointerpart (S? pointerpart)*   -> pointerpart+
	;
pointerpart
	:	ELEMENT LBRACE elementschemedata RBRACE     -> ^(ELEMENTSCHEME elementschemedata)
	|	XPATH LBRACE schemedata RBRACE              -> ^(XPATHSCHEME schemedata)
	| 	XPOINTER LBRACE xpointerschemedata RBRACE   -> ^(XPOINTERSCHEME xpointerschemedata)
	|	XMLNS LBRACE xmlnsschemedata RBRACE         -> ^(XMLNSSCHEME xmlnsschemedata)
	|	qname LBRACE schemedata RBRACE              -> ^(OTHERSCHEME qname schemedata)
	;
elementschemedata
	:	ncname          -> ^(DATAS ^(ELEMENT ncname))
	|   (ncname childsequence) -> ^(DATAS ^(ELEMENT ncname) ^(CHILDSEQUENCE childsequence))
	| 	childsequence	-> ^(DATAS ^(CHILDSEQUENCE childsequence))
	;
childsequence
	:	CHILDSEQUENCE
	;
schemedata
	:	escapeddata*	-> ^(DATAS escapeddata*)
	;
xmlnsschemedata
	:	ncname S? EQUAL S? escapednamespacename	-> ^(DATAS ^(PREFIX ncname) ^(NAMESPACE escapednamespacename))
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
qname	:	p=ncname (COLON l=ncname)?	-> ^(QNAME ^(PREFIX $p) ^(LOCALNAME $l))
	;
ncname	:	NCNAME
	;
function
	:	STRINGRANGE -> ^(FUNCTION STRINGRANGE)
	;