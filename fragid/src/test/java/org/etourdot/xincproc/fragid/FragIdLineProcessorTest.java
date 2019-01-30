package org.etourdot.xincproc.fragid;

import com.google.common.io.CharStreams;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class FragIdLineProcessorTest
{

    @Test
    public void testWithClosedLineLimits() throws Exception
    {
        FragId fragId = new FragId(FRAG_TYPE.LINE, 2, 6, new ArrayList<>());
        FragIdLineProcessor lineProcessor = new FragIdLineProcessor(fragId);
        BufferedReader reader = new BufferedReader(new FileReader(new File(getClass().getClassLoader().getResource("code.pl").getFile())));
        String result = CharStreams.readLines(reader, lineProcessor);
        assertThat(result, equalTo("use strict;\n" +
                "use English;\n" +
                "use Getopt::Std;\n" +
                "use vars qw($opt_p $opt_q $opt_u $opt_m);\n"));
    }

    @Test
    public void testWithClosedCharLimits() throws Exception
    {
        FragId fragId = new FragId(FRAG_TYPE.CHAR, 100, 200, new ArrayList<>());
        FragIdLineProcessor lineProcessor = new FragIdLineProcessor(fragId);
        BufferedReader reader = new BufferedReader(new FileReader(new File(getClass().getClassLoader().getResource("code.pl").getFile())));
        String result = CharStreams.readLines(reader, lineProcessor);
        assertThat(result, equalTo("_q $opt_u $opt_m);\n" +
                "\n" +
                "my $usage = \"Usage: $0 [-q] [-u|-p|-m] file [ file ... ]\\n\";\n" +
                "\n" +
                "die $usage if ! ge"));
    }

    @Test
    public void testWithOpenRightLineLimits() throws Exception
    {
        FragId fragId = new FragId(FRAG_TYPE.LINE, 38, -1, new ArrayList<>());
        FragIdLineProcessor lineProcessor = new FragIdLineProcessor(fragId);
        BufferedReader reader = new BufferedReader(new FileReader(new File(getClass().getClassLoader().getResource("code.pl").getFile())));
        String result = CharStreams.readLines(reader, lineProcessor);
        assertThat(result, equalTo("    close (F);\n" +
                "\n" +
                "    $file = shift @ARGV;\n" +
                "}\n"));
    }

    @Test
    public void testWithOpenRightCharLimits() throws Exception
    {
        FragId fragId = new FragId(FRAG_TYPE.CHAR, 670, -1, new ArrayList<>());
        FragIdLineProcessor lineProcessor = new FragIdLineProcessor(fragId);
        BufferedReader reader = new BufferedReader(new FileReader(new File(getClass().getClassLoader().getResource("code.pl").getFile())));
        String result = CharStreams.readLines(reader, lineProcessor);
        assertThat(result, equalTo("F, \">$file\");\n" +
                "    binmode F;\n" +
                "    print F $_;\n" +
                "    close (F);\n" +
                "\n" +
                "    $file = shift @ARGV;\n" +
                "}\n"));
    }

    @Test
    public void testWithOpenLeftLineLimits() throws Exception
    {
        FragId fragId = new FragId(FRAG_TYPE.LINE, -1, 4, new ArrayList<>());
        FragIdLineProcessor lineProcessor = new FragIdLineProcessor(fragId);
        BufferedReader reader = new BufferedReader(new FileReader(new File(getClass().getClassLoader().getResource("code.pl").getFile())));
        String result = CharStreams.readLines(reader, lineProcessor);
        assertThat(result, equalTo("#!/usr/bin/perl -- # --*-Perl-*--\n" +
                "\n" +
                "use strict;\n" +
                "use English;\n"));
    }

    @Test
    public void testWithOpenLeftCharLimits() throws Exception
    {
        FragId fragId = new FragId(FRAG_TYPE.CHAR, -1, 40, new ArrayList<>());
        FragIdLineProcessor lineProcessor = new FragIdLineProcessor(fragId);
        BufferedReader reader = new BufferedReader(new FileReader(new File(getClass().getClassLoader().getResource("code.pl").getFile())));
        String result = CharStreams.readLines(reader, lineProcessor);
        assertThat(result, equalTo("#!/usr/bin/perl -- # --*-Perl-*--\n" +
                "\n" +
                "use s"));
    }
}