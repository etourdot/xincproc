parser grammar XPointerParser;

options {
    language    =   Java;
    output      =   AST;
    superClass  =   AbstractXPointerParser;
    ASTLabelType=   CommonTree;
    tokenVocab  =   XPointerLexer;
    backtrack   =   true;
    memoize     =   true;
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
    SCHEMEBASED;
}

@header {
    package org.etourdot.xincproc.xpointer;
    import org.etourdot.xincproc.xpointer.exceptions.*;

}

// Parser rules
pointer
    :  	NCNAME   -> ^(POINTER NCNAME)?
	|	pointerpart  (S? pointerpart)* -> ^(POINTER pointerpart*)?
	;
	catch[RecognitionException e] {
        emitErrorMessage("Error: invalid xpointer expression '" + input + "'");
        recover(input,e);
    }

pointerpart
	:	pointerpart_element
	|   pointerpart_xpath
	|   pointerpart_xpointer
	|   pointerpart_xmlns
	|   pointerpart_otherscheme
	;
    catch[RecognitionException e] {
        emitErrorMessage("Error: invalid pointerpart expression '" + input + "'");
        recover(input,e);
    }

pointerpart_element
    :   ELEMENT LBRACE elementschemedata RBRACE     -> ^(ELEMENTSCHEME elementschemedata?)?
    ;
    catch[RecognitionException e] {
        emitErrorMessage("Error: invalid element expression '" + input + "'");
        consumeUntil(input, RBRACE);
    }

pointerpart_xpath
    :   XPATH LBRACE xpathschemedata RBRACE         -> ^(XPATHSCHEME xpathschemedata)
    ;

pointerpart_xpointer
    :   XPOINTER LBRACE xpathschemedata RBRACE      -> ^(XPOINTERSCHEME xpathschemedata)
    ;
    catch[XPointerSchemeException e] {
        consumeUntil(input, RBRACE);
    }

pointerpart_xmlns
    :   XMLNS LBRACE xmlnsschemedata RBRACE         -> ^(XMLNSSCHEME xmlnsschemedata)
    ;

pointerpart_otherscheme
    :   qname LBRACE schemedata RBRACE              -> ^(OTHERSCHEME qname schemedata)
    ;
    catch[OtherSchemeException e] {
        consumeUntil(input, RBRACE);
        input.consume();
    }

elementschemedata
	:	NCNAME       -> ^(ELEMENT NCNAME)
	|   NCNAME CHILDSEQUENCE -> ^(ELEMENT NCNAME) ^(CHILDSEQUENCE CHILDSEQUENCE)
	| 	CHILDSEQUENCE	-> ^(CHILDSEQUENCE CHILDSEQUENCE)
	|   escapeddata*
        {
            emitErrorMessage("Error: bad element scheme data '" + $text + "'");
        } ->
	;
	catch[RecognitionException e] {
        emitErrorMessage("Error: invalid element scheme data expression '" + getTokenErrorDisplay(e.token) + "'");
        consumeUntil(input, RBRACE);
    }

schemedata
	:	escapeddata*
	;

xmlnsschemedata
	:	NCNAME S? EQUAL S? escapednamespacename	-> ^(PREFIX NCNAME) ^(NAMESPACE escapednamespacename)
	;

escapednamespacename
	:	escapeddata*
	;

xpathschemedata
	:	xpathescapeddata*
	;
    catch[XPointerSchemeException e] {
        throw e;
    }

escapeddata
	:	ESCCIRC
	|   ESCLBRACE
	|   ESCRBRACE
	|	LBRACE schemedata RBRACE
	|   ~(LBRACE|RBRACE|CIRC)
	;

xpathescapeddata
    :	ESCCIRC
    |   ESCLBRACE
    |   ESCRBRACE
    |	LBRACE xpathschemedata RBRACE
    |   function
    |   ~(LBRACE|RBRACE|CIRC)
    ;
    catch[XPointerSchemeException e] {
        throw e;
    }

qname
    :	(NCNAME COLON)? NCNAME	-> ^(QNAME NCNAME+)
    |   NCNAME COLON (ELEMENT|XPATH|XPOINTER|XMLNS)
        {
            emitErrorMessage("Error: bad scheme '" + $text + "'");
            throw new OtherSchemeException(input);
        }
	;
	catch[OtherSchemeException e] {
	    throw e;
	}

function
@after {
    emitErrorMessage("Error: xpointer function '" + $f.text + "' is not supported");
    throw new XPointerSchemeException(input);
}
	:	f=STRINGRANGE
	|   f=RANGETO
    |   f=POINT
    |   f=RANGE
    |   f=COVERINGRANG
    |   f=RANGEINSIDE
    |   f=STARTPOINT
    |   f=HERE
    |   f=ORIGIN
	;
    catch[XPointerSchemeException e] {
        throw e;
    }