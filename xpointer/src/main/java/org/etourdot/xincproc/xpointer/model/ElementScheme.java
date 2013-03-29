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

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import org.etourdot.xincproc.xpointer.exceptions.ElementSchemeException;

import javax.xml.namespace.QName;

public class ElementScheme extends DefaultScheme {
    private static final QName ELEMENT_NAME = new QName("element");

    private static class ChildSequenceFunction implements Function<String, String> {
        @Override
        public String apply(final String input)
        {
            return "/*[" + input + "]";
        }
    }

    private static final Function<String, String> CHILDSEQ_FUNCTION = new ElementScheme.ChildSequenceFunction();
    private final String name;
    private final String childSequence;

    public ElementScheme(final String name, final String childSequence)
            throws ElementSchemeException
    {
        super(ElementScheme.ELEMENT_NAME);
        if (Strings.isNullOrEmpty(name) && Strings.isNullOrEmpty(childSequence))
        {
            throw new ElementSchemeException();
        }
        this.name = name;
        this.childSequence = childSequence;
        this.expression = initExpression(name, childSequence);
    }

    public String getChildSequence()
    {
        return this.childSequence;
    }

    public String getName()
    {
        return this.name;
    }

    private static String initExpression(final String name, final String childSequence)
    {
        final StringBuilder findExpr = new StringBuilder();
        if (!Strings.isNullOrEmpty(name))
        {
            findExpr.append(DefaultScheme.ID_SEARCH_EXPR.replaceAll("#ID#", name));
        }
        if (!Strings.isNullOrEmpty(childSequence))
        {
            findExpr.append(Joiner.on("").join(Iterables.transform(Splitter.on('/').omitEmptyStrings()
                    .split(childSequence), ElementScheme.CHILDSEQ_FUNCTION)));
        }
        return findExpr.toString();
    }

    @Override
    public String toString()
    {
        return ElementScheme.ELEMENT_NAME + "(" + this.name + ',' + this.childSequence + ')';
    }
}
