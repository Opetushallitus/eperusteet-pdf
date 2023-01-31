package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.AmmattitaitovaatimusKohdealueetDto;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@JsonTypeName("tutkinnonosa")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TutkinnonOsaDto extends PerusteenOsaDto.Laaja {
    private LokalisoituTekstiDto tavoitteet;
    private ArviointiDto arviointi;
    private List<AmmattitaitovaatimusKohdealueetDto> ammattitaitovaatimuksetLista;
    private LokalisoituTekstiDto ammattitaitovaatimukset;
    private LokalisoituTekstiDto ammattitaidonOsoittamistavat;
    private LokalisoituTekstiDto kuvaus;
    private String koodiUri;
    private String koodiArvo;
    private List<OsaAlueDto> osaAlueet;
    private List<KevytTekstiKappaleDto> vapaatTekstit;
    private TutkinnonOsaTyyppi tyyppi;

    public TutkinnonOsaDto() {
    }

    public TutkinnonOsaDto(LokalisoituTekstiDto nimi, PerusteTila tila, PerusteenOsaTunniste tunniste) {
        super(nimi, tila, tunniste);
    }

    public String getOsanTyyppi() {
        return "tutkinnonosa";
    }

}
