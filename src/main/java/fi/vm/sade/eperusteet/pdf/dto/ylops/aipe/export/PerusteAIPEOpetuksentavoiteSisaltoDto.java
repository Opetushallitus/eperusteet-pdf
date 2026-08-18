package fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.PerusteTavoitteenArviointiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PerusteAIPEOpetuksentavoiteSisaltoDto {
    private Long id;
    private UUID tunniste;
    private LokalisoituTekstiDto tavoite;
    @JsonProperty("laajaalaisetosaamiset")
    private Set<PerusteAIPELaajaalainenosaaminenSisaltoDto> laajattavoitteet;
    private Set<PerusteAIPEOpetuksenkohdealueSisaltoDto> kohdealueet;
    private Set<PerusteTavoitteenArviointiDto> arvioinninkohteet;
    private LokalisoituTekstiDto arvioinninKuvaus;
    private LokalisoituTekstiDto arvioinninOtsikko;
    private LokalisoituTekstiDto vapaaTeksti;
    private LokalisoituTekstiDto tavoitteistaJohdetutOppimisenTavoitteet;
}
