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
 * <code>XIncProcConfiguration</code> class holds configuration options for {@link XIncProcEngine}.
 */
public final class XIncProcConfiguration
{
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
     * New {@link XPointerEngine}.
     * <p/>
     * <p>The best way to get a new xpointer engine.
     * Usage of this method should be prefered to direct instanciation</p>
     *
     * @return the new XPointer engine
     */
    public XPointerEngine newXPointerEngine()
    {
        return new XPointerEngine(this.processor);
    }

    /**
     * new {@link XIncProcEngine}.
     * <p/>
     * <p>This is the only way to get instance of XIncProcEngine to use custom Saxon Processor
     * via the XIncProcConfiguration</p>
     *
     * @return a new XIncProcEngine
     */
    public XIncProcEngine newXIncProcEngine()
    {
        return new XIncProcEngine(this);
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
     * Sets an {@link ErrorListener}.
     * <p/>
     * The {@link ErrorListener} set in the configuration will be used
     * by the {@link XIncProcEngine}
     *
     * @param errorListener the error listener
     */
    public void setErrorListener(final ErrorListener errorListener)
    {
        this.processor.getUnderlyingConfiguration().setErrorListener(errorListener);
    }

    /**
     * Is base uris fixup sets in configuration ?
     * <p>Defaut value is <b>true</b></p>
     *
     * @return the boolean
     */
    public boolean isBaseUrisFixup()
    {
        return this.baseUrisFixup;
    }

    /**
     * Is language fixupsets in configuration ?
     * <p>Defaut value is <b>true</b></p>
     *
     * @return the boolean
     */
    public boolean isLanguageFixup()
    {
        return this.languageFixup;
    }

    /**
     * Gets Saxon {@link net.sf.saxon.s9api.Processor}.
     * <p/>
     * <p>A simple Saxon HE processor is created by default for the treatment.</p>
     * <p>Another Saxon processor could be set to replace the default one.</p>
     *
     * @return the Saxon processor
     */
    public Processor getProcessor()
    {
        return this.processor;
    }

    /**
     * Set Saxon {@link net.sf.saxon.s9api.Processor}.
     * <p/>
     * <p>The default Saxon HE processor could be replace here.</p>
     *
     * @param processor a Saxon Processor
     */
    public void setProcessor(final Processor processor)
    {
        this.processor = processor;
    }

    /**
     * Gets supported XInclude version by the engine
     *
     * @return version supported (currently only 1.0)
     */
    public String getSupportedVersion()
    {
        return SUPPORTED_XINCLUDE_VERSION;
    }

    private boolean baseUrisFixup = true;
    private boolean languageFixup = true;
    private Processor processor;
}
