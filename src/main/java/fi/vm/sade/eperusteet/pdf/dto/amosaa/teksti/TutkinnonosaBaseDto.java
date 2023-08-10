package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.TutkinnonOsaTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TutkinnonosaBaseDto {
    private Long id;
    private TutkinnonOsaTyyppi tyyppi;
    private String koodi;
    private Date muokattu;
    private VierasTutkinnonosaDto vierastutkinnonosa;
    private List<TutkinnonosaToteutusDto> toteutukset;
    private LokalisoituTekstiDto osaamisenOsoittaminen;
    private Long perusteentutkinnonosa;
    private List<VapaaTekstiDto> vapaat;
}
