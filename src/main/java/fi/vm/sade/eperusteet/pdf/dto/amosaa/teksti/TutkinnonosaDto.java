package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutkinnonosaDto extends TutkinnonosaBaseDto {
    private OmaTutkinnonosaDto omatutkinnonosa;
    private VierasTutkinnonosaDto vierastutkinnonosa;
    private List<TutkinnonosaToteutusDto> toteutukset;
    private LokalisoituTekstiDto osaamisenOsoittaminen;
    private Long perusteentutkinnonosa;
    private List<VapaaTekstiDto> vapaat;
}
