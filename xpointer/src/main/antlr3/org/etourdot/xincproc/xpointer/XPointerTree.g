tree grammar XPointerTree;

options {
    ASTLabelType=   CommonTree;
    tokenVocab  =   XPointerParser;
    superClass  =   AbstractXPointerTree;
    backtrack   =   true;
    memoize     =   true;
}

@header {
    package org.etourdot.xincproc.xpointer;

    import org.etourdot.xincproc.xpointer.model.*;
    import org.etourdot.xincproc.xpointer.exceptions.*;
}

pointer returns [Pointer xpointer]
    :	^(POINTER s=shorthand)
        {
            $xpointer = new Pointer($s.shortHand);
        }
	|	^(POINTER sch=schemebased)
	    {
	        $xpointer =  new Pointer($sch.parts);
	    }
	|   POINTER
	;

shorthand returns [ShortHand shortHand]
	:	n=NCNAME
	    {
	        $shortHand = factory.createShortHand($n.text);
	    }
	;

schemebased returns [List parts]
scope {
    List pointerParts;
}
@init {
    $schemebased::pointerParts = new ArrayList();
}
	:	pointerpart+
	    {
	        $parts = $schemebased::pointerParts;
	    }
	;

pointerpart
	:	pointerpart_element
	|   pointerpart_xpath
	|	pointerpart_xpointer
	| 	pointerpart_xmlns
	|	pointerpart_otherscheme
	;

pointerpart_element
    :   ^(ELEMENTSCHEME d1=elementschemedata?)
        {
            ElementScheme elementScheme = factory.createElementScheme($d1.name, $d1.data);
            if (elementScheme != null) {
                $schemebased::pointerParts.add(elementScheme);
             }
        }
    ;

pointerpart_xpath
    :   ^(XPATHSCHEME d2=xpathschemedata)
        {
            $schemebased::pointerParts.add(factory.createXPathScheme($d2.xpathdatas));
        }
    ;

pointerpart_xpointer
    :   ^(XPOINTERSCHEME d3=xpathschemedata)
        {
            $schemebased::pointerParts.add(factory.createXPointerScheme($d3.xpathdatas));
        }
    ;

pointerpart_xmlns
    :   ^(XMLNSSCHEME d4=xmlnsschemedata)
        {
            XmlNsScheme xmlnsScheme = factory.createXmlNsScheme($d4.prefix, $d4.namespace);
            if (xmlnsScheme != null)
            {
                $schemebased::pointerParts.add(xmlnsScheme);
            }
        }
    ;

pointerpart_otherscheme
    :   ^(OTHERSCHEME q=qname s=schemedata)
        {
             emitErrorMessage("Warning: '" + $q.text + "' scheme is not supported");
        }
    ;

elementschemedata returns [String name, String data]
	:	^(ELEMENT n=NCNAME)
	    {
	        $name = $n.text;
	        $data = "";
	    }
	|   ^(ELEMENT n=NCNAME) ^(CHILDSEQUENCE c1=childsequence)
	    {
	        $name = $n.text;
	        $data = ($c1.text!=null)?$c1.text:"";
	    }
	| 	^(CHILDSEQUENCE c2=childsequence)
	    {
	        $name = "";
	        $data = ($c2.text!=null)?$c2.text:"";
	    }
	;
	catch[RecognitionException e] {
        reportElementSchemeDataError(e);
        recover(input,e);
	}

childsequence
	:	CHILDSEQUENCE
	;

schemedata returns [String datas]
@init { StringBuilder builder = new StringBuilder(); }
@after { $datas = builder.toString(); }
	:	(e1=escapeddatas { builder.append($e1.text);})*
	;

xmlnsschemedata returns [String prefix, String namespace]
@init { StringBuilder builder = new StringBuilder(); }
@after { $namespace = builder.toString(); }
	:	^(PREFIX n=NCNAME) { $prefix = $n.text; } ^(NAMESPACE (e=escapeddatas { builder.append($e.text); })*)

	;

xpathschemedata returns [String xpathdatas]
@init { StringBuilder builder = new StringBuilder(); }
@after { $xpathdatas = builder.toString(); }
	:	(d=xpathescapeddata { builder.append($d.text); })*
	;

xpathescapeddata
    :	ESCCIRC
    |   ESCLBRACE
    |   ESCRBRACE
    |   ~(CIRC)
    ;

escapeddatas
    :	~(LBRACE|RBRACE|CIRC|S)
    ;

qname
    :	^(QNAME NCNAME?)
	;
