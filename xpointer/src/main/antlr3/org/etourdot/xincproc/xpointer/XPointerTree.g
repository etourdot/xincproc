tree grammar XPointerTree;

options {
    ASTLabelType=   CommonTree;
    tokenVocab  =   XPointerParser;
    superClass  = AbstractXPointerTree;
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
	:	^(ELEMENTSCHEME d1=elementschemedata)
	    {
	        $schemebased::pointerParts.add(factory.createElementScheme($d1.name, $d1.data));
	    }
	|	^(XPATHSCHEME d2=schemedata)
	    {
            StringBuilder builder = new StringBuilder();
            for (final String aData : $d2.datas)
            {
                builder.append(aData);
            }
	        $schemebased::pointerParts.add(factory.createXPathScheme(builder.toString()));
	    }
	| 	^(XPOINTERSCHEME d3=schemedata)
        {
            StringBuilder builder = new StringBuilder();
            for (final String aData : $d3.datas)
            {
                builder.append(aData);
            }
            $schemebased::pointerParts.add(factory.createXPointerScheme(builder.toString()));
        }
	|	^(XMLNSSCHEME d4=xmlnsschemedata)
	    {
	        XmlNsScheme xmlnsScheme = factory.createXmlNsScheme($d4.prefix, $d4.namespace);
	        if (xmlnsScheme != null)
	        {
	            $schemebased::pointerParts.add(xmlnsScheme);
            }
	    }
	|	^(OTHERSCHEME qname schemedata)
	;

elementschemedata returns [String name, String data]
	:	^(DATAS ^(ELEMENT n=NCNAME))
	    {
	        $name = $n.text;
	        $data = "";
	    }
	|   ^(DATAS ^(ELEMENT n=NCNAME) ^(CHILDSEQUENCE c1=childsequence))
	    {
	        $name = $n.text;
	        $data = ($c1.text!=null)?$c1.text:"";
	    }
	| 	^(DATAS ^(CHILDSEQUENCE c2=childsequence))
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
schemedata returns[List<String> datas]
@init { $datas = new ArrayList<String>(); }
	:	^(DATAS (e1=escapeddatas { $datas.add($e1.text);})*)
	;
xmlnsschemedata returns [String prefix, String namespace]
	:	^(DATAS ^(PREFIX n=NCNAME) ^(NAMESPACE e=escapeddatas))
	    {
	        $prefix = $n.text;
	        $namespace = $e.text;
	    }
	;
escapeddatas
	:	~(LBRACE|RBRACE|CIRC|S)
	|	schemedata
	;
qname
    :	^(QNAME NCNAME+)
	;

function
	:	^(FUNCTION STRINGRANGE)
	;