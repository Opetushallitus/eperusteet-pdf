package fi.vm.sade.eperusteet.pdf.service.ylops;


import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiBase;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiYlops;

/**
 * @author isaul
 */
public interface PerusopetusService {
    void addVuosiluokkakokonaisuudet(DokumenttiYlops docBase);
}
