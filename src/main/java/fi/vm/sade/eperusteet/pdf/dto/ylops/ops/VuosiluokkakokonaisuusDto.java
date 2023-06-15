package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.Tila;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.common.ReferenceableDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiosaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VuosiluokkakokonaisuusDto implements ReferenceableDto {

    private Long id;
    private Reference tunniste;
    private LokalisoituTekstiDto nimi;
    private TekstiosaDto siirtymaEdellisesta;
    private TekstiosaDto tehtava;
    private TekstiosaDto siirtymaSeuraavaan;
    private TekstiosaDto laajaalainenosaaminen;
    private Tila tila;
    private Set<LaajaalainenosaaminenDto> laajaalaisetosaamiset;

    public VuosiluokkakokonaisuusDto(Reference tunniste) {
        this.tunniste = tunniste;
    }

}
