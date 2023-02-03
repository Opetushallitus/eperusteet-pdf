package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.TutkinnonOsaTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.AmmattitaitovaatimusKohdealueetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TutkinnonosaKaikkiDto extends PerusteenOsaDto {
    private final String osanTyyppi = "tutkinnonosa";
    private LokalisoituTekstiDto tavoitteet;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArviointiDto arviointi;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AmmattitaitovaatimusKohdealueetDto> ammattitaitovaatimuksetLista;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LokalisoituTekstiDto ammattitaitovaatimukset;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LokalisoituTekstiDto ammattitaidonOsoittamistavat;
    private LokalisoituTekstiDto kuvaus;
    private Long opintoluokitus;
    private String koodiUri;
    private String koodiArvo;
    private List<OsaAlueKokonaanDto> osaAlueet;
    private TutkinnonOsaTyyppi tyyppi;
    private ValmaTelmaSisaltoDto valmaTelmaSisalto;
}
