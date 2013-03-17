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

package org.etourdot.xincproc.xpointer.model;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 21/09/12
 * Time: 23:11
 */
public class Pointer {
    private ShortHand shortHand;
    private ImmutableList<PointerPart> schemeBased;

    public Pointer() {
        this.shortHand = null;
        this.schemeBased = new ImmutableList.Builder<PointerPart>().build();
    }

    public Pointer(final ShortHand shortHand) {
        this.shortHand = shortHand;
        this.schemeBased = new ImmutableList.Builder<PointerPart>().build();
    }

    public Pointer(final List<PointerPart> schemeBased) {
        this.shortHand = null;
        final ImmutableList.Builder<PointerPart> builder = new ImmutableList.Builder<PointerPart>();
        if (schemeBased != null) {
            builder.addAll(schemeBased);
        }
        this.schemeBased = builder.build();
    }

    public void addPointerPart(final PointerPart pointerPart) {
        this.schemeBased = new ImmutableList.Builder<PointerPart>().addAll(schemeBased).add(pointerPart).build();
    }

    public boolean isShortHand() {
        return shortHand != null;
    }

    public ShortHand getShortHand() {
        return shortHand;
    }

    public boolean isSchemeBased() {
        return !schemeBased.isEmpty();
    }

    public ImmutableList<PointerPart> getSchemeBased() {
        return schemeBased;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (isShortHand()) {
            builder.append(getShortHand().toString());
        } else {
            for (final PointerPart part : getSchemeBased()) {
                builder.append(part.toString());
            }
        }
        return builder.toString();
    }
}
