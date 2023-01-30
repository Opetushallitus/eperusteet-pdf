package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TutkinnonosaToteutusDto {
    private Long id;
    private LokalisoituTekstiDto otsikko;
    private TekstiosaDto tavatjaymparisto;
    private TekstiosaDto arvioinnista;
    private Set<String> koodit;
    private List<VapaaTekstiDto> vapaat;
}
