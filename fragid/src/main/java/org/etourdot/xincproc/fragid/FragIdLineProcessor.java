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

package org.etourdot.xincproc.fragid;

import com.google.common.collect.Range;
import com.google.common.io.LineProcessor;
import org.etourdot.xincproc.fragid.model.FragId;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 20/08/13
 * Time: 15:14
 */
public class FragIdLineProcessor implements LineProcessor
{
    private StringBuilder resultBuilder;
    private FragId fragId;
    private Integer lineCount;
    private Integer position;

    public FragIdLineProcessor(FragId fragId)
    {
        this.fragId = fragId;
        this.lineCount = 0;
        this.position = 0;
        this.resultBuilder = new StringBuilder();
    }

    @Override
    public boolean processLine(java.lang.String line)
            throws IOException
    {
        final boolean returnValue;
        if (fragId.isLineRangePresent())
        {
            final Range<Integer> lineRange = fragId.getLineRange();
            if (lineRange.contains(lineCount))
            {
                resultBuilder.append(line).append("\n");
                returnValue = true;
            }
            else if (lineRange.hasLowerBound() && lineRange.lowerEndpoint().compareTo(lineCount) >= 0)
            {
                returnValue = true;
            }
            else  if (lineRange.hasUpperBound() && lineRange.upperEndpoint().compareTo(lineCount) <= 0)
            {
                returnValue = false;
            }
            else
            {
                returnValue = false;
            }
        }
        else
        {
            resultBuilder.append(line).append("\n");
            returnValue = true;
        }
        lineCount ++;
        return returnValue;
    }

    @Override
    public String getResult()
    {
        if (fragId.isLineRangePresent())
        {
            return resultBuilder.substring(0, resultBuilder.length() - 1);
        }
        else
        {
            final Range<Integer> charRange = fragId.getCharRange();
            final int start = (charRange.hasLowerBound())? (int) charRange.lowerEndpoint() : 0;
            final int end = (charRange.hasUpperBound())? (int) charRange.upperEndpoint() : resultBuilder.length() - 1;
            if (start == end)
            {
                return resultBuilder.substring(start, end + 1);
            }
            else
            {
                return resultBuilder.substring(start, end);
            }
        }
    }
}
