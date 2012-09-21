/*
 * Copyright (C) 2011 Emmanuel Tourdot
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * $Id$
 */
package org.etourdot.xinclude.xpointer;

import com.google.common.collect.ImmutableList;
import net.sf.saxon.s9api.Axis;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmSequenceIterator;
import net.sf.saxon.s9api.XdmValue;
import org.xml.sax.helpers.NamespaceSupport;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import java.io.Reader;
import java.io.Writer;

/**
 * @author Emmanuel Tourdot
 */
public final class XPointer implements PointerHandler
{
    private final ImmutableList.Builder<PointerHandler> builder = new ImmutableList.Builder<PointerHandler>();
    private XPointerContext xpointerContext;
    public static final String XINCPROC_NAMESPACE_URI = "http://org.etourdot.com/XIncProc";
    public static final String XINCPROC_PREFIX = "xpc";
    private static final QName XINCPROC_ROOT_QNAME = new QName(XINCPROC_NAMESPACE_URI, "root", XINCPROC_PREFIX);
    private static final QName XINCPROC_TOP_QNAME = new QName(XINCPROC_NAMESPACE_URI, "top", XINCPROC_PREFIX);

    public static boolean isRootElement(final QName element)
    {
        assert element != null;
        return XINCPROC_ROOT_QNAME.equals(element);
    }

    public static boolean isTopElement(final QName element)
    {
        assert element != null;
        return XINCPROC_TOP_QNAME.equals(element);
    }

    public static Iterable<XdmNode> getTopElements(final XdmNode node)
    {
        assert node != null;
        ImmutableList.Builder<XdmNode> listElementBuilder = new ImmutableList.Builder<XdmNode>();
        final XdmSequenceIterator iterator = getChildrenIterator(node);
        while (iterator.hasNext())
        {
            listElementBuilder.add((XdmNode) iterator.next());
        }
        return listElementBuilder.build();
    }

    public static int getNumberOfChildren(final XdmNode node)
    {
        final XdmSequenceIterator iterator = getChildrenIterator(node);
        int countChildren = 0;
        while (iterator.hasNext())
        {
            countChildren++;
            iterator.next();
        }
        return countChildren;
    }

    public static XdmSequenceIterator getChildrenIterator(final XdmValue value)
    {
        return ((XdmNode) value).axisIterator(Axis.CHILD);
    }

    public void setContext(final XPointerContext xpointerContext)
    {
        this.xpointerContext = xpointerContext;
    }

    public XPointerContext getContext() {
        return xpointerContext;
    }

    @Override
    public void parse(final Reader reader, final Writer writer) throws XPointerException 
    {
        for (final PointerHandler pointerHandler : builder.build())
        {
            pointerHandler.parse(reader, writer);
        }
    }

    @Override
    public void parse(final String systemId, final Writer writer) throws XPointerException
    {
        for (final PointerHandler pointerHandler : builder.build())
        {
            pointerHandler.parse(systemId, writer);
        }
    }

    @Override
    public XdmValue getXdmValue(final Source source) throws XPointerException {
        XdmValue value = null;
        for (final PointerHandler pointerHandler : builder.build())
        {
            final XdmValue newVal = pointerHandler.getXdmValue(source);
            if (value != null)
            {
                value.append(newVal);
            }
            else
            {
                value = newVal;
            }
        }
        return value;
    }

    public void addPointerPart(final PointerHandler handler)
    {
        builder.add(handler);
    }
}
