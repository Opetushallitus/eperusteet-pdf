package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SisaltoViiteDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.SisaltoTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;

import java.util.List;

public interface AmosaaService {
    PerusteKaikkiDto getPerusteKaikkiDto(Long id) throws JsonProcessingException;

    List<SisaltoViiteDto> getSisaltoviitteenTyypilla(Long ktId, Long opsId, SisaltoTyyppi tyyppi);
}
