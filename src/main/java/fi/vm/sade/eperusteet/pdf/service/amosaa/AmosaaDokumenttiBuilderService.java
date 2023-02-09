package fi.vm.sade.eperusteet.pdf.service.amosaa;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;

public interface AmosaaDokumenttiBuilderService {
    Document generateXML(GeneratorData generatorData, OpetussuunnitelmaKaikkiDto ops) throws ParserConfigurationException, JsonProcessingException;
}
