package fi.vm.sade.eperusteet.pdf.service.amosaa;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.Dokumentti;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface AmosaaDokumenttiBuilderService {

    byte[] generatePdf(Dokumentti dokumentti, Long ktId)
            throws ParserConfigurationException, IOException, SAXException, TransformerException;
}
