package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.common.ReferenceableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VuosiluokkaKokonaisuudenLaajaalainenOsaaminenDto implements ReferenceableDto {
    private Long id;
    @JsonProperty("_laajaalainenOsaaminen")
    private Reference laajaalainenOsaaminen;
    @JsonProperty("laajaalainenOsaaminen")
    private LaajaalainenOsaaminenDto laajaalainenOsaaminenDto;
    private LokalisoituTekstiDto kuvaus;
}
