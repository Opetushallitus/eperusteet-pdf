package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KotoTaitotasoDto {
    private Long id;
    private KoodiDto nimi;
    private String koodiUri;
    private LokalisoituTekstiDto tavoitteet;
    private LokalisoituTekstiDto kielenkayttotarkoitus;
    private LokalisoituTekstiDto aihealueet;
    private LokalisoituTekstiDto viestintataidot;
    private LokalisoituTekstiDto opiskelijantaidot;
    private LokalisoituTekstiDto opiskelijanTyoelamataidot;
    private Integer tyoelamaOpintoMinimiLaajuus;
    private Integer tyoelamaOpintoMaksimiLaajuus;
    private LokalisoituTekstiDto suullinenVastaanottaminen;
    private LokalisoituTekstiDto suullinenTuottaminen;
    private LokalisoituTekstiDto vuorovaikutusJaMediaatio;
    private LokalisoituTekstiDto tavoiteTarkennus;
    private LokalisoituTekstiDto sisaltoTarkennus;
}
