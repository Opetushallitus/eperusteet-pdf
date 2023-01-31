package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KoodiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KotoTaitotasoDto {
    private Long id;
    private String koodiUri;
    private KoodiDto nimi;
    private LokalisoituTekstiDto tavoiteTarkennus;
    private LokalisoituTekstiDto sisaltoTarkennus;


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
}
