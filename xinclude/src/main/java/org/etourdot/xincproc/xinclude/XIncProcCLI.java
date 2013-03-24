/*
 * This file is part of the XIncProc framework.
 * Copyright (C) 2010 - 2013 Emmanuel Tourdot
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

package org.etourdot.xincproc.xinclude;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author Emmanuel Tourdot
 */
public class XIncProcCLI {
    private static final Logger LOG = LoggerFactory.getLogger(XIncProcCLI.class);
    private final Options options;

    private XIncProcCLI()
    {
        this.options = new Options();
        this.options.addOption("h", false, "Help");
        final Option output = OptionBuilder.hasArg().withArgName("file").withDescription("use output file").create("o");
        this.options.addOption(output);
    }

    private int execute(final String[] args, final InputStream stdin, final PrintStream stdout,
                        final PrintStream stderr)
    {
        final GnuParser parser = new GnuParser();
        try
        {
            final CommandLine commandLine = parser.parse(this.options, args);
        }
        catch (final ParseException ignored)
        {
            help(stderr);
            return 1;
        }
        return 0;
    }

    private void help(final PrintStream destination)
    {
        final HelpFormatter helpFormatter = new HelpFormatter();
        final PrintWriter printWriter = new PrintWriter(destination);
        try
        {
            helpFormatter.printHelp(printWriter, helpFormatter.getWidth(), "java -jar xincproc.jar", null, this.options,
                    helpFormatter.getLeftPadding(), helpFormatter.getDescPadding(), null, true);
        }
        finally
        {
            printWriter.flush();
            printWriter.close();
        }
    }

    public static void main(final String... args)
    {
        System.exit(new XIncProcCLI().execute(args, System.in, System.out, System.err));
    }
}
