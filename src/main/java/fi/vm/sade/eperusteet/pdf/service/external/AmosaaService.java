package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.SisaltoTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SisaltoViiteDto;

import java.util.List;

public interface AmosaaService {
    OpetussuunnitelmaKaikkiDto getOpetussuunnitelma(Long ktId, Long opsId);

    PerusteKaikkiDto getPerusteKaikkiDto(Long id) throws JsonProcessingException;

//    PerusteenOsaDto getPerusteenOsa(Long perusteId, Long perusteenosaId);

    List<SisaltoViiteDto> getSisaltoviitteenTyypilla(Long ktId, Long opsId, SisaltoTyyppi tyyppi);

    // TODO: remove temp funktiot
    OpetussuunnitelmaKaikkiDto getOpetussuunnitelmaTemp(Long ktId, Long opsId);
    PerusteKaikkiDto getPerusteKaikkiDtoTemp(Long id) throws JsonProcessingException;
}
