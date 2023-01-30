package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KotoKielitaitotasoDto {
    private Long id;
    private List<KotoTaitotasoDto> taitotasot = new ArrayList<>();
    private List<KotoTaitotasoLaajaAlainenOsaaminenDto> laajaAlaisetOsaamiset = new ArrayList<>();
}
