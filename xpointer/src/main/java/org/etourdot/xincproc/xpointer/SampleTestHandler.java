package org.etourdot.xincproc.xpointer;

import org.xml.sax.*;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;

import java.io.IOException;
import java.io.PrintStream;


public class SampleTestHandler implements ContentHandler, LexicalHandler, DTDHandler, EntityResolver, DeclHandler {

    private PrintStream printStream = System.out;

    public void setPrintStream(PrintStream stream) {
        printStream = stream;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public void setDocumentLocator(Locator locator) {
        printStream.println("setDocumentLocator");
    }

    public void startDocument() throws SAXException {
        printStream.println("startDocument");
    }

    public void endDocument() throws SAXException {
        printStream.println("endDocument");
    }

    public void startPrefixMapping(String prefix, String uri)
            throws SAXException {
        printStream.println("startPrefixMapping: " + prefix + ", " + uri);
    }

    public void endPrefixMapping(String prefix) throws SAXException {
        printStream.println("endPrefixMapping: " + prefix);
    }

    public void startElement(
            String namespaceURI, String localName, String qName, Attributes atts)
                throws SAXException {

        printStream.print("startElement: " + namespaceURI + ", "
                         + localName + ", " + qName);

        int n = atts.getLength();

        for (int i = 0; i < n; i++) {
            printStream.print(", " + atts.getQName(i) + "='" + atts.getValue(i) + "'" + atts.getType(i));
        }

        printStream.println("");
    }

    public void endElement(
            String namespaceURI, String localName, String qName)
                throws SAXException {
        printStream.println("endElement: " + namespaceURI + ", "
                           + localName + ", " + qName);
    }

    public void characters(char ch[], int start, int length)
            throws SAXException {

        String s = new String(ch, start, (length > 30)
                                         ? 30
                                         : length);

        if (length > 30) {
            printStream.println("characters: \"" + s + "\"...");
        } else {
            printStream.println("characters: \"" + s + "\"");
        }
    }

    public void ignorableWhitespace(char ch[], int start, int length)
            throws SAXException {
        printStream.println("ignorableWhitespace");
    }

    public void processingInstruction(String target, String data)
            throws SAXException {
        printStream.println("processingInstruction: " + target + ", "
                           + data);
    }

    public void skippedEntity(String name) throws SAXException {
        printStream.println("skippedEntity: " + name);
    }

    @Override
    public void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
        printStream.println("startDTD: " + name);
    }

    @Override
    public void endDTD() throws SAXException {
        printStream.println("endDTD");
    }

    @Override
    public void startEntity(final String name) throws SAXException {
        printStream.println("startEntity: " + name);
    }

    @Override
    public void endEntity(final String name) throws SAXException {
        printStream.println("startDTD: " + name);
    }

    @Override
    public void startCDATA() throws SAXException {
        printStream.println("startCDATA");
    }

    @Override
    public void endCDATA() throws SAXException {
        printStream.println("endCDATA");
    }

    @Override
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        printStream.println("comment: " + new String(ch).substring(start, start+length));
    }

    @Override
    public void notationDecl(final String name, final String publicId, final String systemId) throws SAXException {
        printStream.println("notationDecl:"+name);
    }

    @Override
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) throws SAXException {
        printStream.println("unparsedEntityDecl:"+name);
    }

    @Override
    public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException, IOException {
        printStream.println("resolveEntity:"+publicId);
        return null;
    }

    @Override
    public void elementDecl(final String name, final String model) throws SAXException {
        printStream.println("elementDecl:"+name+":"+model);
    }

    @Override
    public void attributeDecl(final String eName, final String aName, final String type, final String mode, final String value) throws SAXException {
        printStream.println("attributeDecl:"+eName+":"+aName+":"+type+":"+mode+":"+value);
    }

    @Override
    public void internalEntityDecl(final String name, final String value) throws SAXException {
        printStream.println("internalEntityDecl:"+name+":"+value);
    }

    @Override
    public void externalEntityDecl(final String name, final String publicId, final String systemId) throws SAXException {
        printStream.println("externalEntityDecl:"+name+":"+publicId+":"+systemId);
    }
}
