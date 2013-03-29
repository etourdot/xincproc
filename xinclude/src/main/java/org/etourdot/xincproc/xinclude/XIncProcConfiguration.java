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

package org.etourdot.xincproc.xinclude;

import net.sf.saxon.s9api.Processor;
import org.etourdot.xincproc.xpointer.XPointerEngine;
import javax.xml.transform.ErrorListener;

/**
 * XIncProcConfiguration.
 */
public class XIncProcConfiguration {
    /**
     * The constant ALLOW_FIXUP_BASE_URIS.
     */
    public static final String ALLOW_FIXUP_BASE_URIS
            = "http://etourdot.org/xml/features/xinclude/fixup-base-uris";
    /**
     * The constant ALLOW_FIXUP_LANGUAGE.
     */
    public static final String ALLOW_FIXUP_LANGUAGE
            = "http://etourdot.org/xml/features/xinclude/fixup-language";

    /**
     * The XInclude version supported by the XIncProcEngine
     */
    public static final String SUPPORTED_XINCLUDE_VERSION = "1.0";


    private XIncProcConfiguration()
    {
        this.processor = new Processor(false);
    }

    /**
     * New XIncProcConfiguration.
     *
     * @return a new XIncProcConfiguration
     */
    public static XIncProcConfiguration newXIncProcConfiguration()
    {
        return new XIncProcConfiguration();
    }

    /**
     * New XPointerEngine
     *
     * @return the x pointer engine
     */
    public XPointerEngine newXPointerEngine()
    {
        return new XPointerEngine(this.processor);
    }

    /**
     * Sets configuration property.
     *
     * @param name the name
     * @param value the value
     */
    public void setConfigurationProperty(final String name, final Object value)
    {
        if (XIncProcConfiguration.ALLOW_FIXUP_BASE_URIS.equals(name))
        {
            if (value instanceof Boolean)
            {
                this.baseUrisFixup = (Boolean) value;
            }
            else if (value instanceof String)
            {
                this.baseUrisFixup = Boolean.valueOf((String) value);
            }
        }
        if (XIncProcConfiguration.ALLOW_FIXUP_LANGUAGE.equals(name))
        {
            if (value instanceof Boolean)
            {
                this.languageFixup = (Boolean) value;
            }
            else if (value instanceof String)
            {
                this.languageFixup = Boolean.valueOf((String) value);
            }
        }
    }

    /**
     * Sets an error listener.
     *
     * @param errorListener the error listener
     */
    public void setErrorListener(final ErrorListener errorListener)
    {
        this.processor.getUnderlyingConfiguration().setErrorListener(errorListener);
    }

    /**
     * Is base uris fixup.
     *
     * @return the boolean
     */
    public boolean isBaseUrisFixup()
    {
        return this.baseUrisFixup;
    }

    /**
     * Is language fixup.
     *
     * @return the boolean
     */
    public boolean isLanguageFixup()
    {
        return this.languageFixup;
    }

    /**
     * Gets processor.
     *
     * @return the processor
     */
    public Processor getProcessor()
    {
        return this.processor;
    }

    /**
     * Gets supported version by the engine
     *
     * @return version supported (currently only 1.0)
     */
    public String getSupportedVersion()
    {
        return SUPPORTED_XINCLUDE_VERSION;
    }

    private boolean baseUrisFixup = true;
    private boolean languageFixup = true;
    private final Processor processor;
}
