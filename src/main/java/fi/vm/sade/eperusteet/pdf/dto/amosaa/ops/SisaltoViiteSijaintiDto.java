package fi.vm.sade.eperusteet.pdf.dto.amosaa.ops;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaBaseDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.TekstiKappaleNimiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.TutkinnonOsaSijaintiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SisaltoViiteSijaintiDto {
    private Long id;
    private TekstiKappaleNimiDto tekstiKappale;
    private OpetussuunnitelmaBaseDto owner;
    private TutkinnonOsaSijaintiDto tosa;
}

