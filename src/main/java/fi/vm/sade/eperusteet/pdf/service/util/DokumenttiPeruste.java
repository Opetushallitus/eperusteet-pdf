package fi.vm.sade.eperusteet.pdf.service.util;

import fi.vm.sade.eperusteet.pdf.domain.enums.LaajuusYksikko;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaViiteDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.SuoritustapaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.AIPEOpetuksenSisaltoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DokumenttiPeruste extends DokumenttiBase {
    CharapterNumberGenerator generator;
    PerusteenOsaViiteDto sisalto;
    AIPEOpetuksenSisaltoDto aipeOpetuksenSisalto;
    KVLiiteJulkinenDto kvLiiteJulkinenDto;

    public LaajuusYksikko getLaajuusYksikko() {
        Set<SuoritustapaDto> suoritustavat = this.getPeruste().getSuoritustavat();
        if (!suoritustavat.isEmpty()) {
            for (SuoritustapaDto suoritustapa : suoritustavat) {
                return suoritustapa.getLaajuusYksikko();
            }
            return LaajuusYksikko.OSAAMISPISTE;
        }

        return LaajuusYksikko.OSAAMISPISTE;
    }
}