package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kuvatyyppi;

import java.io.InputStream;
import java.util.UUID;

public interface CommonExternalService {
    InputStream getLiitetiedosto(Long sisaltoId, UUID fileName, DokumenttiTyyppi tyyppi);

    byte[] getDokumenttiKuva(GeneratorData generatorData, Kuvatyyppi kuvatyyppi);

    TermiDto getTermi(Long id, String avain, DokumenttiTyyppi dokumenttityyppi);

    void postPdfData(byte[] pdfData, Long dokumenttiId, DokumenttiTyyppi tyyppi);

    void updateDokumenttiTila(DokumenttiTila tila, Long dokumenttiId, DokumenttiTyyppi tyyppi);
}
