package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.CachedPerusteBaseDto;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.JotpaTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.OpsTyyppi;
import fi.vm.sade.eperusteet.utils.domain.utils.Tila;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpetussuunnitelmaBaseDto {

    private Long id;
    private LokalisoituTekstiDto nimi;
    private Tila tila;
    private OpsTyyppi tyyppi;
    private LokalisoituTekstiDto kuvaus;
    private KoulutustoimijaBaseDto koulutustoimija;
    private Date luotu;
    private Date muokattu;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Reference pohja;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long perusteId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String perusteDiaarinumero;

    private CachedPerusteBaseDto peruste;

    @Deprecated
    @ApiModelProperty(hidden = true)
    private Tila tila2016;

    private JotpaTyyppi jotpatyyppi;

}
