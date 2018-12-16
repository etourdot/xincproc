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

package org.etourdot.xincproc;

import com.google.common.io.Closeables;
import org.apache.commons.cli.*;
import org.etourdot.xincproc.xinclude.XIncProcEngine;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Custom executor for CLI
 */
public final class XIncProcCLI
{
    private static final Logger LOG = LoggerFactory.getLogger(XIncProcCLI.class);
    private final Options options;

    private XIncProcCLI()
    {
        this.options = new Options();
        this.options.addOption("h", false, "Help");
        final Option input = OptionBuilder.hasArg().withArgName("file").withDescription("input file").create("if");
        final Option output = OptionBuilder.hasArg().withArgName("file").withDescription("output file").create("of");
        this.options.addOption(input).addOption(output);
    }

    private int execute(final String[] args, final InputStream stdin, final PrintStream stdout,
                        final PrintStream stderr) throws IOException
    {
        final GnuParser parser = new GnuParser();
        try
        {
            final CommandLine commandLine = parser.parse(this.options, args);
            if (!commandLine.hasOption("if"))
            {
                final HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("XIncProc", this.options);
                return -1;
            }
            final File inputFile = new File(commandLine.getOptionValue("if"));
            try (InputStream fis = new FileInputStream(inputFile))
            {
                final OutputStream outputStream;
                if (commandLine.hasOption("of"))
                {
                    final File outputFile = new File(commandLine.getOptionValue("of"));
                    outputStream = new FileOutputStream(outputFile);
                }
                else
                {
                    outputStream = stdout;
                }
                try
                {
                    XIncProcEngine.parse(fis, inputFile.toURI().toASCIIString(), outputStream);
                }
                catch (XIncludeFatalException e)
                {
                    System.err.println("XInclude Fatal error: " + e.getMessage());
                    return -1;
                }
                finally
                {
                    Closeables.close(outputStream, true);
                }
            }

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

    public static void main(final String... args) throws IOException
    {
        System.exit(new XIncProcCLI().execute(args, System.in, System.out, System.err));
    }
}
