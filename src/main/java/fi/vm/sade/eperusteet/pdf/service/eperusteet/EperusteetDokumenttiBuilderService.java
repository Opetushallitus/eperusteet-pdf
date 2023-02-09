package fi.vm.sade.eperusteet.pdf.service.eperusteet;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;

public interface EperusteetDokumenttiBuilderService {

    Document generateXML(GeneratorData generatorData, PerusteKaikkiDto perusteData) throws ParserConfigurationException, JsonProcessingException;
}
