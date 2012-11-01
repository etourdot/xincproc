package org.etourdot.xincproc.xpointer;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.io.Files;
import org.etourdot.xincproc.xpointer.model.ElementScheme;
import org.etourdot.xincproc.xpointer.model.Pointer;
import org.etourdot.xincproc.xpointer.model.ShortHand;
import org.etourdot.xincproc.xpointer.model.XPathScheme;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 22/09/12
 * Time: 15:38
 */
public class XPointerAnalyserTest {
    @Test
    public void testValidPointers() throws Exception {
        File validPointersFile = new File(this.getClass().getClassLoader().getResource("valid_pointers.txt").getFile());
        List<String> lines = Files.readLines(validPointersFile, Charsets.UTF_8);
        for (String line : lines) {
            try {
                Pointer pointer = XPointerAnalyser.analyse(line);
                Assert.assertNotNull(line, pointer);
            } catch (Exception e) {
                Assert.fail(line);
            }
        }
    }

    @Test
    public void testInvalidPointers() throws Exception {
        File validPointersFile = new File(this.getClass().getClassLoader().getResource("invalid_pointers.txt").getFile());
        List<String> lines = Files.readLines(validPointersFile, Charsets.UTF_8);
        for (String line : lines) {
            try {
                Pointer pointer = XPointerAnalyser.analyse(line);
                if (pointer != null) {
                    Assert.fail(line);
                }
            } catch (Exception e) {
            }
        }
    }

    @Test
    public void testGetQueryFromElementScheme() throws Exception {
        ElementScheme elementScheme = new ElementScheme("toto", null);
        String query = XPointerAnalyser.getQueryFromElementScheme(elementScheme);
        Assert.assertEquals("for $d in //*[@id='toto']|fn:id('toto') return $d", query);
        elementScheme = new ElementScheme(null, "/1/2/5");
        query = XPointerAnalyser.getQueryFromElementScheme(elementScheme);
        Assert.assertEquals("for $d in /*[1]/*[2]/*[5] return $d", query);
        elementScheme = new ElementScheme("toto", "/1/2/5");
        query = XPointerAnalyser.getQueryFromElementScheme(elementScheme);
        Assert.assertEquals("for $d in //*[@id='toto']|fn:id('toto')/*[1]/*[2]/*[5] return $d", query);
    }

    @Test
    public void testGetQueryFromShortHand() throws Exception {
        ShortHand shortHand = new ShortHand("toto");
        String query = XPointerAnalyser.getQueryFromShortHand(shortHand);
        Assert.assertEquals("for $d in //*[@id='toto']|fn:id('toto') return $d", query);
    }

    @Test
    public void testGetQueryFromXPathScheme() throws Exception {
        XPathScheme xPathScheme = new XPathScheme("//ftnote[@id='fn6']/para[1])");
        String query = XPointerAnalyser.getQueryFromXPathScheme(xPathScheme);
        Assert.assertEquals("for $d in //ftnote[@id='fn6']/para[1]) return $d", query);
    }

    @Test
    public void testGetQueryFromXPointerScheme() throws Exception {
        XPathScheme xPathScheme = new XPathScheme("//ftnote[@id='fn6']/para[1])");
        String query = XPointerAnalyser.getQueryFromXPathScheme(xPathScheme);
        Assert.assertEquals("for $d in //ftnote[@id='fn6']/para[1]) return $d", query);
    }
}
