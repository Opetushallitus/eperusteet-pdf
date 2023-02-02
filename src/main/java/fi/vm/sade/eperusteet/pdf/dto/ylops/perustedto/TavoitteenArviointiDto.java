package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import fi.vm.sade.eperusteet.pdf.dto.ylops.ReferenceableDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TavoitteenArviointiDto implements ReferenceableDto {
    private Long id;
    private PerusteenLokalisoituTekstiDto arvioinninKohde;
    private PerusteenLokalisoituTekstiDto osaamisenKuvaus;
    private Integer arvosana;

    public PerusteenLokalisoituTekstiDto getHyvanOsaamisenKuvaus() {
        if(arvosana == null || arvosana == 8) {
            return osaamisenKuvaus;
        }

        return null;
    }
}
