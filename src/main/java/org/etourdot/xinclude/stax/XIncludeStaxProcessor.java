package org.etourdot.xinclude.stax;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import org.antlr.runtime.RecognitionException;
import org.etourdot.xinclude.*;
import org.etourdot.xinclude.xpointer.XPointer;
import org.etourdot.xinclude.xpointer.XPointerContext;
import org.etourdot.xinclude.xpointer.XPointerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
* Created by IntelliJ IDEA.
* User: etourdot
* Date: 04/11/11
* Time: 23:22
*/
class XIncludeStaxProcessor
{
    private static final Logger LOG = LoggerFactory.getLogger(XIncludeStaxProcessor.class);

    private final URI baseAbsoluteURI;
    private final XIncProcStaxFactory factory;

    XIncludeStaxProcessor(final XIncProcStaxFactory factory, final URI baseAbsoluteURI)
    {
        this.factory = factory;
        this.baseAbsoluteURI = baseAbsoluteURI;
    }

    void doProcess(final URI baseRelativeURI, final XMLEventReader reader, final XMLEventWriter writer)
                            throws XMLStreamException, XIncludeFatalException
    {
        final ArrayDeque<URI> previousUrisSet = new ArrayDeque<URI>();
        previousUrisSet.add(baseAbsoluteURI.resolve(baseRelativeURI));
        doProcess(baseRelativeURI, reader, writer, previousUrisSet);
    }

    void doProcess(final URI baseRelativeURI, final XMLEventReader reader, final XMLEventWriter writer,
                   final Deque<URI> previousURIs)
                            throws XMLStreamException, XIncludeFatalException
    {
        final Deque<StartElement> stackElements = new ArrayDeque<StartElement>();
        boolean isTopElement = true;
        while (reader.hasNext())
        {
            final XMLEvent event = reader.nextEvent();
            final int type = event.getEventType();
            if (type == XMLStreamConstants.START_ELEMENT)
            {
                final StartElement startElement = event.asStartElement();
                final Attribute langAtt = startElement.getAttributeByName(XIncProcConfiguration.XMLLANG_QNAME);
                if (langAtt != null)
                {
                    stackElements.addLast(startElement);
                }
                Attribute newBaseAtt = null;
                if (isTopElement)
                {
                    newBaseAtt = getBaseAttribute(0, baseRelativeURI, startElement);
                    isTopElement = false;
                }
                if (XIncProcUtils.isXInclude(startElement.getName()))
                {
                    doProcessXInclude(baseRelativeURI, startElement, stackElements.peekLast(), reader, writer, previousURIs);
                }
                else if (XIncProcUtils.isFallback(startElement.getName()))
                {
                    throw new XIncludeFatalException("Fallback not in xinclude element container");
                }
                else
                {
                    final ImmutableList<Attribute> attributes = getNewAttributes(startElement.getAttributes(), newBaseAtt, langAtt);
                    final StartElement newStartElement = factory.getXmlEventFactory().createStartElement(startElement.getName(),
                            attributes.iterator(), startElement.getNamespaces());
                    writer.add(newStartElement);
                }
            }
            else if (type == XMLStreamConstants.END_ELEMENT)
            {
                final EndElement endElement = event.asEndElement();
                final StartElement startElement = stackElements.peek();
                if (startElement != null && startElement.getName().equals(endElement.getName()))
                {
                    stackElements.removeLast();
                }
                if (!XIncProcUtils.isXInclude(endElement.getName()) && !XIncProcUtils.isFallback(endElement.getName()))
                {
                    writer.add(event);
                }
            }
            else if (type != XMLStreamConstants.START_DOCUMENT &&
                     type != XMLStreamConstants.END_DOCUMENT &&
                     type != XMLStreamConstants.DTD)
            {
                writer.add(event);
            }
        }
    }

    private ImmutableList<Attribute> getNewAttributes(final Iterator itAttribute, final Attribute newBaseAtt,
                                                      final Attribute newLangAtt)
    {
        final ImmutableList.Builder<Attribute> attributeBuilder = new ImmutableList.Builder<Attribute>();
        while (itAttribute.hasNext())
        {
            final Attribute attribute = (Attribute) itAttribute.next();
            if (!attribute.getName().equals(XIncProcConfiguration.XMLBASE_QNAME) &&
                !attribute.getName().equals(XIncProcConfiguration.XMLLANG_QNAME))
            {
                attributeBuilder.add(attribute);
            }
        }
        if (newBaseAtt != null && factory.getConfiguration().isBaseUrisFixup())
        {
            attributeBuilder.add(newBaseAtt);
        }
        if (newLangAtt != null && factory.getConfiguration().isLanguageFixup())
        {
            attributeBuilder.add(newLangAtt);
        }
        return attributeBuilder.build();
    }

