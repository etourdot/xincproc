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

package org.etourdot.xincproc.xpointer;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import net.sf.saxon.s9api.*;
import net.sf.saxon.trans.XPathException;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.etourdot.xincproc.xpointer.exceptions.XPointerException;
import org.etourdot.xincproc.xpointer.exceptions.XPointerResourceException;
import org.etourdot.xincproc.xpointer.model.*;
import org.etourdot.xincproc.xpointer.grammar.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.List;

/**
 * The XPointer engine.
 */
public class XPointerEngine
{
    /**
     * Instantiates a new XPointerEngine.
     */
    public XPointerEngine()
    {
        this(new Processor(false));
    }
    /**
     * Instantiates a new XPointerEngine
     *
     * @param processor the saxon processor to use
     */
    public XPointerEngine(final Processor processor)
    {
        this.processor = processor;
        setBaseURI(null);
        setLanguage(null);
        initEngine();
    }

    /**
     * Return an error handler which forget error messages
     *
     * @return an XPointerErrorHandler
     */
    public static XPointerErrorHandler newNilXPointerErrorHandler()
    {
        return NIL_XPOINTER_ERROR_HANDLER;
    }

    /**
     * Return a default error handler
     *
     * @return an XPointerErrorHandler
     */
    public static XPointerErrorHandler newDefaultXPointerErrorHandler()
    {
        return DEFAULT_XPOINTER_ERROR_HANDLER;
    }

    /**
     * Setting the base uri for the pointer resolution
     * The base uri is used for xml:base calculations
     *
     * @param baseURI of the parent xml
     * @return the XPointerEngine for fluent api usage
     */
    public XPointerEngine setBaseURI(final String baseURI)
    {
        if (baseURI == null)
        {
            this.baseURIValue = XdmEmptySequence.getInstance();
        }
        else
        {
            this.baseURIValue = new XdmAtomicValue(baseURI);
        }
        return this;
    }

    /**
     * Setting the language for the pointer resolution
     * The language is used for xml:lang calculation
     *
     * @param language of the parent xml
     * @return the XPointerEngine for fluent api usage
     */
    public XPointerEngine setLanguage(final String language)
    {
        if (language == null)
        {
            this.languageValue = XdmEmptySequence.getInstance();
        }
        else
        {
            this.languageValue = new XdmAtomicValue(language);
        }
        return this;
    }

    /**
     * Getting the error handler
     *
     * @return the current error handler or null if absent
     */
    public XPointerErrorHandler getXPointerErrorHandler()
    {
        return xPointerErrorHandler;
    }

    /**
     * Setting an error handler to capture parsing errors or warnings
     *
     * @param xPointerErrorHandler a XPointerErrorHandler
     * @return the XPointerEngine for fluent api usage
     */
    public XPointerEngine setXPointerErrorHandler(final XPointerErrorHandler xPointerErrorHandler)
    {
        this.xPointerErrorHandler = xPointerErrorHandler;
        return this;
    }

    /**
     * Execute xpointer expression on a xml source returning a xml string
     *
     * @param pointerStr xpointer expression
     * @param source xml source
     * @return serialized xml result or an empty string (not null)
     */
    public String execute(final String pointerStr, final Source source)
    {
        try
        {
            final StringWriter stringWriter = new StringWriter();
            final Serializer serializer = processor.newSerializer(stringWriter);
            serializer.setOutputProperty(Serializer.Property.OMIT_XML_DECLARATION, "yes");
            executeToDestination(pointerStr, source, serializer);
            return stringWriter.toString();
        }
        catch (final XPointerException e)
        {
            final String message = e.getLocalizedMessage();
            if (xPointerErrorHandler != null)
            {
                xPointerErrorHandler.reportError(message);
            }
            else
            {
                LOG.error(message, e);
            }
        }
        return "";
    }

