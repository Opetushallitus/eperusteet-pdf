package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ReferenceableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpetuksenKeskeinensisaltoalueDto implements ReferenceableDto {
    private Long id;
    private KeskeinenSisaltoalueDto sisaltoalueet;
    private LokalisoituTekstiDto omaKuvaus;
}
