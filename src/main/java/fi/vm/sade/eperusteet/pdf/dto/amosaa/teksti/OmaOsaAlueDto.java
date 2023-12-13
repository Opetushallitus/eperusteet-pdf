package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KevytTekstiKappaleDto;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.OmaOsaAlueTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OmaOsaAlueDto {
    private Long id;
    private OmaOsaAlueTyyppi tyyppi;
    private LokalisoituTekstiDto nimi;
    private String perusteenOsaAlueKoodi;
    private Long perusteenOsaAlueId;
    private boolean piilotettu;
    private List<OmaOsaAlueToteutusDto> toteutukset = new ArrayList<>();
    private PaikallisetAmmattitaitovaatimukset2019Dto osaamistavoitteet = new PaikallisetAmmattitaitovaatimukset2019Dto();
    private Long geneerinenarviointi;
    private Integer laajuus;
    private LokalisoituTekstiDto paikallinenTarkennus;
    private List<KevytTekstiKappaleDto> vapaat = new ArrayList<>();

    public int sort() {
        switch (tyyppi) {
            case PAKOLLINEN:
                return 0;
            case VALINNAINEN:
                return 1;
            case PAIKALLINEN:
                return 2;
            default:
                return 3;
        }
    }

    @JsonIgnore
    public String getKoodiArvo() {
        return perusteenOsaAlueKoodi != null ? perusteenOsaAlueKoodi.split("_")[1] : null;
    }
}
