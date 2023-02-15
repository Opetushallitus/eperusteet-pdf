package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;

public interface EperusteetService {
    void postPdfData(byte[] pdfData, Long dokumenttiId);

    void updateDokumenttiTila(DokumenttiTila tila, Long dokumenttiId);

    KVLiiteJulkinenDto getKvLiite(Long id) throws JsonProcessingException;

    ArviointiAsteikkoDto getArviointiasteikko(Long id);

    // TODO: remove temp funktio
    PerusteKaikkiDto getPerusteKaikkiDtoTemp(Long id, Integer revision) throws JsonProcessingException;
}