    /**
     * Execute a xpointer expression on a xml source.
     * The result is send to a Saxon {@link net.sf.saxon.s9api.Destination}
     *
     * @param pointerStr xpointer expression
     * @param source xml source
     * @param destination Saxon destination of result stream
     * @return number of elements in result infoset excluding comments et processing instructions
     * @throws XPointerException the x pointer exception
     */
    public int executeToDestination(final String pointerStr, final Source source, final Destination destination)
            throws XPointerException
    {
        return executeToDestination(pointerStr, source, destination, null);
    }

    /**
     * Execute a xpointer expression on a xml source.
     * The result is send to a Saxon {@link net.sf.saxon.s9api.Destination}*
     * The copying attributes are copied to top-level element
     *
     * @param pointerStr xpointer expression
     * @param source xml source
     * @param destination Saxon destination of result stream
     * @param copyingAttributes attributes to copy on top-level element
     * @return number of elements in result infoset excluding comments et processing instructions
     * @throws XPointerException the x pointer exception
     */
    public int executeToDestination(final String pointerStr, final Source source, final Destination destination,
                                    final Attributes copyingAttributes)
            throws XPointerException
    {
        final Pointer pointer = getPointer(pointerStr);
        final XdmNode node = processor.newDocumentBuilder().wrap(source);
        if (pointer != null)
        {
            if (pointer.isShortHandPresent())
            {
                return executeShorthandPointer(pointer.getShortHand(), node, destination, copyingAttributes);
            }
            else if (pointer.isSchemeBased())
            {
                return executeSchemaPointer(pointer, node, destination, copyingAttributes);
            }
            else
            {
                throw new XPointerResourceException("Unknown pointer expression");
            }
        }
        else
        {
            throw new XPointerResourceException("Unknown pointer expression");
        }
    }
    /**
     * Utility method for verifying xpath expression
     *
     * @param xmlNsSchemes namespaces list
     * @param xpathExpression xpath expression to test
     * @return empty string if expression is right, error otherwise
     */
    public String verifyXPathExpression(final Iterable<XmlNsScheme> xmlNsSchemes, final String xpathExpression)
    {
        LOG.trace("verifyXPathExpression: {}", xpathExpression);
        final XPathCompiler xPathCompiler = processor.newXPathCompiler();
        for (final XmlNsScheme xmlNsScheme : xmlNsSchemes)
        {
            final String localPart = xmlNsScheme.getQName().getLocalPart();
            final String namespaceUri = xmlNsScheme.getQName().getNamespaceURI();
            LOG.trace("declareNamespace {}:{}", localPart, namespaceUri);
            xPathCompiler.declareNamespace(localPart, namespaceUri);
        }
        try
        {
            xPathCompiler.compile(xpathExpression);
        }
        catch (final SaxonApiException e)
        {
            return e.getCause().getMessage();
        }
        return "";
    }

    private void initEngine()
    {
        try
        {
            xQueryCompiler = processor.newXQueryCompiler();
            xQueryExecutableScheme = xQueryCompiler.compile(FIND_QUERY_START + FIND_QUERY_SCHEME + FIND_QUERY_END);
            xQueryExecutableShorthand = xQueryCompiler.compile(FIND_QUERY_START + FIND_QUERY_SHORTHAND + FIND_QUERY_END);
            xPathCompiler = processor.newXPathCompiler();
            xPathCompiler.setCaching(true);
        }
        catch (final SaxonApiException e)
        {
            LOG.error("initEngine", e);
        }
    }

    private int executeShorthandPointer(final PointerPart shortHand, final XdmNode node, final Destination destination,
                                        final Attributes copyingAttributes)
            throws XPointerException
    {
        final XQueryEvaluator xQueryEvaluator = getXQueryEvaluator(shortHand, node.asSource());
        try
        {
            final XdmValue value = xQueryEvaluator.evaluate();
            final XdmSequenceIterator itemsIterator = value.iterator();
            int elementCount = 0;
            if (value.size() == 0)
            {
                throw new XPointerResourceException("No identified subresource");
            }
            while (itemsIterator.hasNext())
            {
                final XdmItem item = itemsIterator.next();
                if (((XdmNode) item).getNodeKind().equals(XdmNodeKind.ELEMENT))
                {
                    elementCount++;
                }
                processor.writeXdmValue(item, destination);
            }
            return elementCount;
        }
        catch (final SaxonApiException e)
        {
            throw new XPointerException(e.getLocalizedMessage(), e);
        }
    }

