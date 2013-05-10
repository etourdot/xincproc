<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="text" />

    <xsl:template match="testsuite">
        <xsl:call-template name="generateTOC"/>
        <xsl:for-each select="testcases">
            <xsl:variable name="caseName"><xsl:value-of select="concat('TestSuite', position(),'Test')"/></xsl:variable>
            <xsl:result-document href="{concat($caseName,'.java')}"><xsl:text>package org.etourdot.xincproc.xinclude.testsuite;
import org.etourdot.xincproc.xinclude.AbstractSuiteTest;
import org.concordion.integration.junit4.ConcordionRunner;
import org.concordion.api.extension.Extensions;
import org.concordion.ext.EmbedExtension;
import org.concordion.api.ExpectedToFail;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
@Extensions(EmbedExtension.class)</xsl:text>
<xsl:if test="@creator='NIST' or contains(@creator,'Harold')">
    <xsl:text>@ExpectedToFail</xsl:text>
</xsl:if><xsl:text>
public class </xsl:text><xsl:value-of select="$caseName"/><xsl:text> extends AbstractSuiteTest {</xsl:text>
<xsl:text>}&#xA;</xsl:text></xsl:result-document>
        </xsl:for-each>
    </xsl:template>

    <xsl:template name="generateTOC">
        <xsl:result-document href="TestSuiteTOCTest.java"><xsl:text>package org.etourdot.xincproc.xinclude.testsuite;
import org.concordion.integration.junit4.ConcordionRunner;
import org.concordion.api.extension.Extensions;
import org.concordion.ext.EmbedExtension;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class TestSuiteTOCTest {}&#xA;</xsl:text></xsl:result-document>
    </xsl:template>
</xsl:stylesheet>
