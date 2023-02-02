package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lops2019.Lops2019SisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lukio.LukiokoulutuksenPerusteenSisaltoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EperusteetPerusteDto extends PerusteDto {
    private Long id;
    private PerusteVersionDto globalVersion;
    private PerusopetuksenPerusteenSisaltoDto perusopetus;
    private EsiopetuksenPerusteenSisaltoDto esiopetus;
    private LukiokoulutuksenPerusteenSisaltoDto lukiokoulutus;
    private Lops2019SisaltoDto lops2019;
    private TPOOpetuksenSisaltoDto tpo;
    private AipePerusteenSisaltoDto aipe;
}
