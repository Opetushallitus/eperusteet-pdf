package fi.vm.sade.eperusteet.pdf.service.eperusteet;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.Dokumentti;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface EperusteetDokumenttiBuilderService {
    byte[] generatePdf(Dokumentti dokumentti)
            throws ParserConfigurationException, IOException, TransformerException, SAXException;
}