    private int executeSchemaPointer(final Pointer pointer, final XdmNode node, final Destination destination,
                                     final Attributes copyingAttributes)
            throws XPointerException
    {
        Source sourceTransform = node.asSource();
        final int nbPointerPart = pointer.getSchemeBased().size();
        final ImmutableList.Builder<String> builderExpressions = new ImmutableList.Builder<String>();
        for (int i = 0; i < nbPointerPart; i++)
        {
            final PointerPart part = pointer.getSchemeBased().get(i);
            if (part instanceof XmlNsScheme)
            {
                final XmlNsScheme xmlNsScheme = (XmlNsScheme) part;
                final QName qName = xmlNsScheme.getQName();
                xPathCompiler.declareNamespace(qName.getLocalPart(), qName.getNamespaceURI());
                xQueryCompiler.declareNamespace(qName.getLocalPart(), qName.getNamespaceURI());
            }
            else if (part instanceof XPathScheme)
            {
                final XQueryEvaluator xQueryEvaluator = getXQueryEvaluator(part, sourceTransform);
                try
                {
                    final TeeDestination teeDestination;
                    if (i == (nbPointerPart - 1))
                    {
                        teeDestination = new TeeDestination(destination, new SAXDestination(new DebugHandler()));
                        final XdmValue value = xQueryEvaluator.evaluate();
                        if (value.size() == 0)
                        {
                            throw new XPointerResourceException("No identified subresource");
                        }
                        else
                        {
                            final XdmSequenceIterator itemsIterator = value.iterator();
                            int elementCount = 0;
                            while (itemsIterator.hasNext())
                            {
                                final XdmItem item = itemsIterator.next();
                                if (!item.isAtomicValue() && ((XdmNode) item).getNodeKind().equals(XdmNodeKind.ELEMENT))
                                {
                                    elementCount++;
                                }
                                processor.writeXdmValue(item, teeDestination);
                            }
                            return elementCount;
                        }
                    }
                    else
                    {
                        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        teeDestination = new TeeDestination(new Serializer(baos), new SAXDestination(new DebugHandler()));
                        xQueryEvaluator.run(teeDestination);
                        sourceTransform = processor.getUnderlyingConfiguration().buildDocument(
                                new StreamSource(new ByteArrayInputStream(baos.toByteArray())));
                    }
                }
                catch (final SaxonApiException e)
                {
                    throw new XPointerException(e.getLocalizedMessage(), e);
                }
                catch (final XPathException e)
                {
                    throw new XPointerException(e.getLocalizedMessage(), e);
                }
            }
            else
            {
                builderExpressions.add(part.getExpression());
            }
        }
        final XQueryEvaluator xQueryEvaluator = getXQueryEvaluator(builderExpressions.build(), sourceTransform);
        try
        {
            final TeeDestination teeDestination = new TeeDestination(destination,
                    new SAXDestination(new DebugHandler()));
            final XdmValue value = xQueryEvaluator.evaluate();
            if (value.size() == 0)
            {
                throw new XPointerResourceException("No identified subresource");
            }
            else
            {
                final XdmSequenceIterator itemsIterator = value.iterator();
                int elementCount = 0;
                while (itemsIterator.hasNext())
                {
                    final XdmItem item = itemsIterator.next();
                    if (((XdmNode) item).getNodeKind().equals(XdmNodeKind.ELEMENT))
                    {
                        elementCount++;
                    }
                    processor.writeXdmValue(item, teeDestination);
                }
                return elementCount;
            }
        }
        catch (final SaxonApiException e)
        {
            throw new XPointerException(e.getLocalizedMessage(), e);
        }
    }

    private XPathSelector getXPathSelector(final PointerPart part)
            throws XPointerException
    {
        try
        {
            final XPathExecutable xPathExecutable = xPathCompiler.compile(part.getExpression());
            return xPathExecutable.load();
        }
        catch (final SaxonApiException e)
        {
            throw new XPointerException(e.getLocalizedMessage(), e);
        }
    }

