package fi.vm.sade.eperusteet.pdf.dto.amosaa.ops;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaBaseDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.PerusteBaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VanhentunutPohjaperusteDto {
    OpetussuunnitelmaBaseDto opetussuunnitelma;
    PerusteBaseDto perusteUusi;
    PerusteBaseDto perusteVanha;

    Date edellinenPaivitys;
    Date perustePaivittynyt;
}
