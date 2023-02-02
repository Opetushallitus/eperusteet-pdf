package fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.TutkinnonOsaTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.Reference;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.ReferenceableDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.Sortable;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.TutkinnonOsaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class TutkinnonOsaViiteDto implements ReferenceableDto, Sortable {

    private Long id;
    private BigDecimal laajuus;
    private BigDecimal laajuusMaksimi; // TODO: Ainoastaan valmatelmalla
    private Integer jarjestys;
    @JsonProperty("_tutkinnonOsa")
    private Reference tutkinnonOsa;
    @JsonProperty("tutkinnonOsa")
    private TutkinnonOsaDto tutkinnonOsaDto;

    private Date muokattu;
    private LokalisoituTekstiDto nimi;
    private TutkinnonOsaTyyppi tyyppi;

    public TutkinnonOsaViiteDto(BigDecimal laajuus, Integer jarjestys, LokalisoituTekstiDto nimi, TutkinnonOsaTyyppi tyyppi) {
        this.laajuus = laajuus;
        this.jarjestys = jarjestys;
        this.nimi = nimi;
        this.tyyppi = tyyppi;
    }
}
