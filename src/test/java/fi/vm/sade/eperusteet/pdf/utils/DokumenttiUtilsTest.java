package fi.vm.sade.eperusteet.pdf.utils;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DokumenttiUtilsTest {

    @Test
    void isValidHttpUrlAcceptsOnlyCompleteHttpAndHttps() {
        assertTrue(DokumenttiUtils.isValidHttpUrl("https://example.com"));
        assertTrue(DokumenttiUtils.isValidHttpUrl(" http://example.com/path "));
        assertFalse(DokumenttiUtils.isValidHttpUrl("http://"));
        assertFalse(DokumenttiUtils.isValidHttpUrl("https://"));
        assertFalse(DokumenttiUtils.isValidHttpUrl("http:///"));
        assertFalse(DokumenttiUtils.isValidHttpUrl("ftp://example.com"));
        assertFalse(DokumenttiUtils.isValidHttpUrl("mailto:info@example.com"));
        assertFalse(DokumenttiUtils.isValidHttpUrl("#osio"));
    }

    @Test
    void sanitizeDocumentLinksRemovesInvalidHttpHref() throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = doc.createElement("body");
        doc.appendChild(root);

        Element invalid = doc.createElement("a");
        invalid.setAttribute("href", "http://");
        invalid.setTextContent("rikki");
        root.appendChild(invalid);

        Element valid = doc.createElement("a");
        valid.setAttribute("href", "https://example.com");
        valid.setTextContent("ok");
        root.appendChild(valid);

        Element internal = doc.createElement("a");
        internal.setAttribute("href", "#osio");
        internal.setTextContent("osio");
        root.appendChild(internal);

        DokumenttiUtils.sanitizeDocumentLinks(doc);

        assertFalse(invalid.hasAttribute("href"));
        assertEquals("rikki", invalid.getTextContent());
        assertEquals("https://example.com", valid.getAttribute("href"));
        assertEquals("#osio", internal.getAttribute("href"));
    }
}
