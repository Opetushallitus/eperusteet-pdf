package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.KoulutustyyppiToteutus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class PerusteDto {
    private Long id;
    private PerusteenLokalisoituTekstiDto nimi;
    private KoulutusTyyppi koulutustyyppi;
    private Set<KoulutusDto> koulutukset;
    private Set<Kieli> kielet;
    private PerusteenLokalisoituTekstiDto kuvaus;
    private String diaarinumero;
    private Date voimassaoloAlkaa;
    private Date siirtymaPaattyy;
    private Date voimassaoloLoppuu;
    private Date muokattu;
    private String tila;
    private String tyyppi;
    private KoulutustyyppiToteutus toteutus;
    private Set<String> korvattavatDiaarinumerot;
}
