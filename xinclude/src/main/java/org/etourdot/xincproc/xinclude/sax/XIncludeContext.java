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

package org.etourdot.xincproc.xinclude.sax;

import com.google.common.base.Optional;
import org.etourdot.xincproc.xinclude.XIncProcConfiguration;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.NamespaceSupport;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * This class represents the context in which the XIncProcXIncludeFilter
 * is executed.
 * It stores some informations abouts base uri, language, stack of xinclude or fallback
 * elements ...
 */
public class XIncludeContext
{
    /**
     * Instantiates a new XIncludeContext.
     *
     * @param configuration the configuration to which context is attached
     */
    public XIncludeContext(final XIncProcConfiguration configuration)
    {
        this.configuration = configuration;
    }

    /**
     * Create a new context by deep copy of an existant one.
     *
     * @param contextToCopy the context to copy
     * @return the XIncludeContext
     */
    public static XIncludeContext newContext(final XIncludeContext contextToCopy)
    {
        final XIncludeContext newContext = new XIncludeContext(contextToCopy.configuration);
        newContext.currentBaseURI = contextToCopy.currentBaseURI;
        newContext.basesURIDeque.addAll(contextToCopy.basesURIDeque);
        newContext.language = contextToCopy.language;
        newContext.xincludeDeque.addAll(contextToCopy.xincludeDeque);
        newContext.docType = DocType.copy(contextToCopy.docType);
        return newContext;
    }

    /**
     * Gets configuration.
     *
     * @return the configuration to which context is attached
     */
    public XIncProcConfiguration getConfiguration()
    {
        return this.configuration;
    }

    /**
     * Update context with element attributes.
     *
     * @param attributes the attributes
     * @throws XIncludeFatalException if attribute xml:base is invalid
     */
    public void updateContextWithElementAttributes(final Attributes attributes)
            throws XIncludeFatalException
    {
        extractCurrentBaseURI(attributes);
        if (this.currentBaseURI.isPresent())
        {
            addBaseURIPath(this.currentBaseURI.get());
        }
    }

    /**
     * Update context when end element.
     */
    public void updateContextWhenEndElement()
    {
        if (this.currentBaseURI.isPresent())
        {
            removeBaseURIPath(this.currentBaseURI.get());
            this.currentBaseURI = Optional.absent();
        }
    }

    /**
     * Is language fixup.
     *
     * @return the boolean
     */
    public boolean isLanguageFixup()
    {
        return this.configuration.isLanguageFixup();
    }

    /**
     * Is base fixup.
     *
     * @return the boolean
     */
    public boolean isBaseFixup()
    {
        return this.configuration.isBaseUrisFixup();
    }

    /**
     * Gets source uRI.
     *
     * @return the source uRI
     */
    public URI getSourceURI()
    {
        return this.sourceURI;
    }

    /**
     * Sets source uRI.
     *
     * @param sourceURI the source uRI
     */
    public void setSourceURI(final URI sourceURI)
    {
        this.sourceURI = sourceURI;
    }

    /**
     * Gets current exception.
     *
     * @return the current exception
     */
    public Exception getCurrentException()
    {
        return this.currentException;
    }

    /**
     * Sets current exception.
     *
     * @param currentException the current exception
     */
    public void setCurrentException(final Exception currentException)
    {
        this.currentException = currentException;
    }

    /**
     * Add in inclusion chain.
     *
     * @param path the path
     * @param pointer the pointer
     * @throws XIncludeFatalException the x include fatal exception
     */
    public void addInInclusionChain(final URI path, final String pointer)
            throws XIncludeFatalException
    {
        final String xincludePath = path.toASCIIString() + ((null != pointer) ? ('#' + pointer) : "");
        if (this.xincludeDeque.contains(xincludePath))
        {
            throw new XIncludeFatalException("Inclusion Loop on path: " + xincludePath);
        }
        this.xincludeDeque.addLast(xincludePath);
    }

    /**
     * Remove from inclusion chain.
     */
    public void removeFromInclusionChain()
    {
        this.xincludeDeque.pollLast();
    }

