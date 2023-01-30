package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.vm.sade.eperusteet.pdf.domain.amosaa.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.CachedPerusteKevytDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.utils.domain.utils.Kieli;
import fi.vm.sade.eperusteet.utils.domain.utils.Tila;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpetussuunnitelmaTilastoDto {
    public static SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");

    private LokalisoituTekstiDto nimi;
    private Tila tila;
    private Date paatospaivamaara;
    private Date voimaantulo;
    private KoulutustoimijaBaseDto koulutustoimija;
    private Set<Kieli> julkaisukielet;
    private Set<JulkaisuKevytDto> julkaisut;
    private KoulutusTyyppi koulutustyyppi;

    @JsonIgnore
    private CachedPerusteKevytDto peruste;

    public KoulutusTyyppi getKoulutustyyppi() {
        if (peruste != null) {
            return peruste.getKoulutustyyppi();
        }
        return koulutustyyppi;
    }

    public LokalisoituTekstiDto getPerusteNimi() {
        if (peruste != null) {
            return peruste.getNimi();
        }

        return null;
    }

    public Long getPerusteId() {
        if (peruste != null) {
            return peruste.getPerusteId();
        }

        return null;
    }

    public Integer getJulkaisuVuosi() {
        if (CollectionUtils.isNotEmpty(julkaisut)) {
            return julkaisut.stream()
                    .sorted(Comparator.comparing(JulkaisuKevytDto::getLuotu))
                    .map(julkaisu -> Integer.parseInt(getYearFormat.format(julkaisu.getLuotu())))
                    .findFirst()
                    .orElse(null);
        }

        if (tila.equals(Tila.JULKAISTU) && voimaantulo != null) {
            return Integer.parseInt(getYearFormat.format(voimaantulo));
        }

        return null;
    }
}
