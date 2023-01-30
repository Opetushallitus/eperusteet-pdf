package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

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
@NoArgsConstructor
@AllArgsConstructor
public class OpetussuunnitelmaJulkinenDto extends OpetussuunnitelmaBaseDto {
    private String kommentti;
    private Set<Kieli> julkaisukielet;
    private KoulutustoimijaBaseDto koulutustoimija;
    private Set<LiiteDto> liitteet;
    private Date paatospaivamaara;
    private Date voimaantulo;
    private String hyvaksyja;
    private String paatosnumero;
    private String suoritustapa;
    private Set<String> tutkintonimikkeet = new HashSet<>();
    private Set<String> osaamisalat = new HashSet<>();
}
