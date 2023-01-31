package fi.vm.sade.eperusteet.pdf.service.amosaa.impl;

import fi.vm.sade.eperusteet.pdf.service.amosaa.AmosaaPdfService;
import fi.vm.sade.eperusteet.pdf.service.util.DokumenttiEventListener;
import fi.vm.sade.eperusteet.utils.dto.dokumentti.DokumenttiMetaDto;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Service
public class AmosaaPdfServiceImpl implements AmosaaPdfService {
    private static final Logger LOG = LoggerFactory.getLogger(AmosaaPdfServiceImpl.class);

    @Value("classpath:docgen/xhtml-to-xslfo-amosaa.xsl")
    Resource template;

    @Value("classpath:docgen/fop.xconf")
    Resource config;

    @Override
    public byte[] xhtml2pdf(Document document, DokumenttiMetaDto meta) throws IOException, TransformerException, SAXException {
        return convertOps2PDF(document, template.getFile(), meta);
    }

    private byte[] convertOps2PDF(Document doc, File xslt, DokumenttiMetaDto meta)
            throws IOException, TransformerException, SAXException {
        // Alustetaan Streamit
        ByteArrayOutputStream xmlStream = new ByteArrayOutputStream();
        ByteArrayOutputStream foStream = new ByteArrayOutputStream();
        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();

        // Muunnetaan ops objekti xml muotoon
        convertOps2XML(doc, xmlStream);

        // Muunntetaan saatu xml malli fo:ksi
        InputStream xmlInputStream = new ByteArrayInputStream(xmlStream.toByteArray());
        convertXML2FO(xmlInputStream, xslt, foStream);

        // Muunnetaan saatu fo malli pdf:ksi
        InputStream foInputStream = new ByteArrayInputStream(foStream.toByteArray());
        convertFO2PDF(doc, foInputStream, pdfStream, meta);

        return pdfStream.toByteArray();
    }

    private void convertOps2XML(Document doc, OutputStream xml)
            throws IOException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        // To avoid <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        Source src = new DOMSource(doc);
        Result res = new StreamResult(xml);

        transformer.transform(src, res);
    }

    private void convertXML2FO(InputStream xml, File xslt, OutputStream fo)
            throws IOException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xslt));

        Source src = new StreamSource(xml);
        Result res = new StreamResult(fo);

        transformer.transform(src, res);
    }

    @SuppressWarnings("unchecked")
    private void convertFO2PDF(Document doc, InputStream fo, OutputStream pdf, DokumenttiMetaDto meta)
            throws IOException, SAXException, TransformerException {

        FopFactory fopFactory = FopFactory.newInstance(config.getFile());

        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        foUserAgent.getRendererOptions().put("pdf-a-mode", "PDF/A-1b");
        foUserAgent.setAccessibility(true);
        foUserAgent.getEventBroadcaster().addEventListener(DokumenttiEventListener.getInstance());

        if (meta != null && meta.getTitle() != null) {
            foUserAgent.setTitle(meta.getTitle());
        }

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

        if (meta != null && meta.getSubject() != null) {
            foUserAgent.setSubject(meta.getSubject());
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

    private void printStream(ByteArrayOutputStream stream) {
        LOG.info(new String(stream.toByteArray(), StandardCharsets.UTF_8));
    }
}
