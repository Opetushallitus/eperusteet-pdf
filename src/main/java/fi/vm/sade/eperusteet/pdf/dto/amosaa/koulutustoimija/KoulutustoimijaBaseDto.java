package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KoulutustoimijaBaseDto {
    Long id;
    String organisaatio;
    LokalisoituTekstiDto nimi;
    private boolean organisaatioRyhma;
}
