package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.common.ReferenceableDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.OppiaineenTavoitteenOpetuksenTavoiteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
    private List<OppiaineenTavoitteenOpetuksenTavoiteDto> oppiaineenTavoitteenOpetuksenTavoitteet;
}
