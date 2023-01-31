package fi.vm.sade.eperusteet.pdf.dto.eperusteet.util;

import com.fasterxml.jackson.annotation.JsonValue;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.NavigationNodeDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.NavigationType;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.TutkinnonOsaViiteDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
public class NavigableLokalisoituTekstiDto extends LokalisoituTekstiDto {

    @Setter
    @Getter
    private NavigationNodeDto navigationNode;

    public NavigableLokalisoituTekstiDto(Long id, Map<Kieli, String> values, NavigationNodeDto navigationNode) {
        super(id, values);
        this.navigationNode = navigationNode;
    }

    public NavigableLokalisoituTekstiDto(TutkinnonOsaViiteDto viite) {
        this(
                viite.getTutkinnonOsaDto().getNimi().getId(),
                viite.getTutkinnonOsaDto().getNimi().getTekstit(),
                NavigationNodeDto.of(
                        NavigationType.tutkinnonosaviite,
                        LokalisoituTekstiDto.of(viite.getNimi().getTekstit()),
                        viite.getId()));
    }

    // Vaatii joitain muutoksia, koska PerusteenOsaDto ei sisällä navigationTypeä ja viitteitä
//    public NavigableLokalisoituTekstiDto(PerusteenOsaDto perusteenOsa) {
//        this(
//                perusteenOsa.getNimi() != null ? perusteenOsa.getNimi().getId() : null,
//                perusteenOsa.getNimi() != null ? perusteenOsa.getNimi().getTekstit() : null,
//                NavigationNodeDto.of(
//                        perusteenOsa.getNavigationType(),
//                        perusteenOsa.getNimi() != null ? LokalisoituTekstiDto.of(perusteenOsa.getNimi().getTekstit()) : null,
//                        perusteenOsa.getViitteet().stream().findFirst().get().getId()));
//    }

    public NavigableLokalisoituTekstiDto(Long id, UUID tunniste, Map<Kieli, String> values) {
        super(id, tunniste, values);
    }

    public NavigableLokalisoituTekstiDto(Map<String, String> values) {
        super(values);
    }

    public NavigableLokalisoituTekstiDto(Long id, Map<Kieli, String> values) {
        super(id, values);
    }

    @Override
    @JsonValue
    public Map<String, Object> asMap() {
        Map<String, Object> map = super.asMap();
        if(navigationNode != null) {
                map.put("navigationNode", navigationNode);
        }

        return map;
    }
}
