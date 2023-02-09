package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.TutkinnonosaExportDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SisaltoViiteExportDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SisaltoViiteExportOpintokokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SuorituspolkuRakenneDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.KoulutusTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OpetussuunnitelmaKaikkiDto extends OpetussuunnitelmaDto {
    SisaltoViiteExportDto sisalto;
    List<SuorituspolkuRakenneDto> suorituspolut;
    List<TutkinnonosaExportDto> tutkinnonOsat;
    List<SisaltoViiteExportOpintokokonaisuusDto> opintokokonaisuudet;

    public KoulutusTyyppi getKoulutustyyppi() {
        if (super.getKoulutustyyppi() != null) {
            return super.getKoulutustyyppi();
        } else if (getPeruste() != null) {
            return getPeruste().getKoulutustyyppi();
        }

        return null;
    }
}
