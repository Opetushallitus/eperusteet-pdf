package fi.vm.sade.eperusteet.pdf.dto.eperusteet.opas.lops2019.oppiaineet.moduuli;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.ReferenceableDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lops2019ModuuliBaseDto implements ReferenceableDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private Boolean pakollinen;
    private KoodiDto koodi;
    private BigDecimal laajuus;
}
