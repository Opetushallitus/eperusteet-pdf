package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.TiedoteJulkaisuPaikka;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKevytDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class TiedoteDto {
    private Long id;
    private Reference perusteprojekti;
    private PerusteKevytDto peruste; // Käytetään ainoastaan haettaessa tiedotteita
    private boolean julkinen;
    private boolean yleinen;
    private LokalisoituTekstiDto otsikko;
    private LokalisoituTekstiDto sisalto;
    private Set<TiedoteJulkaisuPaikka> julkaisupaikat;
    private Set<KoulutusTyyppi> koulutustyypit;
    private Set<PerusteKevytDto> perusteet;
    private Set<KoodiDto> tutkinnonosat;
    private Set<KoodiDto> osaamisalat;
    private Date luotu;
    private String luoja;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nimi;
    private Date muokattu;
    private String muokkaaja;
}