    void doProcessXInclude(final URI baseRelativeURI, final StartElement startElement, final StartElement lastElementWithLang,
                           final XMLEventReader reader, final XMLEventWriter writer, final Deque<URI> previousURIs)
            throws XIncludeFatalException, XMLStreamException
    {
        try
        {
            // Verifying attributes
            XIncProcUtils.checkXInclude(startElement);
            final Attribute parseAtt = startElement.getAttributeByName(XIncProcConfiguration.ATT_PARSE);
            final String parse = (parseAtt==null)? XIncProcConfiguration.XML : parseAtt.getValue();
            final Attribute hrefAtt = startElement.getAttributeByName(XIncProcConfiguration.ATT_HREF);
            final String href = (hrefAtt==null)? null : hrefAtt.getValue();
            final Attribute baseAtt = startElement.getAttributeByName(XIncProcConfiguration.XMLBASE_QNAME);
            final String base = (baseAtt==null)? "" : baseAtt.getValue();
            final Attribute encodingAtt = startElement.getAttributeByName(XIncProcConfiguration.ATT_ENCODING);
            final String encoding = (encodingAtt==null)? Charsets.UTF_8.displayName() : encodingAtt.getValue();
            final Attribute xpointerAtt = startElement.getAttributeByName(XIncProcConfiguration.ATT_XPOINTER);
            final String xpointer = (xpointerAtt==null)? null : xpointerAtt.getValue();
            final URI newBaseRelativeURI = baseRelativeURI.resolve(new URI(base));
            if (XIncProcConfiguration.XML.equals(parse))
            {
                // Processing xml document
                includeXmlContent(newBaseRelativeURI, href, xpointer, lastElementWithLang, writer, previousURIs);
            }
            else if (XIncProcConfiguration.TEXT.equals(parse))
            {
                // Processing text document
                includeTextContent(newBaseRelativeURI, href, encoding, writer);
            }
        }
        catch (final XIncludeResourceException e)
        {
            doProcessFallback(baseRelativeURI, reader, writer);
        }
        catch (URISyntaxException e)
        {
            doProcessFallback(baseRelativeURI, reader, writer);
        }
        // Skip until xinclude end element
        while (reader.hasNext())
        {
            final XMLEvent ev = reader.nextEvent();
            if (ev.getEventType() == XMLStreamConstants.END_ELEMENT)
            {
                if (XIncProcUtils.isXInclude(ev.asEndElement().getName()))
                {
                    break;
                }
            }
            if (ev.getEventType() == XMLStreamConstants.START_ELEMENT)
            {
                if (XIncProcUtils.isXInclude(ev.asStartElement().getName()))
                {
                    throw new XIncludeFatalException("The content of the include element is another include element");
                }
            }
        }
    }

    private void doProcessFallback(final URI baseRelativeURI, final XMLEventReader reader, final XMLEventWriter writer)
            throws XMLStreamException, XIncludeFatalException
    {
        final XMLEvent fallbackEvent = reader.nextTag();
        if (!fallbackEvent.isStartElement() ||
            !XIncProcUtils.isFallback(fallbackEvent.asStartElement().getName()))
        {
            throw new XIncludeFatalException("No fallback element");
        }
        while (reader.hasNext())
        {
            final XMLEvent event = reader.nextEvent();
            final int type = event.getEventType();
            if (type == XMLStreamConstants.START_ELEMENT)
            {
                final StartElement startElement = event.asStartElement();
                if (XIncProcUtils.isXInclude(startElement.getName()))
                {
                    throw new XIncludeFatalException("Wrong include element place");
                }
                else if (XIncProcUtils.isFallback(startElement.getName()))
                {
                    throw new XIncludeFatalException("Fallback not in xinclude element container");
                }
                else
                {
                    writer.add(startElement);
                }
            }
            else if (type == XMLStreamConstants.END_ELEMENT)
            {
                final EndElement endElement = event.asEndElement();
                if (XIncProcUtils.isFallback(endElement.getName()))
                {
                    break;
                }
                else
                {
                    writer.add(endElement);
                }
            }
            else if (type != XMLStreamConstants.START_DOCUMENT &&
                type != XMLStreamConstants.END_DOCUMENT &&
                type != XMLStreamConstants.DTD)
            {
                writer.add(event);
            }

        }
        /*while (reader.hasNext())
        {
            final XMLEvent ev = reader.nextEvent();
            if (ev.getEventType() == XMLStreamConstants.START_ELEMENT)
            {
                if (XIncProcUtils.isFallback(ev.asStartElement().getName()))
                {
                    throw new XIncludeFatalException("More than one fallback element");
                }
            }
        }*/
    }

