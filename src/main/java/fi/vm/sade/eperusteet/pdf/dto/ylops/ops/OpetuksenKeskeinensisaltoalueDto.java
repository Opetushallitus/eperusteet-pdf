package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.ReferenceableDto;
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
