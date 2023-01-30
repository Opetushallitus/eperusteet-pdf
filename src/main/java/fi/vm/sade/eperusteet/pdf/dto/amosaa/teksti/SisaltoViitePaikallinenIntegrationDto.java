package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.Reference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SisaltoViitePaikallinenIntegrationDto {
    private Long id;
    private TekstiKappaleNimiDto tekstiKappale;
    private TutkinnonOsaIntegrationDto tosa;
    private Reference owner;
}
