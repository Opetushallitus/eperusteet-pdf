package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.dto.dokumentti.TemplateTyyppi;
import fi.vm.sade.eperusteet.utils.dto.dokumentti.DokumenttiMetaDto;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface PdfService {
    byte[] xhtml2pdf(Document document, DokumenttiMetaDto meta, TemplateTyyppi tyypi) throws IOException, TransformerException, SAXException;
}
