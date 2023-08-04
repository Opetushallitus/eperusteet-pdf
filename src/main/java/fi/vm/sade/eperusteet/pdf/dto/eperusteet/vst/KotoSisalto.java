package fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;

import java.util.List;

public interface KotoSisalto {
    KoodiDto getNimiKoodi();
    LokalisoituTekstiDto getKuvaus();
    List<KotoTaitotasoDto> getTaitotasot();
}