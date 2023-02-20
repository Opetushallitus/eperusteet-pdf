package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.Reference;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.ReferenceableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VuosiluokkaKokonaisuudenLaajaalainenOsaaminenDto implements ReferenceableDto {
    private Long id;
    private Reference laajaalainenOsaaminen;
    private LokalisoituTekstiDto kuvaus;
}
