package fi.vm.sade.eperusteet.pdf.dto.eperusteet.arviointi;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.OsaamistasoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArviointiAsteikkoDto {
    private Long id;
    private List<OsaamistasoDto> osaamistasot;
}
