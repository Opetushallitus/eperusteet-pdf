package fi.vm.sade.eperusteet.pdf.dto.eperusteet.perusteprojekti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.KoulutustyyppiToteutus;
import fi.vm.sade.eperusteet.pdf.dto.enums.LaajuusYksikko;
import fi.vm.sade.eperusteet.pdf.dto.enums.PerusteTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.ProjektiTila;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteAikatauluDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
public class PerusteprojektiLuontiDto extends PerusteprojektiDto {
    private String koulutustyyppi;
    private LaajuusYksikko laajuusYksikko;
    private Long perusteId;
    private ProjektiTila tila;
    private KoulutustyyppiToteutus toteutus;
    private PerusteTyyppi tyyppi;
    private String ryhmaOid;
    private boolean reforminMukainen = true;
    private Date voimassaoloAlkaa;
    private Date lausuntakierrosAlkaa;
    private Date johtokunnanKasittely;
    private Set<PerusteAikatauluDto> perusteenAikataulut;
    private LokalisoituTekstiDto kuvaus;

    public PerusteprojektiLuontiDto(String koulutustyyppi, LaajuusYksikko laajuusYksikko, Long perusteId, ProjektiTila tila, PerusteTyyppi tyyppi, String ryhmaOid) {
        this.koulutustyyppi = koulutustyyppi;
        this.laajuusYksikko = laajuusYksikko;
        this.perusteId = perusteId;
        this.tila = tila;
        this.tyyppi = tyyppi;
        this.ryhmaOid = ryhmaOid;
    }
}
