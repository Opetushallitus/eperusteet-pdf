package fi.vm.sade.eperusteet.pdf.dto.eperusteet.digi;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OsaamiskokonaisuusOsaAlueDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private List<OsaamiskokonaisuusOsaAlueTasoKuvausDto> tasokuvaukset;
}
