package org.etourdot.xincproc.xpointer;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import net.sf.saxon.s9api.*;
import org.antlr.runtime.RecognitionException;
import org.etourdot.xincproc.xpointer.model.*;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 27/10/12
 * Time: 11:18
 */
public class XPointerEngine {
    static final String QUERY =
            "declare variable $nodes external;" +
            "element root {" +
            "  for $x in $nodes" +
            "  return element top {" +
            "         if (not(fn:empty($x/ancestor::*/@xml:lang))) then " +
            "            attribute xml:lang {($x/ancestor::*/@xml:lang)[last()]} else ()," +
            "         typeswitch($x) " +
            "         case element() return " +
            "                 element {fn:node-name($x)} " +
            "                 {$x/@*, " +
            "                  if (fn:empty($x/ancestor::*/@xml:lang)) then ()" +
            "                  else if (($x/ancestor::*/@xml:lang)[last()] != '') then " +
            "                       attribute xml:lang {($x/ancestor::*/@xml:lang)[last()]}" +
            "                  else (), " +
            "                  $x/node()}" +
            "         case text() return $x" +
            "         default return $x" +
            "  }" +
            "}";

    final Processor processor;
    XQueryEvaluator xQueryEvaluator;

    public XPointerEngine() {
        this(new Processor(false));
    }

    public XPointerEngine(final Processor processor) {
        this.processor = processor;
        final XQueryCompiler xQueryCompiler= processor.newXQueryCompiler();
        try {
            final XQueryExecutable xQueryExecutable = xQueryCompiler.compile(QUERY);
            xQueryEvaluator = xQueryExecutable.load();
        } catch (SaxonApiException e) {
            //TODO
            e.printStackTrace();
        }
    }

    public Pointer getPointer(final String pointerStr) throws RecognitionException {
        return XPointerAnalyser.analyse(pointerStr);
    }

    public XdmValue execute(final String pointerStr, final Source source) throws RecognitionException,
            SaxonApiException {
        final Pointer pointer = getPointer(pointerStr);
        if (pointer.isShortHand()) {
            return executeShorthandPointer(pointer, source);
        } else {
            return executeSchemaPointer(pointer, source);
        }
    }

    public void execute(final String pointerStr, final Source source, final Destination destination) throws RecognitionException,
            SaxonApiException {
        final Pointer pointer = getPointer(pointerStr);
        if (pointer.isShortHand()) {
            executeShorthandPointer(pointer, source, destination);
        } else {
            executeSchemaPointer(pointer, source, destination);
        }
    }

    private XdmValue executeSchemaPointer(final Pointer pointer, final Source source) throws SaxonApiException {
        ImmutableList<XQueryEvaluator> listxXQueryEvaluators = initListXQueryEvaluator(pointer, source, null);
        if (!listxXQueryEvaluators.isEmpty()) {
            return listxXQueryEvaluators.get(0).evaluate();
        } else {
            return new XdmValue(XdmEmptySequence.getInstance());
        }
    }

    private XdmValue executeShorthandPointer(final Pointer pointer, final Source source) throws SaxonApiException {
        final XQueryEvaluator xQueryEvShortHand = getShortHandXQueryEvaluator(pointer, source);
        return xQueryEvShortHand.evaluate();
    }

    private void executeSchemaPointer(final Pointer pointer, final Source source, final Destination destination)
            throws SaxonApiException {
        ImmutableList<XQueryEvaluator> listxXQueryEvaluators = initListXQueryEvaluator(pointer, source, destination);
        if (!listxXQueryEvaluators.isEmpty()) {
            listxXQueryEvaluators.get(0).run();
        }
    }

    private ImmutableList<XQueryEvaluator> initListXQueryEvaluator(Pointer pointer, Source source,
                                                                   Destination destination) throws SaxonApiException {
        ImmutableList.Builder<XQueryEvaluator> xQueryEvaluatorBuilder = new ImmutableList.Builder<XQueryEvaluator>();
        constructXQueryEvaluatorBuilder(pointer, xQueryEvaluatorBuilder);
        return setSourceAndDestination(source, destination, xQueryEvaluatorBuilder);
    }

