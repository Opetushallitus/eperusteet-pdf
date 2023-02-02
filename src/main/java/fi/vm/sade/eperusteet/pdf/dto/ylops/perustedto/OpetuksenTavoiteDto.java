package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ReferenceableDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class OpetuksenTavoiteDto implements ReferenceableDto {
    private Long id;
    private UUID tunniste;
    private PerusteenLokalisoituTekstiDto tavoite;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<KeskeinenSisaltoalueDto> sisaltoalueet;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<LaajaalainenOsaaminenDto> laajattavoitteet;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<OpetuksenKohdealueDto> kohdealueet;
    private Set<TavoitteenArviointiDto> arvioinninkohteet;
    private PerusteenLokalisoituTekstiDto arvioinninKuvaus;
    private PerusteenLokalisoituTekstiDto vapaaTeksti;
    private PerusteenLokalisoituTekstiDto tavoitteistaJohdetutOppimisenTavoitteet;
}
