grammar FragId;

options {
  language	=	Java;
}

text_fragment   :  text_scheme ( ';' integrity_check )*
                ;
text_scheme     :  char_scheme
                |  line_scheme
                ;
char_scheme     :  CHAR ( position | range )
                ;
line_scheme     :  LINE ( position | range )
                ;
integrity_check :  length_scheme (',' mime_charset)?    # integrity_length
                |  md5_scheme (',' mime_charset)?       # integrity_md5
                ;
position        :  NUMBER
                ;
range           :  position ',' position    # close_rng
                |  ',' position             # left_rng
                |  position ','             # right_rng
                ;
length_scheme   :  LENGTH NUMBER
                ;
md5_scheme      :  MD5 md5_value
                ;
md5_value       :  HEX32
                ;
mime_charset    :  MIME_CHARSET_TYPE

                ;

CHAR   : 'char=';
LINE   : 'line=';
LENGTH : 'length=';
MD5    : 'md5=';
NUMBER :  DIGIT+;
HEX32  :  HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT;
MIME_CHARSET_TYPE
       : (ALPHA | DIGIT |
         '!' | '#' | '$' | '%' | '&' |
         '+' | '-' | '^' | '_' |
         '`' | '{' | '}' | '~')+
       ;

fragment ALPHA    : [A-Z];
fragment HEXDIGIT : [a-fA-F0-9];
fragment DIGIT    : [0-9];