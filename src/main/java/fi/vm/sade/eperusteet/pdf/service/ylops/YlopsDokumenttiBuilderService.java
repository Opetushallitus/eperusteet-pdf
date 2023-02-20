package fi.vm.sade.eperusteet.pdf.service.ylops;

import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.OpetussuunnitelmaExportDto;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;

public interface YlopsDokumenttiBuilderService {
    Document generateXML(OpetussuunnitelmaExportDto ops, PerusteKaikkiDto perusteKaikkiDto, GeneratorData generatorData) throws ParserConfigurationException;
}
