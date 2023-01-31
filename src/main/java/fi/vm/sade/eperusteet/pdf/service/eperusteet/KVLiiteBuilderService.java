package fi.vm.sade.eperusteet.pdf.service.eperusteet;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteDto;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface KVLiiteBuilderService {
    Document generateXML(PerusteDto peruste, Kieli kieli)
            throws ParserConfigurationException, IOException, TransformerException;
}
