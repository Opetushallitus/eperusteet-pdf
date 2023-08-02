package fi.vm.sade.eperusteet.pdf.dto.dokumentti;

import fi.vm.sade.eperusteet.pdf.dto.enums.LaajuusYksikko;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaViiteDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.SuoritustapaLaajaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.TutkinnonOsaKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.TutkinnonOsaViiteSuppeaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.AIPEOpetuksenSisaltoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
public class DokumenttiPeruste extends DokumenttiBase {
//    PerusteenOsaViiteDto sisalto;
//    AIPEOpetuksenSisaltoDto aipeOpetuksenSisalto;
    KVLiiteJulkinenDto kvLiiteJulkinenDto;

    public Optional<SuoritustapaLaajaDto> getSuoritustapa() {
        return this.getPeruste().getSuoritustavat().stream().findAny();
    }

    public PerusteenOsaViiteDto.Laaja getSisalto() {
        return getPeruste().getSisallot().stream().findFirst().get().getSisalto();
    }

    public LaajuusYksikko getLaajuusYksikko() {
        Set<SuoritustapaLaajaDto> suoritustavat = this.getPeruste().getSuoritustavat();
        if (!suoritustavat.isEmpty()) {
            for (SuoritustapaLaajaDto suoritustapa : suoritustavat) {
                return suoritustapa.getLaajuusYksikko();
            }
            return LaajuusYksikko.OSAAMISPISTE;
        }

        return LaajuusYksikko.OSAAMISPISTE;
    }

    public Optional<TutkinnonOsaKaikkiDto> getTutkinnonOsa(Long id) {
        return peruste.getTutkinnonOsat().stream().filter(tosa -> tosa.getId().equals(id)).findFirst();
    }

    public Optional<TutkinnonOsaViiteSuppeaDto> getTutkinnonosaViite(Long id) {
        return peruste.getSuoritustavat().stream().map(st -> st.getTutkinnonOsat())
                .flatMap(Collection::stream)
                .filter(viite -> viite.getId().equals(id))
                .findFirst();
    }
}
