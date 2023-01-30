package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.domain.amosaa.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koodisto.KoodistoKoodiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.liite.LiiteDto;
import fi.vm.sade.eperusteet.utils.domain.utils.Kieli;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpetussuunnitelmaDto extends OpetussuunnitelmaBaseDto {
    private String kommentti;
    private Set<Kieli> julkaisukielet;
    private Set<LiiteDto> liitteet;
    private Date paatospaivamaara;
    private Date voimaantulo;
    private Date voimassaoloLoppuu;
    private String hyvaksyja;
    private String paatosnumero;
    private String suoritustapa;
    private boolean esikatseltavissa;
    private Set<String> tutkintonimikkeet = new HashSet<>();
    private Set<String> osaamisalat = new HashSet<>();
    private String oppilaitosTyyppiKoodiUri;
    private KoodistoKoodiDto oppilaitosTyyppiKoodi;
    private KoulutusTyyppi koulutustyyppi;
    private Date viimeisinJulkaisuAika;
}
