package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.Kieli;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteKoosteDto {
    private LokalisoituTekstiDto nimi;
    private String diaarinumero;
    private String koulutustyyppi;
    private Set<Kieli> kielet;
    List<KoodiDto> tutkinnonOsat;
    List<KoosteenOsaamisalaDto> osaamisalat;
}
