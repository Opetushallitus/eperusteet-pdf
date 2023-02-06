package fi.vm.sade.eperusteet.pdf.service.eperusteet;

import fi.vm.sade.eperusteet.pdf.domain.common.Dokumentti;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface EperusteetDokumenttiBuilderService {
    Document generateXML(Dokumentti dokumentti, PerusteKaikkiDto perusteData)
            throws ParserConfigurationException, IOException, TransformerException, SAXException;
}
