package fi.vm.sade.eperusteet.pdf.service.ylops;

import fi.vm.sade.eperusteet.pdf.domain.common.Dokumentti;
import fi.vm.sade.eperusteet.pdf.exception.DokumenttiException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface YlopsDokumenttiBuilderService {

    @PreAuthorize("isAuthenticated()")
    byte[] generatePdf(Dokumentti dokumentti) throws TransformerException, IOException, SAXException, ParserConfigurationException, NullPointerException, DokumenttiException;
}
