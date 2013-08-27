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

package org.etourdot.xincproc.fragid.model;

import com.google.common.base.Optional;
import com.google.common.collect.Range;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 31/05/13
 * Time: 23:24
 */
public class FragId
{
    private Optional<Range> charRange;
    private Optional<Range> lineRange;
    private Optional<Integer> length;
    private Optional<String> md5;
    private Optional<String> charset;

    public FragId()
    {
        this.charRange = Optional.absent();
        this.lineRange = Optional.absent();
        this.length = Optional.absent();
        this.md5 = Optional.absent();
        this.charset = Optional.absent();
    }

    public boolean isCharRangePresent()
    {
        return this.charRange.isPresent();
    }

    public Range getCharRange()
    {
        return this.charRange.get();
    }

    public void setCharRange(final Range range)
    {
        this.charRange = Optional.of(range);
    }

    public boolean isLineRangePresent()
    {
        return this.lineRange.isPresent();
    }

    public Range getLineRange()
    {
        return this.lineRange.get();
    }

    public void setLineRange(final Range range)
    {
        this.lineRange = Optional.of(range);
    }

    public boolean isLengthPresent()
    {
        return this.length.isPresent();
    }

    public int getLength()
    {
        return this.length.get();
    }

    public void setLength(final Integer length)
    {
        this.length = Optional.of(length);
    }

    public boolean isMd5Present()
    {
        return this.md5.isPresent();
    }

    public String getMd5()
    {
        return this.md5.get();
    }

    public void setMd5(final String md5)
    {
        this.md5 = Optional.of(md5);
    }

    public boolean isCharsetPresent()
    {
        return this.charset.isPresent();
    }

    public String getCharset()
    {
        return this.charset.get();
    }

    public void setCharset(final String charset)
    {
        this.charset = Optional.of(charset);
    }
}
