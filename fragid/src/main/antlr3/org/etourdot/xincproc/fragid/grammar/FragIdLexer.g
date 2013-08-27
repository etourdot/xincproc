lexer grammar FragIdLexer;

options {
    language	=   Java;
    superClass  =   AbstractFragIdLexer;
}

@header {
    package org.etourdot.xincproc.fragid.grammar;
}

EQUAL       :   '=';
COMMA       :   ',';
SEMICOLON   :   ';';
CHAR        :   'char';
LINE	    :   'line';
LENGTH	    :   'length';
MD5	        :   'md5';

fragment HEXCAR
            :   ('a'..'f' | 'A'..'F')
            ;

fragment ALPHA
            :   'A'..'Z'
            ;

fragment DIGIT
            :   '0'..'9'
            ;

fragment SPECIALCARS
            :	'!' | '#' | '$' | '%' | '&' | '\'' | '+' | '-' | '^' | '_' | '`' | '{' | '}' | '~'
            ;

NUMBER      :   DIGIT+
            ;

MD5HEX      :   (NUMBER | HEXCAR)+
            ;

CHARSET     :   (ALPHA | DIGIT | SPECIALCARS)+
            ;

S           :   ('\u0009' | '\u000A' | '\u000D' | '\u0020')+
	        ;
