<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:concordion="http://www.concordion.org/2007/concordion">

    <xsl:output method="html"/>

    <xsl:template match="testsuite">
        <html xmlns:concordion="http://www.concordion.org/2007/concordion" xmlns:ext="urn:concordion-extensions:2010">
            <body>
                <h1>Conformance Test suite</h1>
                <p>XIncProc conformance is tested against the official<a href="http://www.w3.org/XML/Test/XInclude/">
                    Xinclude Test Suite</a>.
                </p>
                <p>All tests Xinclude Conformance Test Suite, 2006-09-27, were passed and results are availables here:
                </p>
                <p>Source code is following:</p>
                <pre>
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    FileInputStream source = new FileInputStream(urlInput.getPath());
                    XIncProcEngine.getUnderlyingConfiguration().setConfigurationProperty(XIncProcConfiguration.ALLOW_FIXUP_BASE_URIS, true);
                    XIncProcEngine.getUnderlyingConfiguration().setConfigurationProperty(XIncProcConfiguration.ALLOW_FIXUP_LANGUAGE, true);
                    XIncProcEngine.parse(source, urlInput.toExternalForm(), output);
                </pre>
                <table>
                    <tr>
                        <th width="150px">
                            <p>Id</p>
                            <p>Contributor</p>
                            <p>Date</p>
                        </th>
                        <th width="300px">Description</th>
                        <th width="100px">Result</th>
                        <th>Error message</th>
                        <th>Execution time</th>
                    </tr>
                    <xsl:for-each select="testcases">
                        <tr>
                            <td colspan="5">
                            <h1><xsl:value-of select="@creator"/></h1>
                            </td>
                        </tr>
                        <xsl:for-each select="testcase">
                            <tr concordion:execute="#result = execute(#type,#inHRef,#outHRef)">
                                <td>
                                    <p>
                                        <b>
                                            <xsl:value-of select="@id"/>
                                        </b>
                                    </p>
                                    <p>
                                        <xsl:value-of select="contributor"/>
                                    </p>
                                    <p>
                                        <xsl:value-of select="date"/>
                                    </p>
                                </td>
                                <td>
                                    <xsl:value-of select="description"/>
                                    <div style="display:none;">
                                        <span concordion:set="#type">
                                            <xsl:value-of select="@type"/>
                                        </span>
                                        <span concordion:set="#inHRef">
                                            <xsl:value-of select="concat(../@basedir,'/',@href)"/>
                                        </span>
                                        <span concordion:set="#outHRef">
                                            <xsl:value-of select="concat(../@basedir,'/',output)"/>
                                        </span>
                                    </div>
                                </td>
                                <td concordion:assertEquals="#result.result">
                                    <xsl:value-of select="@type"/>
                                </td>
                                <td>
                                    <span ext:embed="#result.exception"/>
                                </td>
                                <td>
                                    <span ext:embed="#result.time"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
