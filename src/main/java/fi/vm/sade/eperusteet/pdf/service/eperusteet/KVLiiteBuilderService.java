package fi.vm.sade.eperusteet.pdf.service.eperusteet;

import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface KVLiiteBuilderService {

    Document generateXML(PerusteKaikkiDto peruste, Kieli kieli) throws IOException, ParserConfigurationException;
}
