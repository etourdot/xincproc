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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.*;
import org.xml.sax.ext.DefaultHandler2;

import java.io.IOException;

/**
 * This handler extended org.xml.sax.ext.DefaultHandler2 is designed to debug
 * the nodes extract by xpointer engine and send to the Destination.
 */
class DebugHandler extends DefaultHandler2 {
    private static final Logger LOG = LoggerFactory.getLogger(DebugHandler.class);

    @Override
    public void setDocumentLocator(final Locator locator)
    {
        LOG.debug("setDocumentLocator: {}", locator);
    }

    @Override
    public void startDocument() throws SAXException
    {
        LOG.debug("startDocument");
    }

    @Override
    public void endDocument() throws SAXException
    {
        LOG.debug("endDocument");
    }

    @Override
    public void startPrefixMapping(final String prefix, final String uri)
            throws SAXException
    {
        LOG.debug("startPrefixMapping: {}, {}", prefix, uri);
    }

    @Override
    public void endPrefixMapping(final String prefix) throws SAXException
    {
        LOG.debug("endPrefixMapping: {}", prefix);
    }

    @Override
    public void startElement(final String namespaceURI, final String localName, final String qName,
                             final Attributes atts)
            throws SAXException
    {
        LOG.debug("startElement: {}, {}, {}", namespaceURI, localName, qName);

        final int n = atts.getLength();
        for (int i = 0; i < n; i++)
        {
            LOG.debug(", " + atts.getQName(i) + "='" + atts.getValue(i) + '\'' + atts.getType(i));
        }
    }

    @Override
    public void endElement(final String namespaceURI, final String localName, final String qName)
            throws SAXException
    {
        LOG.debug("endElement: {}, {}, {}", namespaceURI, localName, qName);
    }

    @Override
    public void characters(final char[] ch, final int start, final int length)
            throws SAXException
    {
        final String s = new String(ch, start, (30 < length) ? 30 : length);

        if (30 < length)
        {
            LOG.debug("characters: \"{}\"...", s);
        }
        else
        {
            LOG.debug("characters: \"{}\"", s);
        }
    }

    @Override
    public void ignorableWhitespace(final char[] ch, final int start, final int length)
            throws SAXException
    {
        LOG.debug("ignorableWhitespace");
    }

    @Override
    public void processingInstruction(final String target, final String data)
            throws SAXException
    {
        LOG.debug("processingInstruction: {}, {}", target, data);
    }

    @Override
    public void skippedEntity(final String name)
            throws SAXException
    {
        LOG.debug("skippedEntity: {}", name);
    }

    @Override
    public void startDTD(final String name, final String publicId, final String systemId)
            throws SAXException
    {
        LOG.debug("startDTD: {}", name);
    }

    @Override
    public void endDTD()
            throws SAXException
    {
        LOG.debug("endDTD");
    }

    @Override
    public void startEntity(final String name)
            throws SAXException
    {
        LOG.debug("startEntity: {}", name);
    }

    @Override
    public void endEntity(final String name) throws SAXException
    {
        LOG.debug("startDTD: {}", name);
    }

    @Override
    public void startCDATA() throws SAXException
    {
        LOG.debug("startCDATA");
    }

    @Override
    public void endCDATA() throws SAXException
    {
        LOG.debug("endCDATA");
    }

    @Override
    public void comment(final char[] ch, final int start, final int length)
            throws SAXException
    {
        LOG.debug("comment: {}", new String(ch).substring(start, start + length));
    }

    @Override
    public void notationDecl(final String name, final String publicId, final String systemId)
            throws SAXException
    {
        LOG.debug("notationDecl: {}", name);
    }

    @Override
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId,
                                   final String notationName)
            throws SAXException
    {
        LOG.debug("unparsedEntityDecl: {}", name);
    }

    @Override
    public InputSource resolveEntity(final String publicId, final String systemId)
            throws SAXException, IOException
    {
        LOG.debug("resolveEntity: {}", publicId);
        return null;
    }

    @Override
    public void elementDecl(final String name, final String model)
            throws SAXException
    {
        LOG.debug("elementDecl: {}, {}", name, model);
    }

    @Override
    public void attributeDecl(final String eName, final String aName, final String type, final String mode,
                              final String value)
            throws SAXException
    {
        LOG.debug("attributeDecl: {}, {}, {}, {}, {}", eName, aName, type, mode, value);
    }

    @Override
    public void internalEntityDecl(final String name, final String value) throws SAXException
    {
        LOG.debug("internalEntityDecl: {}, {}", name, value);
    }

    @Override
    public void externalEntityDecl(final String name, final String publicId, final String systemId)
            throws SAXException
    {
        LOG.debug("externalEntityDecl: {}, {}, {}", name, publicId, systemId);
    }

    @Override
    public InputSource getExternalSubset(final String name, final String baseURI)
            throws SAXException, IOException
    {
        LOG.debug("getExternalSubset: {}, {}", name, baseURI);
        return null;
    }

    @Override
    public InputSource resolveEntity(final String name, final String publicId, final String baseURI,
                                     final String systemId)
            throws SAXException, IOException
    {
        LOG.debug("resolveEntity: {}", name, publicId, baseURI, systemId);
        return null;
    }
}
