package fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.ylops.KoodiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lops2019LaajaAlainenDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto kuvaus;
    private KoodiDto koodi;

    static public Lops2019LaajaAlainenDto of(String koodisto, String koodiArvo, String nimi, Kieli kieli) {
        Lops2019LaajaAlainenDto lao = new Lops2019LaajaAlainenDto();
        lao.setKoodi(KoodiDto.of(koodisto, koodiArvo));
        lao.setNimi(LokalisoituTekstiDto.of(kieli, nimi));
        return lao;
    }
}
