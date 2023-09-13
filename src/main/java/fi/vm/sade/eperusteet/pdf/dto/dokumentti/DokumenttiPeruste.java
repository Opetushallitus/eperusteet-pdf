package fi.vm.sade.eperusteet.pdf.dto.dokumentti;

import fi.vm.sade.eperusteet.pdf.dto.enums.LaajuusYksikko;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaViiteDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.SuoritustapaLaajaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.TutkinnonOsaKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.TutkinnonOsaViiteSuppeaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.AIPEOpetuksenSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.LaajaalainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.PerusopetuksenPerusteenSisaltoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
public class DokumenttiPeruste extends DokumenttiBase {
    KVLiiteJulkinenDto kvLiiteJulkinenDto;

    public Optional<SuoritustapaLaajaDto> getSuoritustapa() {
        if (this.getPeruste().getSuoritustavat() == null) {
            return Optional.empty();
        }
        return this.getPeruste().getSuoritustavat().stream().findFirst();
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

    public Optional<LaajaalainenOsaaminenDto> getLaajaAlainenOsaaminen(Long id) {
        return getPeruste().getPerusopetuksenPerusteenSisalto().getLaajaalaisetosaamiset().stream().filter(lao -> lao.getId().equals(id)).findAny();
    }
}
