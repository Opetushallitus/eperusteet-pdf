package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ReferenceableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerusteTavoitteenArviointiDto implements ReferenceableDto {
    private Long id;
    private LokalisoituTekstiDto arvioinninKohde;
    private LokalisoituTekstiDto osaamisenKuvaus;
    private Integer arvosana;

    public LokalisoituTekstiDto getHyvanOsaamisenKuvaus() {
        if (arvosana == null || arvosana == 8) {
            return osaamisenKuvaus;
        }

        return null;
    }
}
