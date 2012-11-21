/*
 * Copyright (C) 2011 Emmanuel Tourdot
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * $Id$
 */
package org.etourdot.xincproc.xinclude;

import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * @author Emmanuel Tourdot
 */
public interface XIncProcEngineHandler
{
    void parse(final URI baseURI, final OutputStream output) throws XIncludeFatalException;

    void parse(final Source source, final Result result) throws XIncludeFatalException;

    void parse(final InputStream source, final String systemId, final OutputStream result) throws XIncludeFatalException;

}
