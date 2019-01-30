grammar XPointer;

options {
  language    =   Java;
}

@header {
import org.etourdot.xincproc.xpointer.exceptions.*;
}

// Parser rules
pointer
  :	 pointerName                        # singlePointer
  |	 pointerpart  (S? pointerpart)*     # multiPointer
  ;
  catch[RecognitionException e] {
  notifyErrorListeners("Error: invalid xpointer expression '" + _input + "'");
  }

pointerpart
  :  pointerpartElement                 # elementPointer
  |  XPATH '(' xpathschemedata ')'      # xpathPointer
  |  XPOINTER '(' xpathschemedata ')'   # xpointerPointer
  |  XMLNS '(' xmlnsschemedata ')'      # xmlnsPointer
  |  qname '(' schemedata ')'           # otherPointer
  ;
  catch[NoViableAltException e] {
  notifyErrorListeners("Error: invalid pointerpart expression '" + _input + "'");
  }

pointerpartElement
  :  ELEMENT '(' elementschemedata ')'
  ;
  catch[RecognitionException e] {
  notifyErrorListeners("Error: invalid element expression '" + _input + "'");
  }

elementschemedata
  :  NCNAME                             # eltName
  |  NCNAME CHILDSEQUENCE               # eltNameChild
  |  CHILDSEQUENCE                      # eltChild
  |  escapeddata*
  {
  notifyErrorListeners("Error: bad element scheme data '" + $text + "'");
  }                                     # eltDatas
  ;
  catch[RecognitionException e] {
  notifyErrorListeners("Error: invalid element scheme data expression '" + _input + "'");
  }

schemedata
  :  escapeddata*
  ;

xmlnsschemedata
  :  NCNAME S? '=' S? escapednamespacename
  |  escapednamespacename
  ;

escapednamespacename
  :  escapeddata*
  ;

xpathschemedata
  :  xpathescapeddata*
  ;

escapeddata
  :  '^^'
  |  '^('
  |  '^)'
  |	 '(' schemedata ')'
  |  ~('('|')'|'^')+
  ;

xpathescapeddata
  :	 '^^'
  |  '^('
  |  '^)'
  |	 '(' xpathschemedata ')'
  |  function
  |  ~('('|')'|'^')
  ;

qname
  :	 (NCNAME ':')? NCNAME                       # normQName
  |  NCNAME ':' (ELEMENT|XPATH|XPOINTER|XMLNS)  # specQName
  ;

function
@after {
    notifyErrorListeners("Error: xpointer function '" + $f.text + "' is not supported");
}
  :  f=STRINGRANGE
  |  f=RANGETO
  |  f=POINT
  |  f=RANGE
  |  f=COVERINGRANGE
  |  f=RANGEINSIDE
  |  f=STARTPOINT
  |  f=HERE
  |  f=ORIGIN
  ;

pointerName
  : NCNAME
  ;

ENTITY
  :  '&' (ALPHA | DIGIT)+ ';'
  ;

CHILDSEQUENCE
  :  ('/' '1'..'9' DIGIT*)+
  ;

XPOINTER    :  'xpointer';
XPATH	    :  'xpath';
ELEMENT	    :  'element';
XMLNS	    :  'xmlns';
STRINGRANGE :  'string-range';
RANGETO     :  'range-to';
POINT       :  'point';
RANGE       :  'range';
COVERINGRANGE: 'covering-range';
RANGEINSIDE :  'range-inside';
STARTPOINT  :  'start-point';
HERE        :  'here';
ORIGIN      :  'origin';

SPECIALCARS
  :  '"' | '\'' | '_' | '[' | ']' | '/' | ',' | '*' | '-' | '.' | '@' | '<' | '>'
  ;

S :  (' '|'\t'|'\n'|'\r')+
  ;

DIGIT :  [0-9] ;

NCNAME
  :  NCNameStartChar NCNameChar*
  ;

fragment
NCNameStartChar
  :  'A'..'Z'
  |   '_'
  |  'a'..'z'
  |  '\u00C0'..'\u00D6'
  |  '\u00D8'..'\u00F6'
  |  '\u00F8'..'\u02FF'
  |  '\u0370'..'\u037D'
  |  '\u037F'..'\u1FFF'
  |  '\u200C'..'\u200D'
  |  '\u2070'..'\u218F'
  |  '\u2C00'..'\u2FEF'
  |  '\u3001'..'\uD7FF'
  |  '\uF900'..'\uFDCF'
  |  '\uFDF0'..'\uFFFD'
  ;

fragment
NCNameChar
  :  NCNameStartChar | '-' | '.' | '0'..'9'
  |  '\u00B7' | '\u0300'..'\u036F'
  |  '\u203F'..'\u2040'
  ;

fragment ALPHA :  [a-zA-Z] ;