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

import javax.xml.namespace.QName;

public class ShortHand extends AbstractDefaultScheme
{
    private static final QName SHORTHAND_NAME = new QName("shorthand");
    private final String name;

    public ShortHand(final String name)
    {
        super(ShortHand.SHORTHAND_NAME);
        this.name = name;
        this.expression = AbstractDefaultScheme.ID_SEARCH_EXPR.replaceAll("#ID#", name);
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public String toString()
    {
        return ShortHand.SHORTHAND_NAME + "(" + this.name + ')';
    }
}
