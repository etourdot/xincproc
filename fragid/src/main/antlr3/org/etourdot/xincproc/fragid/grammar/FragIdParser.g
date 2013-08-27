parser grammar FragIdParser;

options {
    language    =   Java;
    output      =   AST;
    superClass  =   AbstractFragIdParser;
    ASTLabelType=   CommonTree;
    tokenVocab  =   FragIdLexer;
    backtrack   =   true;
    memoize     =   true;
}

tokens {
    CHAR;
    LINE;
    LENGTH;
    MD5;
    RANGE;
    STARTRANGE;
    ENDRANGE;
    POSITION;
}

@header {
    package org.etourdot.xincproc.fragid.grammar;
}

// Parser rules
textfragment
    : textscheme (SEMICOLON integritycheck)?
    ;

textscheme
    : charscheme
    | linescheme
    ;

charscheme
    : CHAR EQUAL range -> ^(CHAR range)
    ;

linescheme
    : LINE EQUAL range -> ^(LINE range)
    ;

range
    : n1=NUMBER COMMA n2=NUMBER -> ^(RANGE ^(STARTRANGE $n1) ^(ENDRANGE $n2))
    | COMMA n3=NUMBER -> ^(RANGE ^(ENDRANGE $n3))
    | n4=NUMBER -> ^(RANGE ^(POSITION $n4))
    | n5=NUMBER COMMA -> ^(RANGE ^(STARTRANGE $n5))
    | COMMA -> RANGE
    ;

integritycheck
    : lengthscheme (COMMA mimecharset)?
    | md5scheme (COMMA mimecharset)?
    ;

lengthscheme
    : LENGTH EQUAL n1=NUMBER -> ^(LENGTH $n1)
    ;

md5scheme
    : MD5 EQUAL n1=MD5HEX -> ^(MD5 $n1)
    ;

mimecharset
    : CHARSET
    ;