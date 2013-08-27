package org.etourdot.xincproc.fragid;
/*
 * This file is part of the XIncProc framework.
 * Copyright (C) 2011 - 2013 Emmanuel Tourdot
 *
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this software.
 * If not, see <http://www.gnu.org/licenses/>.
 */

import static org.junit.Assert.*;

import com.google.common.io.Resources;
import org.etourdot.xincproc.fragid.exceptions.FragIdException;
import org.etourdot.xincproc.fragid.model.FragId;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 28/05/13
 * Time: 23:45
 */
public class FragIdEngineTest {
    @Test
    public void testGetFragId() throws Exception
    {
        final FragIdEngine fragIdEngine = new FragIdEngine();
        final FragId fragId = fragIdEngine.getFragId("line=10,20;md5=38dc89f294b24691d3f0d893ed3c119c");
        assertTrue(fragId.isLineRangePresent());
        assertFalse(fragId.isCharRangePresent());
        assertFalse(fragId.isCharsetPresent());
        assertFalse(fragId.isLengthPresent());
        assertTrue(fragId.isMd5Present());
    }

    @Test
    public void testExecuteLineRange() throws Exception
    {
        final FragIdEngine fragIdEngine = new FragIdEngine();
        final URI fileUri = getClass().getResource("code.pl").toURI();
        String result = fragIdEngine.execute("line=2,6", fileUri);
        assertEquals("use strict;\n" +
                "use English;\n" +
                "use Getopt::Std;\n" +
                "use vars qw($opt_p $opt_q $opt_u $opt_m);", result);
        result = fragIdEngine.execute("line=3", fileUri);
        assertEquals("use English;", result);
        result = fragIdEngine.execute("line=40,", fileUri);
        assertEquals("    $file = shift @ARGV;\n" +
                "}", result);
        result = fragIdEngine.execute("line=,2", fileUri);
        assertEquals("#!/usr/bin/perl -- # --*-Perl-*--\n" +
                "\n" +
                "use strict;", result);
        result = fragIdEngine.execute("line=,", fileUri);
        assertEquals(Resources.toString(fileUri.toURL(), Charset.defaultCharset()).replace("\r\n","\n"), result);
    }

    @Test
    public void testExecuteCharRange() throws Exception
    {
        final FragIdEngine fragIdEngine = new FragIdEngine();
        final URI fileUri = getClass().getResource("code.pl").toURI();
        String result = fragIdEngine.execute("char=100,200", fileUri);
        assertEquals("_q $opt_u $opt_m);\n" +
                "\n" +
                "my $usage = \"Usage: $0 [-q] [-u|-p|-m] file [ file ... ]\\n\";\n" +
                "\n" +
                "die $usage if ! ge", result);
        result = fragIdEngine.execute("char=3", fileUri);
        assertEquals("u",result);
        result = fragIdEngine.execute("char=766,", fileUri);
        assertEquals("GV;\n" +
                "}",result);
        result = fragIdEngine.execute("char=,5", fileUri);
        assertEquals("#!/us",result);
        result = fragIdEngine.execute("char=,", fileUri);
        assertEquals(Resources.toString(fileUri.toURL(), Charset.defaultCharset()).replace("\r\n","\n"), result);
    }

    @Test
    public void testCheckLenIntegrity() throws Exception
    {
        final FragIdEngine fragIdEngine = new FragIdEngine();
        final URI fileUri = getClass().getResource("code.pl").toURI();
        try
        {
            String result = fragIdEngine.execute("char=100,200;length=100", fileUri);
            fail();
        }
        catch (final FragIdException e)
        {
        }
        String result = fragIdEngine.execute("char=100,200;length=812", fileUri);
    }

    @Test
    public void testCheckMd5Integrity() throws Exception
    {
        final FragIdEngine fragIdEngine = new FragIdEngine();
        final URI fileUri = getClass().getResource("code.pl").toURI();
        try
        {
            String result = fragIdEngine.execute("char=100,200;md5=b24c075e3fe86a115621bd0e81c5b27e", fileUri);
            fail();
        }
        catch (final FragIdException e)
        {
        }
        String result = fragIdEngine.execute("char=100,200;md5=b24c075e3fe86a115621bd0e81c5a27e", fileUri);
    }
}
