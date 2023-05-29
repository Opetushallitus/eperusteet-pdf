package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TutkinnonosaViiteExportDto extends SisaltoViiteExportBaseDto {
    private TutkinnonosaDto tosa;

    @JsonUnwrapped
    private TekstiKappaleJulkinenDto tekstiKappale;
}
