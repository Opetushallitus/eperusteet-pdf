package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Käytetään tutkinnon osien viemiseen kokonaisuudessaan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SisaltoViiteExportTutkinnonosaDto extends SisaltoViiteExportBaseDto {
    private TutkinnonosaDto tosa;
}
