package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lops2019.laajaalainenosaaminen;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.KoodiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lops2019LaajaAlainenOsaaminenDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private KoodiDto koodi;
    private LokalisoituTekstiDto kuvaus;
}
