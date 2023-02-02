package fi.vm.sade.eperusteet.pdf.dto.ylops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OppiaineOpintojaksoDto {
    Long id;
    List<OppiaineOpintojaksoDto> lapset;
}
