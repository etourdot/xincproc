lexer grammar XPointerLexer;

options {
    language	=	Java;
    superClass = AbstractXPointerLexer;
}

@header {
    package org.etourdot.xincproc.xpointer;
}

ESCCIRC     :   '^^';
ESCLBRACE   :   '^(';
ESCRBRACE   :   '^)';
COLON       :   ':';
EQUAL       :   '=';
CIRC        :   '^';
LBRACE      :   '(';
RBRACE      :   ')';
XPOINTER    :   'xpointer';
XPATH	    :   'xpath';
ELEMENT	    :   'element';
XMLNS	    :   'xmlns';
STRINGRANGE :	'string-range';

DIGIT	    :   '0'..'9';
fragment NCNAMESTARTCHAR
	        :   'A'..'Z' | '_' | 'a'..'z' | '\u00C0'..'\u00D6' | '\u00D8'..'\u00F6' | '\u00F8'..'\u02FF'
	        |   '\u0370'..'\u037D' | '\u037F'..'\u1FFF' | '\u200C'..'\u200D' | '\u2070'..'\u218F' | '\u2C00'..'\u2FEF'
	        |   '\u3001'..'\uD7FF' | '\uF900'..'\uFDCF' | '\uFDF0'..'\uFFFD'
	        ;

fragment NCNAMECHAR
	        :   NCNAMESTARTCHAR | '-' | '.' | '0'..'9' | '\u00B7' | '\u0300'..'\u036F' | '\u203F'..'\u2040'
	        ;

SPECIALCARS
	        :	'"' | '\'' | '_' | '[' | ']' | '/' | ',' | '*' | '-' | '.'
	        ;

NCNAME	    :  NCNAMESTARTCHAR NCNAMECHAR*
	        ;

CHILDSEQUENCE
            :   ('/' '1'..'9' DIGIT*)+
            ;

ENTITY      :   '&' ('A'..'Z' | '_' | 'a'..'z' | DIGIT)+ ';'
	        ;

S           :   ('\u0009' | '\u000A' | '\u000D' | '\u0020')+ { $channel = HIDDEN; }
	        ;