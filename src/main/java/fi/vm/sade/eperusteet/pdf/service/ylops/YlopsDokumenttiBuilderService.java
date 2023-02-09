package fi.vm.sade.eperusteet.pdf.service.ylops;

import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.OpetussuunnitelmaExportDto;
import fi.vm.sade.eperusteet.pdf.exception.DokumenttiException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface YlopsDokumenttiBuilderService {
    Document generateXML(OpetussuunnitelmaExportDto ops, PerusteKaikkiDto perusteKaikkiDto, GeneratorData generatorData) throws ParserConfigurationException;
}
