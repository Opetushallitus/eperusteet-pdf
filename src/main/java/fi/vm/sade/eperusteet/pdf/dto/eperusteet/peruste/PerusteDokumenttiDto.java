package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import fi.vm.sade.eperusteet.pdf.domain.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.domain.enums.PerusteTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteDokumenttiDto {
    private Long id;
    private PerusteTyyppi tyyppi;
    private Set<Kieli> kielet;
    private Set<SuoritustapaDto> suoritustavat;
    private PerusteVersionDto globalVersion;
    private Optional<Date> viimeisinJulkaisuAika;
}
