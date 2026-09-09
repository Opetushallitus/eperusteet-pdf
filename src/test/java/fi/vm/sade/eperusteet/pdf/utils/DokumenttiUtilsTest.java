package fi.vm.sade.eperusteet.pdf.utils;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Test
    void addTekstiKeepsParagraphContentWithoutXhtmlNamespace() throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element body = doc.createElement("body");
        doc.appendChild(body);

        DokumenttiUtils.addTeksti(doc, "<p>asdfasdfasfasdf</p>", "div", body);

        assertEquals(1, body.getChildNodes().getLength());
        Node div = body.getFirstChild();
        assertEquals("div", div.getNodeName());
        assertTrue(div.getNamespaceURI() == null || div.getNamespaceURI().isEmpty());
        assertTrue(div.getTextContent().contains("asdfasdfasfasdf"));

        Node paragraph = firstElementChild(div);
        assertNotNull(paragraph);
        assertEquals("p", paragraph.getNodeName());
        assertTrue(paragraph.getNamespaceURI() == null || paragraph.getNamespaceURI().isEmpty());

        String xml = serializeXml(doc);
        assertFalse(xml.contains("http://www.w3.org/1999/xhtml"));
        assertTrue(xml.contains("asdfasdfasfasdf"));
    }

    @Test
    void addTekstiDoesNotMoveHeadMetadataIntoXhtmlNamespace() throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element html = doc.createElement("html");
        doc.appendChild(html);
        Element head = doc.createElement("head");
        html.appendChild(head);
        Element body = doc.createElement("body");
        html.appendChild(body);

        Element title = doc.createElement("title");
        title.setTextContent("Perusteen nimi");
        head.appendChild(title);

        Element peruste = doc.createElement("peruste");
        peruste.setTextContent("Perusteen nimi");
        head.appendChild(peruste);

        Element description = doc.createElement("description");
        head.appendChild(description);

        DokumenttiUtils.addTeksti(doc, "<p>kvliite</p>", "div", description);
        DokumenttiUtils.addTeksti(doc, "<p>asdfasdfasfasdf</p>", "div", body);

        assertTrue(head.getNamespaceURI() == null || head.getNamespaceURI().isEmpty());
        assertTrue(title.getNamespaceURI() == null || title.getNamespaceURI().isEmpty());
        assertTrue(peruste.getNamespaceURI() == null || peruste.getNamespaceURI().isEmpty());

        String xml = serializeXml(doc);
        assertFalse(xml.contains("http://www.w3.org/1999/xhtml"));
        assertTrue(xml.contains("<title>Perusteen nimi</title>"));
        assertTrue(xml.contains("<peruste>Perusteen nimi</peruste>"));
        assertTrue(xml.contains("asdfasdfasfasdf"));
        assertTrue(xml.contains("kvliite"));
    }

    private static Node firstElementChild(Node node) {
        Node child = node.getFirstChild();
        while (child != null && child.getNodeType() != Node.ELEMENT_NODE) {
            child = child.getNextSibling();
        }
        return child;
    }

    private static String serializeXml(Document doc) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        return writer.toString();
    }
}
