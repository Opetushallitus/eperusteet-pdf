package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.vst;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KotoTaitotasoDto {
    private Long id;
    private KoodiDto nimi;
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
