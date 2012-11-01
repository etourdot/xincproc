grammar XPointer;

options {
	language	=	Java;
	output		=	AST;
	ASTLabelType	=	CommonTree;
}
tokens {
	XPOINTER;
	XPATH;
	ELEMENT;
	XMLNS;
}
@header {
	package org.etourdot.xinclude.xpointer;
	
	import javax.xml.namespace.QName;
	import org.etourdot.xinclude.XIncProcConfiguration;
}
@lexer::header {
	package org.etourdot.xinclude.xpointer;
	import javax.xml.namespace.QName;
}
@members {
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
}

// Lexer Rules
ESCCIRC 	: 	'^^';
ESCLBRACE	: 	'^(';
ESCRBRACE 	: 	'^)';
COLON        	:	':';
EQUAL		: 	'=';
CIRC		:	'^';
LBRACE		:	'(';
RBRACE		: 	')';
XPOINTER	:	'xpointer';
XPATH		:	'xpath';
ELEMENT		:	'element';
XMLNS		:	'xmlns';

fragment NCNAMESTARTCHAR
		:	'A'..'Z'             | '_'                |
			'a'..'z'             | '\u00C0'..'\u00D6' | '\u00D8'..'\u00F6' |
			'\u00F8'..'\u02FF'   | '\u0370'..'\u037D' | '\u037F'..'\u1FFF' |
			'\u200C'..'\u200D'   | '\u2070'..'\u218F' | '\u2C00'..'\u2FEF' |
			'\u3001'..'\uD7FF'   | '\uF900'..'\uFDCF' | '\uFDF0'..'\uFFFD'
		;
fragment NCNAMECHAR
		:	NCNAMESTARTCHAR      | '-'                | '.'                | 
		        '0'..'9'             | '\u00B7'           | '\u0300'..'\u036F' |
		        '\u203F'..'\u2040'
		;
fragment NORMALCHAR	
		:	NCNAMECHAR           | '\u0020'..'\u0027' | '\u002A'..'\u002F' |
		        '\u003B'..'\u003C'   | '\u003E'..'\u0040' | '\u005B'..'\u005D' 
		;	

S		:	('\u0009' | '\u000A' | '\u000D' | '\u0020')+ { $channel = HIDDEN; };
CHILDSEQUENCE :	('/' '1'..'9' '0'..'9'*)+;
NCNAME		: 	NCNAMESTARTCHAR NCNAMECHAR*;
FNNAME		:	(NORMALCHAR | ESCLBRACE | ESCRBRACE | ESCCIRC | COLON)*;

// Parser rules
pointer    	:  	shorthand | schemebased;

shorthand	:	ncname
		{
			xpointer.addPointerPart(getConfiguration().getXPointerEngine().createPointerPart($ncname.text, getContext()));
		}
		;
schemebased	:	pointerpart (S? pointerpart)*;
pointerpart	:	ELEMENT LBRACE! elementexpr RBRACE!
			{
			    StringBuilder builder = new StringBuilder();
			    for (final Object aData : $elementexpr.datas)
                {
                    builder.append(((CommonTree)aData).toStringTree());
                }
				xpointer.addPointerPart(getConfiguration().getXPointerEngine().createPointerPart(new QName("element"), unescape(builder.toString()), getContext()));
			}
			|
			(XPATH | XPOINTER) LBRACE! xpathexpr RBRACE!
			{
				StringBuilder builder = new StringBuilder();
				for (final Object aData : $xpathexpr.datas)
				{
					builder.append(((CommonTree)aData).toStringTree());
				}
				xpointer.addPointerPart(getConfiguration().getXPointerEngine().createPointerPart(new QName("xpath"), unescape(builder.toString()), getContext()));
			}
			|
			XMLNS LBRACE! xmlnsexpr RBRACE!
			{
				getContext().addNamespace($xmlnsexpr.qname);
			}
			|
			qname LBRACE! schemedata RBRACE!
			{
				StringBuilder builder = new StringBuilder();
				for (final Object aData : $schemedata.datas)
				{
					builder.append(((CommonTree)aData).toStringTree());
				}
				xpointer.addPointerPart(getConfiguration().getXPointerEngine().createPointerPart($qname.qname, unescape(builder.toString()), getContext()));
			}
			;
schemedata returns[List datas]
		:	(data+=escapeddata)+ {$datas = $data;};
qname returns[QName qname]
		: 	p=ncname (COLON! l=ncname)?
			{
				if ($l.text != null) { $qname = new QName("",$p.text,$l.text); }
				else { $qname = new QName($p.text);}
			}
		;
ncname  	: 	NCNAME;

elementschemedata
        :  CHILDSEQUENCE;
elementexpr returns[List datas]
        :	(data+=elementschemedata)+ {$datas = $data;};

xmlnsexpr returns[QName qname]
	 	:	p=NCNAME EQUAL u=fnname
	 		{
	 			$qname = new QName($u.text, "", $p.text);
	 		}
	 	;
	 	
fnname		:	FNNAME | LBRACE fnname? RBRACE;
escapeddata 	:	ncname | LBRACE schemedata RBRACE;

xpathexpr returns[List datas]
		:	(data+=fnname)+ {$datas = $data;};
