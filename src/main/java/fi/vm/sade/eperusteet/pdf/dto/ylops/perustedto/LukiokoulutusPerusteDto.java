package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lukio.LukiokoulutuksenPerusteenSisaltoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LukiokoulutusPerusteDto extends PerusteDto {
    private LukiokoulutuksenPerusteenSisaltoDto lukiokoulutus;
}
