package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.Reference;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ReferenceableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpetuksenTavoiteDto implements ReferenceableDto {
    private Long id;
    private UUID tunniste;
    private LokalisoituTekstiDto tavoite;
    private Set<Reference> laajattavoitteet;
    private Set<Reference> kohdealueet;
    private Set<TavoitteenArviointiDto> arvioinninkohteet;
    private Set<OpetuksenKeskeinensisaltoalueDto> sisaltoalueet;
    private LokalisoituTekstiDto arvioinninKuvaus;
    private LokalisoituTekstiDto vapaaTeksti;
    private LokalisoituTekstiDto tavoitteistaJohdetutOppimisenTavoitteet;

}
