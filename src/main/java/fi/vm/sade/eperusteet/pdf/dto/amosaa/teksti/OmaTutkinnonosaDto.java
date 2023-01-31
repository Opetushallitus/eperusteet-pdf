package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.ArviointiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OmaTutkinnonosaDto {
    private Long id;
    private String koodi;
    private BigDecimal laajuus;
    private ArviointiDto arviointi;
    private Long geneerinenarviointi;
    private PaikallisetAmmattitaitovaatimukset2019Dto ammattitaitovaatimukset;
    private LokalisoituTekstiDto ammattitaidonOsoittamistavat;

    @Deprecated
    private List<AmmattitaitovaatimuksenKohdealueDto> ammattitaitovaatimuksetLista;

    @Deprecated
    private LokalisoituTekstiDto tavoitteet;
}
