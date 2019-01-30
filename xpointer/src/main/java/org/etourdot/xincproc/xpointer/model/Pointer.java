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

package org.etourdot.xincproc.xpointer.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import static java.util.Optional.ofNullable;

public class Pointer
{
    private final ShortHand shortHand;
    private ImmutableList<PointerPart> schemeBased;

    public Pointer()
    {
        this((ShortHand) null);
    }

    public Pointer(final ShortHand shortHand)
    {
        this.shortHand = shortHand;
        this.schemeBased = ImmutableList.of();
    }

    public Pointer(final Iterable<PointerPart> schemeBased)
    {
        this.shortHand = null;
        final ImmutableList.Builder<PointerPart> builder = new ImmutableList.Builder<>();
        if (null != schemeBased && !Iterables.isEmpty(schemeBased))
        {
            builder.addAll(schemeBased);
        }
        this.schemeBased = builder.build();
    }

    public void addPointerPart(final PointerPart pointerPart)
    {
        this.schemeBased = new ImmutableList.Builder<PointerPart>().addAll(this.schemeBased).add(pointerPart).build();
    }

    public boolean isShortHandPresent()
    {
        return ofNullable(this.shortHand).isPresent();
    }

    public PointerPart getShortHand()
    {
        return this.shortHand;
    }

    public boolean isSchemeBased()
    {
        return !this.schemeBased.isEmpty();
    }

    public ImmutableList<PointerPart> getSchemeBased()
    {
        return new ImmutableList.Builder().addAll(this.schemeBased).build();
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        if (isShortHandPresent())
        {
            builder.append(this.shortHand.toString());
        }
        else
        {
            for (final PointerPart part : this.schemeBased)
            {
                builder.append(part.toString());
            }
        }
        return builder.toString();
    }
}
