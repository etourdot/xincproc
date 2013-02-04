package org.etourdot.xincproc.xinclude;

import com.google.common.base.Strings;
import com.google.common.io.Resources;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeResourceException;
import org.xml.sax.Attributes;

import javax.xml.namespace.QName;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: etourdot
 * Date: 29/10/11
 * Time: 12:51
 */
public final class XIncProcUtils {
    /**
     * Check xinclude attributes
     * see http://www.w3.org/TR/2006/REC-xinclude-20061115/#include_element
     *
     * @param startElement xinclude element to test attributes
     * @throws org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException
     * @throws org.etourdot.xincproc.xinclude.exceptions.XIncludeResourceException
     */
    public static void checkXInclude(final StartElement startElement) throws XIncludeFatalException,
            XIncludeResourceException
    {
        final Attribute hrefAtt = startElement.getAttributeByName(XIncProcConfiguration.ATT_HREF);
        final String href = (hrefAtt==null)? null : hrefAtt.getValue();
        final Attribute parseAtt = startElement.getAttributeByName(XIncProcConfiguration.ATT_PARSE);
        final String parse = (parseAtt==null)? XIncProcConfiguration.XML : parseAtt.getValue();
        final Attribute xpointerAtt = startElement.getAttributeByName(XIncProcConfiguration.ATT_XPOINTER);
        final String xpointer = (xpointerAtt==null)? null : xpointerAtt.getValue();
        final Attribute encodingAtt = startElement.getAttributeByName(XIncProcConfiguration.ATT_XPOINTER);
        final String encoding = (encodingAtt==null)? null : encodingAtt.getValue();
        final Attribute acceptAtt = startElement.getAttributeByName(XIncProcConfiguration.ATT_ACCEPT);
        final String accept = (acceptAtt==null)? null : acceptAtt.getValue();
        final Attribute acceptLanguageAtt = startElement.getAttributeByName(XIncProcConfiguration.ATT_ACCEPT_LANGUAGE);
        final String acceptLanguage = (acceptLanguageAtt==null)? null : acceptLanguageAtt.getValue();
    }

    /**
     * Check xinclude attributes
     * see http://www.w3.org/TR/2006/REC-xinclude-20061115/#include_element
     *  
     * @param atts
     * @throws XIncludeFatalException
     * @throws XIncludeResourceException
     */
    public static void checkXInclude(final Attributes atts) throws XIncludeFatalException, XIncludeResourceException
    {
        final String href = atts.getValue("", XIncProcConfiguration.ATT_HREF.getLocalPart());
        final String parseAtt = atts.getValue("", XIncProcConfiguration.ATT_PARSE.getLocalPart());
        final String parse = (parseAtt == null) ? XIncProcConfiguration.XML : parseAtt;
        final String xpointer = atts.getValue("", XIncProcConfiguration.ATT_XPOINTER.getLocalPart());
        final String encoding = atts.getValue("", XIncProcConfiguration.ATT_ENCODING.getLocalPart());
        final String accept = atts.getValue("", XIncProcConfiguration.ATT_ACCEPT.getLocalPart());
        final String acceptLanguage = atts.getValue("", XIncProcConfiguration.ATT_ACCEPT_LANGUAGE.getLocalPart());
    }

     /**
     * Return if element is Xinclude or not
     * @param qname
     * @return
     */
    public static boolean isXInclude(final QName qname)
    {
        if (XIncProcConfiguration.XINCLUDE_QNAME.getLocalPart().equals(qname.getLocalPart()))
        {
            if (Strings.isNullOrEmpty(qname.getNamespaceURI()) ||
                XIncProcConfiguration.XINCLUDE_QNAME.getNamespaceURI().equals(qname.getNamespaceURI()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Return if element is Fallback or not
     * @param qname
     * @return
     */
    public static boolean isFallback(final QName qname)
    {
        if (XIncProcConfiguration.FALLBACK_QNAME.getLocalPart().equals(qname.getLocalPart()))
        {
            if (Strings.isNullOrEmpty(qname.getNamespaceURI()) ||
                XIncProcConfiguration.FALLBACK_QNAME.getNamespaceURI().equals(qname.getNamespaceURI()))
            {
                return true;
            }
        }
        return false;
    }

    public static URI computeBase(final List<URI> uris)
    {
        final Iterator<URI> it = uris.iterator();
        URI resultURI = it.next();
        while (it.hasNext())
        {
            final URI uri = it.next();
            resultURI = resultURI.resolve(uri);
        }
        return  resultURI;
    }

    public static URI resolveBase(final URI baseURI, final List<URI> uris) throws XIncludeFatalException
    {
        final URI computedUri = computeBase(uris);
        final URI resolvedUri = baseURI.resolve(computedUri);
        if (resolvedUri.compareTo(baseURI)==0)
        {
            throw new XIncludeFatalException("Inclusion loop error");
        }
        return resolvedUri;
    }

    private static URI computeBase(final Stack<URI> stack)
    {
        assert !stack.isEmpty();
        final Iterator<URI> it = stack.iterator();
        URI resultURI = it.next();
        while (it.hasNext())
        {
            final URI uri = it.next();
            resultURI = resultURI.resolve(uri);
        }
        return  resultURI;
    }

    public static URI resolveBase(final URI baseURI, final Stack<URI> stack) throws XIncludeFatalException
    {
        final URI computedUri = computeBase(stack);
        final URI resolvedUri = baseURI.resolve(computedUri);
        if (resolvedUri.compareTo(baseURI)==0)
        {
            throw new XIncludeFatalException("Inclusion loop error");
        }
        return resolvedUri;
    }

    /**
     * Read a text source from the URI
     * @param source
     * @param encoding
     * @return
     * @throws XIncludeFatalException
     * @throws XIncludeResourceException
     */
    public static String readTextURI(final URI source, final String encoding)
            throws XIncludeFatalException, XIncludeResourceException
    {
        try
        {
            final URL url = source.toURL();
            final URLConnection urlConnection = url.openConnection();
            final Charset charset;
            if (encoding == null)
            {
                BOMInputStream bomIn = new BOMInputStream(urlConnection.getInputStream(), ByteOrderMark.UTF_8,
                        ByteOrderMark.UTF_16LE, ByteOrderMark.UTF_16BE,
                        ByteOrderMark.UTF_32LE, ByteOrderMark.UTF_32BE);
                final String guessCharset = bomIn.getBOMCharsetName();
                if (guessCharset == null)
                {
                    charset = Charset.defaultCharset();
                }
                else
                {
                    charset = Charset.forName(guessCharset);
                }
            }
            else
            {
                charset = Charset.forName(encoding);
            }
            return Resources.toString(url, charset);
        }
        catch (MalformedURLException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
        catch (UnsupportedEncodingException e)
        {
            throw new XIncludeFatalException(e.getMessage());
        }
        catch (IOException e)
        {
            throw new XIncludeResourceException(e.getMessage());
        }
    }
}
