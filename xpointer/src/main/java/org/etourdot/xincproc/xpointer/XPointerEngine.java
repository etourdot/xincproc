package org.etourdot.xincproc.xpointer;

import com.google.common.collect.ImmutableList;
import net.sf.saxon.om.DocumentInfo;
import net.sf.saxon.s9api.*;
import net.sf.saxon.trans.XPathException;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.etourdot.xincproc.xpointer.exceptions.XPointerException;
import org.etourdot.xincproc.xpointer.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 27/10/12
 * Time: 11:18
 */
public class XPointerEngine {
    private static final Logger log = LoggerFactory.getLogger(XPointerEngine.class);

    private static final String FIND_QUERY_START =
        " declare variable $ctxbase external;                                               " +
        " declare variable $ctxlang external;                                               ";
    private static final String FIND_QUERY_SHORTHAND =
        " declare variable $shorthand external;                                             " +
        " for $x in fn:id($shorthand)|//*[@id=$shorthand]                                   ";
    private static final String FIND_QUERY_SCHEME =
        " declare variable $val external;                                                   " +
        " for $x in $val                                                                    ";
    private static final String FIND_QUERY_END =
        " return typeswitch($x)                                                             " +
        "    case element() return                                                          " +
        "         element {fn:node-name($x)} {                                              " +
        "            (                                                                      " +
        "               if (fn:empty($x/ancestor-or-self::*/@xml:base)) then (              " +
        "                   if (fn:empty($ctxbase)) then ()                                 " +
        "                   else (attribute xml:base {$ctxbase})                            " +
        "               ) else (attribute xml:base { fn:base-uri($x) })                     " +
        "            ),                                                                     " +
        "            (                                                                      " +
        "               let $elt := $x/@xml:lang                                            " +
        "               let $prt := ($x/parent::*/@xml:lang)[last()]                      " +
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

    private static final DefaultXPointerErrorHandler defaultXPointerErrorHandler = new DefaultXPointerErrorHandler();

    private XdmValue baseURIValue;

    private XdmValue languageValue;