    private void constructXQueryEvaluatorBuilder(Pointer pointer, ImmutableList.Builder<XQueryEvaluator> xQueryEvaluatorBuilder)
            throws SaxonApiException {
        ImmutableList.Builder<XmlNsScheme> xmlnsBuilder = new ImmutableList.Builder<XmlNsScheme>();
        for (PointerPart part : pointer.getSchemeBased()) {
            if (part instanceof XmlNsScheme) {
                xmlnsBuilder.add((XmlNsScheme) part);
            } else {
                final XQueryCompiler xQueryCompiler = processor.newXQueryCompiler();
                declareNamespaces(xmlnsBuilder, xQueryCompiler);
                String query = null;
                if (part instanceof ElementScheme) {
                    query = XPointerAnalyser.getQueryFromElementScheme((ElementScheme) part);
                } else if (part instanceof XPointerScheme) {
                    query = XPointerAnalyser.getQueryFromXPointerScheme((XPointerScheme) part);
                } else if (part instanceof XPathScheme) {
                    query = XPointerAnalyser.getQueryFromXPathScheme((XPathScheme) part);
                }
                if (!Strings.isNullOrEmpty(query)) {
                    final XQueryExecutable xQueryExecutable = xQueryCompiler.compile(query);
                    final XQueryEvaluator xQueryEvalPart = xQueryExecutable.load();
                    xQueryEvaluatorBuilder.add(xQueryEvalPart);
                }
            }
        }
    }

    private void declareNamespaces(ImmutableList.Builder<XmlNsScheme> xmlnsBuilder, XQueryCompiler xQueryCompiler) {
        ImmutableList<XmlNsScheme> listXmlns = xmlnsBuilder.build();
        for (XmlNsScheme xmlNsScheme : listXmlns) {
            final QName qName = xmlNsScheme.getQName();
            xQueryCompiler.declareNamespace(qName.getPrefix(), qName.getLocalPart());
        }
    }

    private ImmutableList<XQueryEvaluator> setSourceAndDestination(Source source, Destination destination,
                                                                   ImmutableList.Builder<XQueryEvaluator> xQueryEvaluatorBuilder)
            throws SaxonApiException {
        ImmutableList<XQueryEvaluator> listxXQueryEvaluators = xQueryEvaluatorBuilder.build();
        for (int i = 0 ; i < listxXQueryEvaluators.size() ; i++) {
            if (i==0) {
                listxXQueryEvaluators.get(i).setSource(source);
            }
            if (i<(listxXQueryEvaluators.size()-1)) {
                listxXQueryEvaluators.get(i).setDestination(listxXQueryEvaluators.get(i+1));
            } else if (i==(listxXQueryEvaluators.size()-1)) {
                listxXQueryEvaluators.get(i).setDestination(destination);
            }
        }
        return listxXQueryEvaluators;
    }

    private void executeShorthandPointer(final Pointer pointer, final Source source, final Destination destination) throws SaxonApiException {
        final XQueryEvaluator xQueryEvShortHand = getShortHandXQueryEvaluator(pointer, source);
        xQueryEvShortHand.run(destination);
    }

    private XQueryEvaluator getShortHandXQueryEvaluator(Pointer pointer, Source source) throws SaxonApiException {
        final String query = XPointerAnalyser.getQueryFromShortHand(pointer.getShortHand());
        final XQueryCompiler xQueryCompiler = processor.newXQueryCompiler();
        final XQueryExecutable xQueryExecutable = xQueryCompiler.compile(query);
        final XQueryEvaluator xQueryEvShortHand = xQueryExecutable.load();
        xQueryEvShortHand.setSource(source);
        return xQueryEvShortHand;
    }
}