    private void includeTextContent(final URI baseRelativeURI, final String href,
                                    final String encoding, final XMLEventWriter writer)
            throws XIncludeFatalException, XIncludeResourceException {
        try
        {
            final URI source = baseAbsoluteURI.resolve(baseRelativeURI).resolve(new URI(href));
            // Writing content to stat event writer
            writer.add(factory.getXmlEventFactory().createCharacters(XIncProcUtils.readTextURI(source, encoding)));
        }
        catch (XMLStreamException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
        catch (URISyntaxException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
    }

    private void includeXmlContent(final URI baseRelativeURI, final String href, final String xpointer,
                                   final StartElement lastElementWithLang,
                                   final XMLEventWriter writer, Deque<URI> previousURIs)
             throws XIncludeFatalException, XIncludeResourceException
    {
        try
        {
            final URI source = baseAbsoluteURI.resolve(baseRelativeURI).resolve(new URI(href));
            if (previousURIs.contains(source))
            {
                throw new XIncludeFatalException("Inclusion loops error");
            }
            else
            {
                previousURIs.add(source);
            }
            final XMLEventReader readerBase = factory.getXmlInputFactory().
                    createXMLEventReader(new StreamSource(source.toASCIIString()));
            final XIncludeStaxProcessor processor = new XIncludeStaxProcessor(this.factory, baseAbsoluteURI);
            if (Strings.isNullOrEmpty(xpointer))
            {
                processor.doProcess(baseRelativeURI.resolve(new URI(href)), readerBase, writer, previousURIs);
            }
            else
            {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final XMLEventWriter writerBase = factory.getXmlOutputFactory().createXMLEventWriter(baos, Charsets.UTF_8.displayName());
                processor.doProcess(baseRelativeURI.resolve(new URI(href)), readerBase, writerBase, previousURIs);
                writerBase.flush();
                writerBase.close();
                doProcessXPointer(baos, writer, xpointer, baseRelativeURI.resolve(new URI(href)), lastElementWithLang);
            }
            previousURIs.remove(source);
        }
        catch (XMLStreamException e)
        {
            throw new XIncludeResourceException(e);
        }
        catch (URISyntaxException e)
        {
            throw new XIncludeFatalException(e);
        }
        catch (XPointerException e)
        {
            throw new XIncludeFatalException(e);
        }
    }


    private void doProcessXPointer(final ByteArrayOutputStream baOutputStream, final XMLEventWriter writer,
                                   final String xpointer, final URI baseRelativeURI, final StartElement lastElementWithLang)
                            throws XPointerException {
        try
        {
            final XPointerContext context = new XPointerContext(xpointer, factory.getConfiguration());
            // Writing parsing stream
            final Attribute parentLangAttribute = getParentLangAttribute(lastElementWithLang);
            final InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(
                    getXIncludeBytes(baOutputStream, context)), Charsets.UTF_8.displayName());
            if (isr.ready())
            {
                readingStreamWithXPointer(factory.getXmlInputFactory().createXMLEventReader(isr), baseRelativeURI,
                        parentLangAttribute, context, writer);
            }
        }
        catch(final Exception e)
        {
            throw new XPointerException(e);
        }
    }

    private void readingStreamWithXPointer(final XMLEventReader reader, final URI baseRelativeURI,
                                           final Attribute parentLangAttribute, final XPointerContext context,
                                           final XMLEventWriter writer) throws Exception
    {
        Attribute topLangAttribute = null;
        int level = 0;
        while (reader.hasNext())
        {
            final XMLEvent event = reader.nextEvent();
            final int type = event.getEventType();
            if (type == XMLStreamConstants.START_ELEMENT)
            {
                final StartElement startElement = event.asStartElement();
                topLangAttribute = readingStartElementWithXPointer(startElement, level, baseRelativeURI,
                            parentLangAttribute, topLangAttribute, writer, context.getConfiguration().isLanguageFixup());
                level ++;
            }
            else if (type == XMLStreamConstants.END_ELEMENT)
            {
                final EndElement endElement = event.asEndElement();
                if (XPointer.isTopElement(endElement.getName()))
                {
                    topLangAttribute = null;
                }
                else if (!XPointer.isRootElement(endElement.getName()))
                {
                    writer.add(event);
                }
                level --;
            }
            else if (type != XMLStreamConstants.START_DOCUMENT &&
                     type != XMLStreamConstants.END_DOCUMENT &&
                     type != XMLStreamConstants.DTD)
            {
                writer.add(event);
            }
        }
    }

    private Attribute readingStartElementWithXPointer(final StartElement startElement, final int level, final URI baseRelativeURI,
                                                 final Attribute parentLangAttribute, final Attribute topLangAttribute,
                                                 final XMLEventWriter writer, final boolean isLanguageFixup)
            throws Exception
    {
        if (XPointer.isTopElement(startElement.getName()))
        {
            return getTopLangAttribute(startElement);
        }
        else if (!XPointer.isRootElement(startElement.getName()))
        {
            final Attribute newBaseAtt = getBaseAttribute(level, baseRelativeURI, startElement);
            final Attribute currentLangAttribute = startElement.getAttributeByName(XIncProcConfiguration.XMLLANG_QNAME);
            final Attribute newLangAtt = getLangAttributes(level, currentLangAttribute, parentLangAttribute,
                                            topLangAttribute, isLanguageFixup);
            writeNewElement(writer, startElement.getName(), startElement.getNamespaces(),
                    startElement.getAttributes(), newBaseAtt, newLangAtt);
            return topLangAttribute;
        }
        return null;
    }

    private Attribute getTopLangAttribute(final StartElement startElement)
    {
        final Attribute langAttribute = startElement.getAttributeByName(XIncProcConfiguration.XMLLANG_QNAME);
        if (langAttribute == null)
        {
            return factory.getXmlEventFactory().createAttribute(XIncProcConfiguration.XMLLANG_QNAME, "");
        }
        return langAttribute;
    }

    private Attribute getLangAttributes(final int level, final Attribute currentLangAttribute,
                                        final Attribute parentLangAttribute, final Attribute topLangAttribute,
                                        final boolean isLanguageFixup)
    {
        if (level > 2)
        {
            return null;
        }
        if (currentLangAttribute == null && isLanguageFixup)
        {
            if (!parentLangAttribute.getValue().equals(topLangAttribute.getValue()))
            {
                return topLangAttribute;
            }
            else
            {
                return  null;
            }
        }
        return currentLangAttribute;
    }

    private void writeNewElement(final XMLEventWriter writer, final QName name, final Iterator namespaces,
                                 final Iterator attributes, final Attribute newBaseAtt, final Attribute newLangAtt) throws Exception
    {
        final ImmutableList<Attribute> attributesList = getNewAttributes(attributes, newBaseAtt, newLangAtt);
        final StartElement newStartElement = factory.getXmlEventFactory().createStartElement(name,
            attributesList.iterator(), namespaces);
        writer.add(newStartElement);
    }

    private Attribute getParentLangAttribute(StartElement lastElementWithLang)
    {
        if (lastElementWithLang == null)
        {
            return factory.getXmlEventFactory().createAttribute(XIncProcConfiguration.XMLLANG_QNAME, "");
        }
        else
        {
            return lastElementWithLang.getAttributeByName(XIncProcConfiguration.XMLLANG_QNAME);
        }
    }

    private byte[] getXIncludeBytes(final ByteArrayOutputStream baOutputStream, final XPointerContext context)
            throws Exception
    {
        // Getting pointer
        final XPointer pointer = factory.getConfiguration().getXPointerEngine().parse(context);
        final InputStreamReader in = new InputStreamReader(new ByteArrayInputStream(baOutputStream.toByteArray()),
                Charsets.UTF_8.displayName());
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final OutputStreamWriter out = new OutputStreamWriter(baos, Charsets.UTF_8.displayName());
        // Parsing Xinclude stream with pointer
        pointer.parse(in, out);
        out.flush();
        out.close();
        return baos.toByteArray();
    }

    private Attribute getBaseAttribute(final int level, final URI baseRelativeURI, final StartElement startElement)
            throws XIncludeFatalException
    {
        if (level > 2)
        {
            return null;
        }
        final Attribute baseAtt = startElement.getAttributeByName(XIncProcConfiguration.XMLBASE_QNAME);
        if (!Strings.isNullOrEmpty(baseRelativeURI.toASCIIString()))
        {
            final URI newBaseURI;
            if (baseAtt != null)
            {
                try
                {
                    newBaseURI = baseRelativeURI.resolve(new URI(baseAtt.getValue()));
                }
                catch (URISyntaxException e)
                {
                    throw new XIncludeFatalException(e);
                }
            }
            else
            {
                newBaseURI = baseRelativeURI;
            }
            return factory.getXmlEventFactory().createAttribute(XIncProcConfiguration.XMLBASE_QNAME, newBaseURI.toASCIIString());
        }
        return baseAtt;
    }
}
