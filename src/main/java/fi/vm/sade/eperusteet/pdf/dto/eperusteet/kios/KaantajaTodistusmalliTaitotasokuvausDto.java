package fi.vm.sade.eperusteet.pdf.dto.eperusteet.kios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KaantajaTodistusmalliTaitotasokuvausDto {

    private Long id;
    @Builder.Default
    private List<KaantajaTodistusmalliTaitotasoDto> taitotasot = new ArrayList<>();
}