    private XdmValue getContextItem(final Source source, final PointerPart part)
            throws XPointerException
    {
        final XPathSelector xPathSelector = getXPathSelector(part);
        try
        {
            xPathSelector.setContextItem(processor.newDocumentBuilder().build(source));
            return xPathSelector.evaluate();
        }
        catch (final SaxonApiException e)
        {
            throw new XPointerException(e.getLocalizedMessage(), e);
        }
    }

    private XQueryEvaluator getXQueryEvaluator(final List<String> expressions, final Source source)
            throws XPointerException
    {
        final XQueryEvaluator xQueryEvaluator;
        final String newStringquery = FIND_QUERY_DYNAMIC.replaceAll("#VAL#", Joiner.on(" union ").join(expressions));
        try
        {
            xQueryEvaluator = xQueryCompiler.compile(FIND_QUERY_START + newStringquery + FIND_QUERY_END).load();
            xQueryEvaluator.setSource(source);
        }
        catch (final SaxonApiException e)
        {
            throw new XPointerException(e.getLocalizedMessage(), e);
        }
        xQueryEvaluator.setExternalVariable(new net.sf.saxon.s9api.QName("ctxbase"), baseURIValue);
        xQueryEvaluator.setExternalVariable(new net.sf.saxon.s9api.QName("ctxlang"), languageValue);
        return xQueryEvaluator;
    }

    private XQueryEvaluator getXQueryEvaluator(final PointerPart pointerPart, final Source source)
            throws XPointerException
    {
        final XQueryEvaluator xQueryEvaluator;
        final XdmValue shorthandValue;
        if (pointerPart instanceof ShortHand)
        {
            shorthandValue = new XdmAtomicValue(((ShortHand) pointerPart).getName());
            xQueryEvaluator = xQueryExecutableShorthand.load();
            xQueryEvaluator.setExternalVariable(new net.sf.saxon.s9api.QName("shorthand"), shorthandValue);
        }
        else
        {
            final XdmValue contextItem = getContextItem(source, pointerPart);
            xQueryEvaluator = xQueryExecutableScheme.load();
            xQueryEvaluator.setExternalVariable(new net.sf.saxon.s9api.QName("val"), contextItem);
        }
        try
        {
            xQueryEvaluator.setSource(source);
        }
        catch (final SaxonApiException e)
        {
            throw new XPointerException(e.getLocalizedMessage(), e);
        }
        xQueryEvaluator.setExternalVariable(new net.sf.saxon.s9api.QName("ctxbase"), baseURIValue);
        xQueryEvaluator.setExternalVariable(new net.sf.saxon.s9api.QName("ctxlang"), languageValue);
        return xQueryEvaluator;
    }

    Pointer getPointer(final String pointerStr)
            throws XPointerException
    {
        LOG.trace("start analyse '{}'", pointerStr);
        final CharStream input = new ANTLRStringStream(pointerStr);
        LOG.trace("-> start lexer analyse");
        final XPointerLexer xPointerLexer = new XPointerLexer(input);
        final CommonTokenStream commonTokenStream = new CommonTokenStream(xPointerLexer);
        final XPointerParser xPointerParser = new XPointerParser(commonTokenStream);
        xPointerParser.setErrorHandler(xPointerErrorHandler);
        final XPointerParser.pointer_return result;
        try
        {
            LOG.trace("-> start parser analyse");
            result = xPointerParser.pointer();
        }
        catch (final Exception e)
        {
            throw new XPointerException("Unknown pointer expression", e);
        }
        final CommonTree ast = (CommonTree) result.getTree();
        Pointer pointer = null;
        if (ast != null)
        {
            final CommonTreeNodeStream nodes = new CommonTreeNodeStream(ast);
            nodes.setTokenStream(commonTokenStream);
            final XPointerTree xPointerTree = new XPointerTree(nodes);
            xPointerTree.setErrorHandler(xPointerErrorHandler);
            try
            {
                LOG.trace("-> start tree analyse");
                pointer = xPointerTree.pointer();
            }
            catch (final Exception e)
            {
                throw new XPointerException(e);
            }
        }
        LOG.trace("end analyse '{}'", pointerStr);
        return pointer;
    }

