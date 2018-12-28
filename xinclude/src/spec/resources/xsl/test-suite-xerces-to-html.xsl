<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:concordion="http://www.concordion.org/2007/concordion">

    <xsl:output method="html"/>

    <xsl:template match="testsuite">
        <html xmlns:concordion="http://www.concordion.org/2007/concordion" xmlns:ext="urn:concordion-extensions:2010">
            <body>
                <h1>Conformance Test suite with Xerces</h1>
                <p>All tests Xinclude Conformance Test Suite, 2006-09-27, were passed and results are availables here:
                </p>
                <p>This tests are for comparison purpose. Source code is following:</p>
                <pre>
                    Processor processor = = new Processor(false);
                    processor.getUnderlyingConfiguration().setSchemaValidationMode(Validation.LAX);
                    processor.getUnderlyingConfiguration().setXIncludeAware(true);
                    DocumentBuilder documentBuilder = processor.newDocumentBuilder();
                    XdmNode node = documentBuilder.build(new File(urlInput.getPath()));
                    Serializer serializer = processor.newSerializer(new StringWriter());
                    String output = serializer.serializeNodeToString(node);
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
                            <tr concordion:execute="#result = executeWithSaxon(#type,#inHRef,#outHRef)">
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
