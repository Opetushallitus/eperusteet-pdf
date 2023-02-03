package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.KoulutustyyppiToteutus;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Tila;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Tyyppi;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.annotation.Identifiable;
import fi.vm.sade.eperusteet.pdf.domain.ylops.OpsIdentifiable;
import fi.vm.sade.eperusteet.pdf.dto.ylops.koodisto.KoodistoDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.koodisto.OrganisaatioDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpetussuunnitelmaBaseDto
        implements Serializable, Identifiable, OpsIdentifiable {
    private Long id;
    private Set<Kieli> julkaisukielet;
    private Set<OrganisaatioDto> organisaatiot;
    private Set<KoodistoDto> kunnat;
    private LokalisoituTekstiDto kuvaus;
    private String luoja;
    private Date luotu;
    private Date muokattu;
    private String muokkaaja;
    private String hyvaksyjataho;
    private LokalisoituTekstiDto nimi;
    private String perusteenDiaarinumero;
    private Long perusteenId;
    private Tila tila;
    private Tyyppi tyyppi;
    private KoulutusTyyppi koulutustyyppi;
    private KoulutustyyppiToteutus toteutus;
    private Date paatospaivamaara;
    private String ryhmaoid;
    private String ryhmanNimi;
    private boolean esikatseltavissa;
    private boolean ainepainoitteinen;
    private Date perusteenVoimassaoloAlkaa;
    private Date perusteenVoimassaoloLoppuu;
    private boolean tuoPohjanOpintojaksot;
    private boolean tuoPohjanOppimaarat;
    private Date perusteDataTuontiPvm = new Date();
    private Date viimeisinSyncPvm;
    private Date viimeisinJulkaisuAika;
}
