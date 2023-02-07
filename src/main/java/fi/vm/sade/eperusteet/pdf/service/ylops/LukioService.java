package fi.vm.sade.eperusteet.pdf.service.ylops;

import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiYlops;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface LukioService {
    void addOppimistavoitteetJaOpetuksenKeskeisetSisallot(DokumenttiYlops docBase) throws ParserConfigurationException, SAXException, IOException;
}
