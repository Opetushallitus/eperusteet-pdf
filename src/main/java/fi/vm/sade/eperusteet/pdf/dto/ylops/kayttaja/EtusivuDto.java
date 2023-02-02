package fi.vm.sade.eperusteet.pdf.dto.ylops.kayttaja;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtusivuDto {
    private Long opetussuunnitelmatJulkaistut;
    private Long opetussuunnitelmatKeskeneraiset;
    private Long pohjatJulkaistut;
    private Long pohjatKeskeneraiset;
}
