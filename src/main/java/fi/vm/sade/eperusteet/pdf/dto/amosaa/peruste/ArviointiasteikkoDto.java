package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArviointiasteikkoDto {
    private Long id;
    private List<OsaamistasoDto> osaamistasot;
}