    public String verifyXPathExpression(final ImmutableList.Builder<XmlNsScheme> xmlnsBuilder, final String xpathExpression)
    {
        log.trace("verifyXPathExpression: {}", xpathExpression);
        final XPathCompiler xPathCompiler = processor.newXPathCompiler();
        for (final XmlNsScheme xmlNsScheme : xmlnsBuilder.build())
        {
            final String localPart = xmlNsScheme.getQName().getLocalPart();
            final String namespaceUri = xmlNsScheme.getQName().getNamespaceURI();
            log.trace("declareNamespace {}:{}", localPart, namespaceUri);
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

    private static class NilXPointerErrorHandler implements XPointerErrorHandler
    {
        @Override
        public void reportError(String error)
        {
        }
    }
    private static final NilXPointerErrorHandler nilXPointerErrorHandler = new NilXPointerErrorHandler();

    private final Processor processor;
    private XQueryExecutable xQueryExecutableShorthand;
    private XQueryExecutable xQueryExecutableScheme;
    private XPathCompiler xPathCompiler;

    private XPointerErrorHandler xPointerErrorHandler;

    public XPointerEngine()
    {
        this(new Processor(false));
    }

    public XPointerEngine(final Processor processor)
    {
        this.processor = processor;
        setBaseURI(null);
        setLanguage(null);
        initEngine();
    }

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

    public XPointerEngine setXPointerErrorHandler(final XPointerErrorHandler xPointerErrorHandler)
    {
        this.xPointerErrorHandler = xPointerErrorHandler;
        return this;
    }

    public XPointerErrorHandler getxPointerErrorHandler()
    {
        return xPointerErrorHandler;
    }

    private void initEngine()
    {
        try
        {
            final XQueryCompiler xQueryCompiler = processor.newXQueryCompiler();
            xQueryExecutableShorthand = xQueryCompiler.compile(FIND_QUERY_START + FIND_QUERY_SHORTHAND + FIND_QUERY_END);
            xQueryExecutableScheme = xQueryCompiler.compile(FIND_QUERY_START + FIND_QUERY_SCHEME + FIND_QUERY_END);
            xPathCompiler = processor.newXPathCompiler();
            xPathCompiler.setCaching(true);
        }
        catch (final SaxonApiException e)
        {
            log.error("initEngine", e);
        }
    }


    Pointer getPointer(final String pointerStr)
            throws XPointerException
    {
        log.trace("start analyse '{}'", pointerStr);
        final CharStream input = new ANTLRStringStream(pointerStr);
        log.trace("-> start lexer analyse");
        final XPointerLexer xPointerLexer = new XPointerLexer(input);
        final CommonTokenStream commonTokenStream = new CommonTokenStream(xPointerLexer);
        final XPointerParser xPointerParser = new XPointerParser(commonTokenStream);
        xPointerParser.setErrorHandler(xPointerErrorHandler);
        XPointerParser.pointer_return result = null;
        try
        {
            log.trace("-> start parser analyse");
            result = xPointerParser.pointer();
        }
        catch (final Exception e)
        {
            log.error("parser exception", e);
            throw new XPointerException(e);
        }
        final CommonTree ast = (CommonTree) result.getTree();
        Pointer pointer = null;
        if (ast != null)
        {
            final CommonTreeNodeStream nodes = new CommonTreeNodeStream(ast);
            nodes.setTokenStream(commonTokenStream);
            final XPointerTree xPointerTree = new XPointerTree(nodes);
            xPointerTree.setErrorHandler(xPointerErrorHandler);
            xPointerTree.setPointerFactory(new PointerFactory());
            try
            {
                log.trace("-> start tree analyse");
                pointer = xPointerTree.pointer();
            }
            catch (final Exception e)
            {
                log.error("tree exception", e);
                throw new XPointerException(e);
            }
        }
        log.trace("end analyse '{}'", pointerStr);
        return pointer;
    }

    /**
     * Execute xpointer expression on xml source returning a xml string
     *
     * @param pointerStr xpointer expression
     * @param source xml source
     * @return serialized xml result or an empty string (not null)
     * @throws XPointerException
     */
    public String execute(final String pointerStr, final Source source)
    {
        try
        {
            final StringWriter stringWriter = new StringWriter();
            Serializer serializer = processor.newSerializer(stringWriter);
            serializer.setOutputProperty(Serializer.Property.OMIT_XML_DECLARATION, "yes");
            executeToDestination(pointerStr, source, serializer);
            return stringWriter.toString();
        }
        catch (final XPointerException e)
        {
            if (xPointerErrorHandler != null)
            {
                xPointerErrorHandler.reportError(e.getLocalizedMessage());
            }
            else
            {
                log.error(e.getLocalizedMessage(), e);
            }
        }
        return "";
    }

    /**
     * Execute a xpointer expression on xml source.
     * The result is send to a Saxon Destination {@link http://www.saxonica.com/documentation/javadoc/net/sf/saxon/s9api/Destination.html}
     *
     * @param pointerStr xpointer expression
     * @param source xml source
     * @param destination Saxon destination of result stream
     * @throws XPointerException
     */
    public void executeToDestination(final String pointerStr, final Source source, final Destination destination)
            throws XPointerException
    {
        final Pointer pointer = getPointer(pointerStr);
        try
        {
            final DocumentInfo documentInfo = processor.getUnderlyingConfiguration().buildDocument(source);
            if (pointer != null)
            {
                if (pointer.isShortHand())
                {
                    executeShorthandPointer(pointer.getShortHand(), documentInfo, destination);
                }
                else
                {
                    executeSchemaPointer(pointer, documentInfo, destination);
                }
            }
        }
        catch (final XPathException e)
        {
            throw new XPointerException(e);
        }
    }

    private void executeShorthandPointer(final ShortHand shortHand, final Source source, final Destination destination)
            throws XPointerException
    {
        final XQueryEvaluator xQueryEvaluator = getXQueryEvaluator(shortHand, source);
        try
        {
            for (final Iterator<XdmItem> itResults = xQueryEvaluator.iterator(); itResults.hasNext(); ) {
                final XdmItem nextItem =  itResults.next();
                processor.writeXdmValue(nextItem, destination);
            }
        }
        catch (final SaxonApiException e)
        {
            throw new XPointerException(e);
        }
    }

    private void executeSchemaPointer(final Pointer pointer, final Source source, final Destination destination)
            throws XPointerException
    {
        Source sourceTransform = source;
        final int nbPointerPart = pointer.getSchemeBased().size();
        for (int i = 0 ; i < nbPointerPart ; i++)
        {
            final PointerPart part = pointer.getSchemeBased().get(i);
            if (part instanceof XmlNsScheme)
            {
                final XmlNsScheme xmlNsScheme = (XmlNsScheme) part;
                final QName qName = xmlNsScheme.getQName();
                xPathCompiler.declareNamespace(qName.getLocalPart(), qName.getNamespaceURI());
            }
            else
            {
                final XQueryEvaluator xQueryEvaluator = getXQueryEvaluator(part, sourceTransform);
                if (i == (nbPointerPart-1))
                {
                    try
                    {
                        xQueryEvaluator.run(destination);
                    }
                    catch (final SaxonApiException e)
                    {
                        throw new XPointerException(e);
                    }
                }
                else
                {
                    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    final Serializer serializer = new Serializer(baos);
                    try
                    {
                        xQueryEvaluator.run(serializer);
                    }
                    catch (final SaxonApiException e)
                    {
                        throw new XPointerException(e);
                    }
                    sourceTransform = new StreamSource(new ByteArrayInputStream(baos.toByteArray()));
                }
            }
        }
    }

    private XPathSelector getXPathSelector(final PointerPart part)
            throws XPointerException
    {
        final String xpathExpression = part.getExpression().replaceAll("\\^\\(", "(").replaceAll("\\^\\)",")").replaceAll("\\^\\^", "^");
        try
        {
            final XPathExecutable xPathExecutable = xPathCompiler.compile(xpathExpression);
            return xPathExecutable.load();
        }
        catch (final SaxonApiException e)
        {
            throw new XPointerException(e.getLocalizedMessage(), e);
        }
    }

    private XdmValue getContextItem(final Source source, PointerPart part)
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
            throw new XPointerException(e);
        }
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
            shorthandValue = XdmEmptySequence.getInstance();
            xQueryEvaluator = xQueryExecutableScheme.load();
            final XdmValue contextItem = getContextItem(source, pointerPart);
            xQueryEvaluator.setExternalVariable(new net.sf.saxon.s9api.QName("val"), contextItem);
        }
        try
        {
            xQueryEvaluator.setSource(source);
        }
        catch (final SaxonApiException e)
        {
            throw new XPointerException(e);
        }
        xQueryEvaluator.setExternalVariable(new net.sf.saxon.s9api.QName("ctxbase"), baseURIValue);
        xQueryEvaluator.setExternalVariable(new net.sf.saxon.s9api.QName("ctxlang"), languageValue);
        return xQueryEvaluator;
    }

    /**
     * Return an error handler which forget error messages
     *
     * @return an XPointerErrorHandler
     */
    public static XPointerErrorHandler createNilXPointerErrorHandler()
    {
        return nilXPointerErrorHandler;
    }

    /**
     * Return a default error handler
     *
     * @return an XPointerErrorHandler
     */
    public static XPointerErrorHandler createDefaultXPointerErrorHandler()
    {
        return defaultXPointerErrorHandler;
    }

    class XPointerExecutor {
        private XPathSelector xPathSelector;
        private XQueryEvaluator xQueryEvaluator;

        public void setxPathSelector(final XPathSelector xPathSelector)
        {
            this.xPathSelector = xPathSelector;
        }

        public void setxQueryEvaluator(final XQueryEvaluator xQueryEvaluator)
        {
            this.xQueryEvaluator = xQueryEvaluator;
        }
    }
}
