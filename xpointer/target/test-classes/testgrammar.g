grammar testgrammar;

options {
    language	=   Java;
    output      =   AST;
    ASTLabelType=   CommonTree;
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

// Lexer rules
ESCCIRC     :   '^^';
ESCLBRACE   :   '^(';
ESCRBRACE   :   '^)';
COLON       :   ':';
EQUAL       :   '=';
CIRC        :   '^';
LBRACE      :   '(';
RBRACE      :   ')';
/*QUOTE	    :	'"';
SIMPLEQUOTE :	'\'';
UNDERSCORE  :   '_';
LBRACKET    :	'[';	
RBRACKET    :	']';
SLASH	    :	'/';
COMMA	    :	',';
STAR	    :   '*';*/
XPOINTER    :   'xpointer';
XPATH	    :   'xpath';
ELEMENT	    :   'element';
XMLNS	    :   'xmlns';
STRINGRANGE :	'string-range';

DIGIT	    :   '0'..'9';
fragment NCNAMESTARTCHAR
	: 'A'..'Z' | '_' | 'a'..'z' | '\u00C0'..'\u00D6' | '\u00D8'..'\u00F6' | '\u00F8'..'\u02FF' | '\u0370'..'\u037D' | 
	  '\u037F'..'\u1FFF' | '\u200C'..'\u200D' | '\u2070'..'\u218F' | '\u2C00'..'\u2FEF' | '\u3001'..'\uD7FF' | '\uF900'..'\uFDCF' | '\uFDF0'..'\uFFFD'
	;
	
fragment NCNAMECHAR
	:   	NCNAMESTARTCHAR | '-' | '.' | '0'..'9' | '\u00B7' | '\u0300'..'\u036F' | '\u203F'..'\u2040'
	;
	
SPECIALCARS
	:	'"' | '\'' | '_' | '[' | ']' | '/' | ',' | '*' | '-'
	;
	
NCNAME	:  NCNAMESTARTCHAR NCNAMECHAR*
	;

CHILDSEQUENCE
        :   ('/' '1'..'9' DIGIT*)+
        ;
ENTITY  :   '&' ('A'..'Z' | '_' | 'a'..'z' | DIGIT)+ ';'
	;
S       :   ('\u0009' | '\u000A' | '\u000D' | '\u0020')+ { $channel = HIDDEN; }
	;

pointerHelper
        :   	shorthand   -> ^(POINTER shorthand)
	|	schemebased -> ^(POINTER schemebased)
	;
shorthand
	:	ncname
	;
schemebased
	:	pointerpart (S? pointerpart)*
	;
pointerpart
	:	ELEMENT LBRACE elementschemedata RBRACE     -> ^(ELEMENTSCHEME elementschemedata)
	|	XPATH LBRACE schemedata RBRACE              -> ^(XPATHSCHEME schemedata)
	| 	XPOINTER LBRACE xpointerschemedata RBRACE   -> ^(XPOINTERSCHEME xpointerschemedata)
	|	XMLNS LBRACE xmlnsschemedata RBRACE         -> ^(XMLNSSCHEME xmlnsschemedata)
	|	qname LBRACE schemedata RBRACE              -> ^(OTHERSCHEME qname schemedata)
	;
elementschemedata
	:	(ncname childsequence?) -> ^(DATAS ^(ELEMENT ncname) ^(CHILDSEQUENCE childsequence)?)
	| 	childsequence	-> ^(DATAS ^(CHILDSEQUENCE childsequence))
	;
childsequence
	:	CHILDSEQUENCE
	;
schemedata
	:	escapeddata*	-> ^(DATAS escapeddata*)
	;
xmlnsschemedata 
	:	ncname S? EQUAL S? n=escapednamespacename	-> ^(DATAS ^(PREFIX ncname) ^(NAMESPACE $n))
	;
escapednamespacename 
	:	escapeddata*
	;
xpointerschemedata
	:	escapeddata*
	;
escapeddata
	:	~(LBRACE|RBRACE|CIRC|S)
	|	LBRACE schemedata RBRACE
	;
qname	:	p=ncname (COLON l=ncname)?	-> ^(QNAME ^(PREFIX $p) ^(LOCALNAME $l))
	;
ncname	:	NCNAME^
	;
function
	:	STRINGRANGE -> ^(FUNCTION STRINGRANGE)
	;