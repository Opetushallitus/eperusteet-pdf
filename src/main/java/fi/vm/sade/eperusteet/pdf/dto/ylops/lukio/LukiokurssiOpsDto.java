package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lukio.LukiokurssiPerusteDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiosaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class LukiokurssiOpsDto extends LukiokurssiListausOpsDto
        implements PerusteeseenViittaava<LukiokurssiPerusteDto> {
    private LukiokurssiPerusteDto perusteen;
    private LokalisoituTekstiDto kuvaus;
    private TekstiosaDto tavoitteet;
    private TekstiosaDto keskeinenSisalto;
    private TekstiosaDto tavoitteetJaKeskeinenSisalto;
}
