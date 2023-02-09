package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
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
