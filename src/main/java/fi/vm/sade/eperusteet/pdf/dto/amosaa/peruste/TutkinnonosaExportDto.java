package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SisaltoViiteExportBaseDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.TekstiKappaleJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.TutkinnonosaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TutkinnonosaExportDto extends SisaltoViiteExportBaseDto {
    private TutkinnonosaDto tosa;

    @JsonUnwrapped
    private TekstiKappaleJulkinenDto tekstiKappale;
}
