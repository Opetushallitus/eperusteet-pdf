package fi.vm.sade.eperusteet.eperusteetpdfservice.service;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.Dokumentti;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteDto;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface DokumenttiNewBuilderService {
    Document generateXML(PerusteDto peruste, Dokumentti dokumentti)
            throws ParserConfigurationException, IOException, TransformerException;
}
