package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.Reference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class KoulutustoimijaDto extends KoulutustoimijaBaseDto {
    LokalisoituTekstiDto kuvaus;
    private Set<Reference> ystavat;
    private boolean salliystavat;
}
