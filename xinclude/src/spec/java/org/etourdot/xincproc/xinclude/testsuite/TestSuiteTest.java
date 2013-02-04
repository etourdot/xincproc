package org.etourdot.xincproc.xinclude.testsuite;

import org.concordion.api.ExpectedToFail;
import org.concordion.api.extension.Extensions;
import org.concordion.ext.EmbedExtension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.etourdot.xincproc.xinclude.AbstractSuiteTest;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
@Extensions(EmbedExtension.class)
@ExpectedToFail
public class TestSuiteTest extends AbstractSuiteTest {}
