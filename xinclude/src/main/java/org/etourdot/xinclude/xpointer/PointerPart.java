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

import com.google.common.base.Strings;
import net.sf.saxon.s9api.*;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;


/**
 * @author Emmanuel Tourdot
 */
public final class PointerPart implements PointerHandler
{
    private final static String XPOINTER = "xpointer";
    private final static String ELEMENT = "element";
    private final static String XPATH = "xpath";
    private final QName schemeName;
    private final String schemeData;
    private final String localName;
    private XPointerContext xpointerContext;

    public PointerPart(final String localName)
    {
        this.localName = localName;
        this.schemeName = null;
        this.schemeData = null;
    }

    public PointerPart(final QName schemeName, final String schemeData)
    {
        this.localName = null;
        this.schemeName = schemeName;
        this.schemeData = schemeData;
    }

    public void setContext(final XPointerContext xpointerContext)
    {
        this.xpointerContext = xpointerContext;
    }

    @Override
    public void parse(final Reader reader, final Writer writer) throws XPointerException
    {
        try
        {
            final XQueryEvaluator evaluator = getEvaluator();
            if (evaluator != null)
            {
                evaluator.setSource(new StreamSource(reader));
                evaluator.run(new Serializer(writer));
            }
        }
        catch (final SaxonApiException e)
        {
            throw new XPointerException(e);
        }
    }

    @Override
    public void parse(final String systemId, final Writer writer) throws XPointerException
    {
        try
        {
            final XQueryEvaluator evaluator = getEvaluator();
            if (evaluator != null)
            {
                evaluator.setSource(new StreamSource(systemId));
                evaluator.run(new Serializer(writer));
            }
        }
        catch (final SaxonApiException e)
        {
            throw new XPointerException(e);
        }
    }

    @Override
    public XdmValue getXdmValue(final Source source) throws XPointerException
    {
        try
        {
            final XQueryEvaluator evaluator = getEvaluator();
            if (evaluator != null)
            {
                evaluator.setSource(source);
                return evaluator.evaluate();
            }
        }
        catch (final SaxonApiException e)
        {
            throw new XPointerException(e);
        }
        return null;
    }

    private XQueryEvaluator getEvaluator() throws SaxonApiException, XPointerException
    {
        final String query = "element xpc:root {" +
                             "  for $x in %s" +
                             "  return element xpc:top {" +
                             "         if (not(fn:empty($x/ancestor::*/@xml:lang))) then attribute xml:lang {($x/ancestor::*/@xml:lang)[last()]} else ()," +
                             "         typeswitch($x) " +
                             "         case element() return " +
                             "                 element {fn:node-name($x)} " +
                             "                 {$x/@*, " +
                             "                  if (fn:empty($x/ancestor::*/@xml:lang)) then ()" +
                             "                  else if (($x/ancestor::*/@xml:lang)[last()] != '') then attribute xml:lang {($x/ancestor::*/@xml:lang)[last()]}" +
                             "                  else (), " +
                             "                  $x/node()}" +
                             "         case text() return $x" +
                             "         default return $x" +
                             "         }" +
                             " }";

        final XQueryCompiler compiler = getXQueryCompiler();

        final StringBuilder builder = new StringBuilder();
        if (Strings.isNullOrEmpty(localName))
        {
            builder.append(getFormattedQuery(query));
        }
        else
        {
            final String idExpr = "//*[@id='#ID#']|fn:id('#ID#')";
            builder.append(String.format(query, idExpr.replaceAll("#ID#", localName)));
        }
        final XQueryExecutable executable = compiler.compile(builder.toString());
        return executable.load();
    }

    private String getFormattedQuery(final String query) throws XPointerException
    {
        final String localPartName = schemeName.getLocalPart();
        if (XPOINTER.equalsIgnoreCase(localPartName) ||
            XPATH.equalsIgnoreCase(localPartName))
        {
            return String.format(query, schemeData);
        }
        if (ELEMENT.equalsIgnoreCase(localPartName) && schemeData!=null)
        {
            final String idExpr = "//*[@id='#ID#']|fn:id('#ID#')";
            return String.format(query, idExpr.replaceAll("#ID#", schemeData));
        }
        throw new XPointerException("Unknown Xpointer scheme");
    }

    private XQueryCompiler getXQueryCompiler()
    {
        final Processor processor = xpointerContext.getConfiguration().getProcessor();
        final XQueryCompiler compiler = processor.newXQueryCompiler();
        compiler.declareNamespace("xsd", "http://www.w3.org/2001/XMLSchema");
        compiler.declareNamespace("xml", "http://www.w3.org/XML/1998/namespace");
        compiler.declareNamespace(XPointer.XINCPROC_PREFIX, XPointer.XINCPROC_NAMESPACE_URI);
        final Map<String, String> namespaces = xpointerContext.getNamespaces();
        for (final Map.Entry entry : namespaces.entrySet())
        {
            compiler.declareNamespace((String) entry.getKey(), (String) entry.getValue());
        }
        return compiler;
    }
}
