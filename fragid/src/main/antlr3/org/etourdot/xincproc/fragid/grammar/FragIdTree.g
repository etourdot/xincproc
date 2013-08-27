tree grammar FragIdTree;

options {
    ASTLabelType=   CommonTree;
    tokenVocab  =   FragIdParser;
    superClass  =   AbstractFragIdTree;
    backtrack   =   true;
    memoize     =   true;
}

@header {
    package org.etourdot.xincproc.fragid.grammar;

    import org.etourdot.xincproc.fragid.model.*;
    import org.etourdot.xincproc.fragid.exceptions.*;
    import com.google.common.collect.Range;
}

// Parser rules
textfragment returns [FragId fid]
scope {
    FragId fragid;
}
@init {
    $textfragment::fragid = new FragId();
}
    : textscheme (SEMICOLON integritycheck)?
        {
            $fid = $textfragment::fragid;
        }
    ;

textscheme
    : charscheme
    | linescheme
    ;

charscheme
    : ^(CHAR r=range)
        {
            $textfragment::fragid.setCharRange($r.range);
        }
    ;

linescheme
    : ^(LINE r=range)
        {
            $textfragment::fragid.setLineRange($r.range);
        }
    ;

range returns [Range range]
    : ^(RANGE ^(STARTRANGE n1=NUMBER) ^(ENDRANGE n2=NUMBER))
        {
            if ($n2.text==null)
            {
                $range = Range.atLeast(new Integer($n1.text));
            }
            else
            {
                $range = Range.closedOpen(new Integer($n1.text),new Integer($n2.text));
            }
        }
    | ^(RANGE ^(ENDRANGE n3=NUMBER))
        {
            $range = Range.atMost(new Integer($n3.text));
        }
    | ^(RANGE ^(POSITION n4=NUMBER))
        {
            $range = Range.closed(new Integer($n4.text),new Integer($n4.text));
        }
    | ^(RANGE ^(STARTRANGE n5=NUMBER))
        {
            $range = Range.atLeast(new Integer($n5.text));
        }
    | RANGE
        {
            $range = Range.all();
        }
    ;

integritycheck
    : ^(lengthscheme mimecharset?)
    | ^(md5scheme mimecharset?)
    ;

lengthscheme
    : ^(LENGTH n=NUMBER)
        {
            $textfragment::fragid.setLength(new Integer($n.text));
        }
    ;

md5scheme
    : ^(MD5 v=MD5HEX)
        {
            $textfragment::fragid.setMd5($v.text);
        }
    ;

mimecharset
    : c=CHARSET
        {
            $textfragment::fragid.setCharset($c.text);
        }
    ;

