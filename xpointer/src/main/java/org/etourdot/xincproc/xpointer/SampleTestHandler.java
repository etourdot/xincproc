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

package org.etourdot.xincproc.xpointer;

import org.xml.sax.*;
import org.xml.sax.ext.DefaultHandler2;

import java.io.IOException;
import java.io.PrintStream;


public class SampleTestHandler extends DefaultHandler2 implements ContentHandler, DTDHandler {

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
        printStream.println("comment: " + new String(ch).substring(start, start + length));
    }

    @Override
    public void notationDecl(final String name, final String publicId, final String systemId) throws SAXException {
        printStream.println("notationDecl:" + name);
    }

    @Override
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) throws SAXException {
        printStream.println("unparsedEntityDecl:" + name);
    }

    @Override
    public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException, IOException {
        printStream.println("resolveEntity:" + publicId);
        return null;
    }

    @Override
    public void elementDecl(final String name, final String model) throws SAXException {
        printStream.println("elementDecl:" + name + ":" + model);
    }

    @Override
    public void attributeDecl(final String eName, final String aName, final String type, final String mode, final String value) throws SAXException {
        printStream.println("attributeDecl:" + eName + ":" + aName + ":" + type + ":" + mode + ":" + value);
    }

    @Override
    public void internalEntityDecl(final String name, final String value) throws SAXException {
        printStream.println("internalEntityDecl:" + name + ":" + value);
    }

    @Override
    public void externalEntityDecl(final String name, final String publicId, final String systemId) throws SAXException {
        printStream.println("externalEntityDecl:" + name + ":" + publicId + ":" + systemId);
    }

    @Override
    public InputSource getExternalSubset(final String name, final String baseURI) throws SAXException, IOException {
        printStream.println("getExternalSubset:" + name + ":" + baseURI);
        return super.getExternalSubset(name, baseURI);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public InputSource resolveEntity(final String name, final String publicId, final String baseURI, final String systemId) throws SAXException, IOException {
        printStream.println("resolveEntity:" + name + ":" + publicId + ":" + baseURI + ":" + systemId);
        return super.resolveEntity(name, publicId, baseURI, systemId);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
