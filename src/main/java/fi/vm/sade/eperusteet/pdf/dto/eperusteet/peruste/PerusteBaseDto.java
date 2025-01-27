package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.KoulutustyyppiToteutus;
import fi.vm.sade.eperusteet.pdf.dto.enums.PerusteTila;
import fi.vm.sade.eperusteet.pdf.dto.enums.PerusteTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.KoulutusDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.MuutosmaaraysDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.PerusteIdentifiable;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.annotation.Identifiable;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.pdf.utils.PerusteUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PerusteBaseDto implements Serializable, PerusteIdentifiable, Identifiable {

    private Long id;
    @JsonIgnore
    private Integer revision;

    private PerusteVersionDto globalVersion;

    private LokalisoituTekstiDto nimi;
    private String koulutustyyppi;

    private KoulutustyyppiToteutus toteutus;
    private Set<KoulutusDto> koulutukset;
    private Set<Kieli> kielet;
    private LokalisoituTekstiDto kuvaus;
    private MaarayskirjeDto maarayskirje;
    private List<MuutosmaaraysDto> muutosmaaraykset = new ArrayList<>();
    private String diaarinumero;
    private Date voimassaoloAlkaa;
    private Date siirtymaPaattyy;
    private Date voimassaoloLoppuu;
    private Date paatospvm;
    private Optional<Date> viimeisinJulkaisuAika;
    private Date luotu;
    private Date muokattu;
    private PerusteTila tila;
    private PerusteTyyppi tyyppi;
    private Boolean koulutusvienti;
    private Set<String> korvattavatDiaarinumerot;
    private Set<KoodiDto> osaamisalat;

    // Tuodaan kvliitteestä
    private LokalisoituTekstiDto tyotehtavatJoissaVoiToimia;
    private LokalisoituTekstiDto suorittaneenOsaaminen;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<TutkintonimikeKoodiDto> tutkintonimikkeet;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<PerusteKevytDto> oppaanPerusteet;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<KoulutusTyyppi> oppaanKoulutustyypit;

    private Set<PerusteAikatauluDto> perusteenAikataulut;

    @Override
    public KoulutustyyppiToteutus getToteutus() {
        return PerusteUtils.getToteutus(this.toteutus, this.koulutustyyppi, this.tyyppi);
    }
}
