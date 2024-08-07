package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.exception.DokumenttiException;
import fi.vm.sade.eperusteet.pdf.utils.DokumenttiEventListener;
import fi.vm.sade.eperusteet.utils.dto.dokumentti.DokumenttiMetaDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
@Service
public class PdfServiceImpl implements PdfService {
    private static final Logger LOG = LoggerFactory.getLogger(PdfServiceImpl.class);

    @Value("classpath:docgen/xhtml-to-xslfo-eperusteet.xsl")
    private Resource eperusteetTemplate;

    @Value("classpath:docgen/xhtml-to-xslfo-ylops.xsl")
    private Resource ylopsTemplate;

    @Value("classpath:docgen/xhtml-to-xslfo-amosaa.xsl")
    private Resource amosaaTemplate;

    @Value("classpath:docgen/kvliite.xsl")
    private Resource kvLiiteTemplate;

    @Value("${fopConfiguration}")
    private Resource config;

    @Value("${docgenPath: ''}")
    private String docgenPath;

    @Override
    public byte[] xhtml2pdf(Document document, DokumenttiMetaDto meta, DokumenttiTyyppi tyyppi) throws IOException, TransformerException, SAXException, DokumenttiException {
        // Alustetaan Streamit
        ByteArrayOutputStream xmlStream = new ByteArrayOutputStream();
        ByteArrayOutputStream foStream = new ByteArrayOutputStream();
        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();

        // Muunnetaan ops objekti xml muotoon
        convertOps2XML(document, xmlStream);

        // Muunntetaan saatu xml malli fo:ksi
        InputStream xmlInputStream = new ByteArrayInputStream(xmlStream.toByteArray());
        convertXML2FO(xmlInputStream, selectTemplate(tyyppi), foStream);

        // Muunnetaan saatu fo malli pdf:ksi
        InputStream foInputStream = new ByteArrayInputStream(foStream.toByteArray());

        convertFO2PDF(document, foInputStream, pdfStream, meta, tyyppi);

        return pdfStream.toByteArray();
    }

    @SuppressWarnings("unchecked")
    private void convertFO2PDF(Document doc, InputStream fo, OutputStream pdf, DokumenttiMetaDto meta, DokumenttiTyyppi tyyppi)
            throws IOException, SAXException, TransformerException {
        FopFactory fopFactory = FopFactory.newInstance(config.getURI(), config.getInputStream());

        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        foUserAgent.setAccessibility(true);
        foUserAgent.getRendererOptions().put("pdf-a-mode", "PDF/A-1b");
        foUserAgent.getEventBroadcaster().addEventListener(DokumenttiEventListener.getInstance());

        if (meta != null && meta.getTitle() != null) {
            foUserAgent.setTitle(meta.getTitle());
        }

        if (meta != null && meta.getSubject() != null) {
            foUserAgent.setSubject(meta.getSubject());
        }

        if (DokumenttiTyyppi.AMOSAA.equals(tyyppi)) {
            // Override with document title
            try {
                XPathFactory xPathfactory = XPathFactory.newInstance();
                XPath xpath = xPathfactory.newXPath();
                XPathExpression expr = xpath.compile("/html/head/title");
                NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                if (nl.getLength() > 0) {
                    Node titleNode = nl.item(0);
                    String title = titleNode.getTextContent();
                    if (title != null) {
                        foUserAgent.setTitle(title);
                    }
                }
            } catch (XPathExpressionException e) {
                LOG.error(e.getLocalizedMessage());
            }
        }

        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, pdf);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        // XSLT version
        transformer.setParameter("versionParam", "2.0");

        Source src = new StreamSource(fo);
        Result res = new SAXResult(fop.getDefaultHandler());

        transformer.transform(src, res);
    }

    private void convertOps2XML(Document doc, OutputStream xml) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        // To avoid <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        Source src = new DOMSource(doc);
        Result res = new StreamResult(xml);

        transformer.transform(src, res);
    }

    private void convertXML2FO(InputStream xml, InputStream xslt, OutputStream fo) throws IOException, TransformerException {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xslt));
            transformer.setParameter("docgenPath", docgenPath);

            Source src = new StreamSource(xml);
            Result res = new StreamResult(fo);

            transformer.transform(src, res);
        } finally {
            fo.close();
        }
    }

    private InputStream selectTemplate(DokumenttiTyyppi tyyppi) throws IOException, DokumenttiException {
        if (tyyppi.equals(DokumenttiTyyppi.PERUSTE)) {
            return eperusteetTemplate.getInputStream();
        } else if (tyyppi.equals(DokumenttiTyyppi.AMOSAA)) {
            return amosaaTemplate.getInputStream();
        } else if (tyyppi.equals(DokumenttiTyyppi.YLOPS)) {
            return ylopsTemplate.getInputStream();
        } else if (tyyppi.equals(DokumenttiTyyppi.KVLIITE)) {
            return kvLiiteTemplate.getInputStream();
        } else {
            throw new DokumenttiException("Dokumentti-tyyppiä ei ole määritetty.");
        }
    }
}