    private static class NilXPointerErrorHandler implements XPointerErrorHandler
    {
        @Override
        public void reportError(final String error)
        {
        }
    }

    private static final Logger LOG = LoggerFactory.getLogger(XPointerEngine.class);
    private static final String FIND_QUERY_START =
            " declare variable $ctxbase external;                                               " +
            " declare variable $ctxlang external;                                               ";
    private static final String FIND_QUERY_SHORTHAND =
            " declare variable $shorthand external;                                             " +
            " for $x in fn:element-with-id($shorthand)|//*[@id=$shorthand]                      ";
    private static final String FIND_QUERY_SCHEME =
            " declare variable $val external;                                                   " +
            " for $x in $val                                                                    ";
    private static final String FIND_QUERY_DYNAMIC =
            "  for $x in (#VAL#)                                                                ";
    private static final String FIND_QUERY_END =
            " return typeswitch($x)                                                             " +
            "    case element() return                                                          " +
            "         element {fn:node-name($x)} {                                              " +
            "            (                                                                      " +
            "               if (fn:empty($x/ancestor-or-self::*/@xml:base)) then (              " +
            "                   if (fn:empty($ctxbase)) then ()                                 " +
            "                   else (attribute xml:base {$ctxbase})                            " +
            "               ) else (                                                            " +
            "                   if (fn:empty($x/@xml:base)) then (                              " +
            "                      if (fn:empty($ctxbase)) then ()                              " +
            "                      else (attribute xml:base { fn:base-uri($x) })                " +
            "                   ) else (                                                        " +
            "                      attribute xml:base {$x/@xml:base}                            " +
            "                   )                                                               " +
            "               )                                                                   " +
            "            ),                                                                     " +
            "            (                                                                      " +
            "               let $elt := $x/@xml:lang                                            " +
            "               let $prt := ($x/parent::*/@xml:lang)[last()]                        " +
            "               return if (fn:empty($ctxlang)) then (                               " +
            "                         if (fn:empty($prt) or $prt = '') then (                   " +
            "                            if (fn:empty($elt)) then ()                            " +
            "                            else (attribute xml:lang {$elt})                       " +
            "                         ) else (                                                  " +
            "                            attribute xml:lang {if (fn:empty($elt)) then ($prt)    " +
            "                                                else ($elt)}                       " +
            "                         )                                                         " +
            "                      ) else (                                                     " +
            "                         if (fn:empty($prt) or $prt = '') then (                   " +
            "                            if (fn:empty($elt)) then (attribute xml:lang {''})     " +
            "                            else (attribute xml:lang {$elt})                       " +
            "                         ) else (                                                  " +
            "                            if ($prt = $ctxlang) then (                            " +
            "                               if (fn:empty($elt)) then ()                         " +
            "                               else (attribute xml:lang {$elt})                    " +
            "                            ) else (                                               " +
            "                               attribute xml:lang {if (fn:empty($elt)) then ($prt) " +
            "                                           else ($elt)}                            " +
            "                            )                                                      " +
            "                         )                                                         " +
            "                      )                                                            " +
            "            ),                                                                     " +
            "            $x/(@*|node()) except ($x/@xml:base,$x/@xml:lang)                      " +
            "         }                                                                         " +
            "    default return $x                                                              ";
    private static final DefaultXPointerErrorHandler DEFAULT_XPOINTER_ERROR_HANDLER = new DefaultXPointerErrorHandler();
    private static final NilXPointerErrorHandler NIL_XPOINTER_ERROR_HANDLER = new NilXPointerErrorHandler();
    private final Processor processor;
    private XdmValue baseURIValue;
    private XdmValue languageValue;
    private XQueryExecutable xQueryExecutableShorthand;
    private XQueryExecutable xQueryExecutableScheme;
    private XPathCompiler xPathCompiler;
    private XQueryCompiler xQueryCompiler;
    private XPointerErrorHandler xPointerErrorHandler;
}
