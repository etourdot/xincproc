<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:concordion="http://www.concordion.org/2007/concordion">

    <xsl:output method="html" />

    <xsl:template match="testsuite">
        <!--xsl:call-template name="generateTOC"/-->
        <html xmlns:concordion="http://www.concordion.org/2007/concordion" xmlns:ext="urn:concordion-extensions:2010">
            <body>
        <xsl:for-each select="testcases">
            <!--xsl:variable name="caseName"><xsl:value-of select="concat('TestSuite', position())"/></xsl:variable>
            <xsl:result-document href="{concat($caseName,'.html')}"-->
                <h1><xsl:value-of select="@creator"/></h1>
                <table>
                    <tr>
                        <th width="150px"><p>Id</p><p>Contributor</p><p>Date</p></th>
                        <th width="300px">Description</th>
                        <th width="100px">Result</th>
                        <th>Error message</th>
                    </tr>
                    <xsl:for-each select="testcase">
                        <tr concordion:execute="#result = execute(#type,#inHRef,#outHRef)">
                            <td><p><b><xsl:value-of select="@id"/></b></p>
                                <p><xsl:value-of select="contributor"/></p>
                                <p><xsl:value-of select="date"/></p>
                            </td>
                            <td><xsl:value-of select="description"/>
                            <div style="display:none;"><span concordion:set="#type"><xsl:value-of select="@type"/></span>
                            <span concordion:set="#inHRef"><xsl:value-of select="concat(../@basedir,'/',@href)"/></span>
                            <span concordion:set="#outHRef"><xsl:value-of select="concat(../@basedir,'/',output)"/></span></div>
                            </td>
                            <td concordion:assertEquals="#result.result"><xsl:value-of select="@type"/></td>
                            <td><span ext:embed="#result.exception"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            <!--/xsl:result-document-->
        </xsl:for-each>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="generateTOC">
        <xsl:result-document href="TestSuiteTOC.html">
            <html xmlns:concordion="http://www.concordion.org/2007/concordion">
            <body>
            <h1>Conformance Test suite</h1>
            <p>XIncProc conformance is tested against the official <a href="http://www.w3.org/XML/Test/XInclude/">Xinclude Test Suite</a>.</p>
            <p>All tests Xinclude Conformance Test Suite, 2006-09-27, were passed and results are availables here:</p>
            <ul>
                <xsl:for-each select="testcases">
                    <li><a href="{concat('TestSuite',position(),'.html')}"><xsl:value-of select="concat(@creator,' test suite')"/></a></li>
                </xsl:for-each>
            </ul>
            </body>
            </html>
        </xsl:result-document>
    </xsl:template>
</xsl:stylesheet>