    /**
     * Gets initial base uRI.
     *
     * @return the initial base uRI
     */
    public URI getInitialBaseURI()
    {
        return this.initialBaseURI;
    }

    /**
     * Sets initial base uRI.
     *
     * @param initialBaseURI the initial base uRI
     */
    public void setInitialBaseURI(final URI initialBaseURI)
    {
        this.initialBaseURI = initialBaseURI;
        this.currentBaseURI = Optional.absent();
        this.basesURIDeque.clear();
    }

    /**
     * Add base uRI path.
     *
     * @param basePath the base path
     */
    public void addBaseURIPath(final URI basePath)
    {
        this.basesURIDeque.addLast(basePath);
    }

    /**
     * Gets base uRI paths.
     *
     * @return the base uRI paths
     */
    public List<URI> getBaseURIPaths()
    {
        return new ArrayList<URI>(this.basesURIDeque);
    }

    /**
     * Gets current base uRI.
     *
     * @return the current base uRI
     */
    public URI getCurrentBaseURI()
    {
        return this.currentBaseURI.orNull();
    }

    /**
     * Gets href uRI.
     *
     * @return the href uRI
     */
    public URI getHrefURI()
    {
        return this.hrefURI;
    }

    /**
     * Sets href uRI.
     *
     * @param hrefURI the href uRI
     */
    public void setHrefURI(final URI hrefURI)
    {
        this.hrefURI = hrefURI;
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public String getLanguage()
    {
        return this.language;
    }

    /**
     * Sets language.
     *
     * @param language the language
     */
    public void setLanguage(final String language)
    {
        this.language = language;
    }

    @Override
    public String toString()
    {
        return "sourceURI:" + this.sourceURI + "\n,currentBase:" + this.currentBaseURI.get() + ",hrefURI:" + this.hrefURI
                + "\n,lang:" + this.language;
    }

    private void extractCurrentBaseURI(final Attributes attributes)
            throws XIncludeFatalException
    {
        final int baseAttIdx = attributes.getIndex(NamespaceSupport.XMLNS,
                XIncludeConstants.XMLBASE_QNAME.getLocalPart());
        final URI foundURI;
        if (0 <= baseAttIdx)
        {
            try
            {
                foundURI = new URI(attributes.getValue(baseAttIdx));
            }
            catch (final URISyntaxException ignored)
            {
                throw new XIncludeFatalException("Invalid base URI");
            }
        }
        else
        {
            foundURI = null;
        }
        this.currentBaseURI = Optional.fromNullable(foundURI);
    }

    void removeBaseURIPath(final URI basePath)
    {
        this.basesURIDeque.removeLastOccurrence(basePath);
    }

    String getDocType()
    {
        return this.docType.getDocTypeValue();
    }

    void setDocType(final String name, final String publicId, final String systemId)
    {
        this.docType.setDoctype(name).setPublicId(publicId).setSystemId(systemId);
    }

    void addAttributeDoctype(final String eName, final String aName, final String type, final String mode,
                 final String value)
    {
        this.docType.addAttribute(eName, aName, type, mode, value);
    }

    void addElementDoctype(final String name, final String model)
    {
        this.docType.addElement(name, model);
    }

    void addExternalEntityDoctype(final String name, final String publicId, final String systemId)
    {
        this.docType.addExternalEntity(name, publicId, systemId);
    }

    void addInternalEntityDoctype(final String name, final String value)
    {
        this.docType.addInternalEntity(name, value);
    }

    void  addUnparsedEntityDoctype(final String name, final String publicId, final String systemId,
                            final String notationName)
            throws XIncludeFatalException
    {
        this.docType.addUnparsedEntity(name, publicId, systemId, notationName);
    }

    private final XIncProcConfiguration configuration;
    private final Deque<URI> basesURIDeque = new ArrayDeque<URI>();
    private final Deque<String> xincludeDeque = new ArrayDeque<String>();
    private URI initialBaseURI;
    private Optional<URI> currentBaseURI = Optional.absent();
    private String language;
    private URI sourceURI;
    private URI hrefURI;
    private Exception currentException;
    private DocType docType = new DocType();
}
