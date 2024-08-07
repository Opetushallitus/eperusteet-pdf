package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.common.ReferenceableDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiosaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OppiaineenVuosiluokkakokonaisuusDto implements ReferenceableDto {
    private Long id;
    private Reference vuosiluokkakokonaisuus;
    private TekstiosaDto tehtava;
    private TekstiosaDto yleistavoitteet;
    private TekstiosaDto tyotavat;
    private TekstiosaDto ohjaus;
    private TekstiosaDto arviointi;
    private TekstiosaDto tavoitteistaJohdetutOppimisenTavoitteet;
    private TekstiosaDto sisaltoalueinfo;
    private Integer jnro;
    private Boolean piilotettu;

    private Set<OppiaineenVuosiluokkaDto> vuosiluokat;
}
