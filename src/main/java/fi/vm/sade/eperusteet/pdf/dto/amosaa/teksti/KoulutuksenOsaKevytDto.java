package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.KooditettuDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KoulutuksenOsaKevytDto implements KooditettuDto {

    private Long id;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto kooditettuNimi;
    private String nimiKoodi;

    @Override
    public void setKooditettu(LokalisoituTekstiDto kooditettu) {
        this.kooditettuNimi = kooditettu;
    }

    public LokalisoituTekstiDto getNimi() {
        if (kooditettuNimi != null) {
            return kooditettuNimi;
        }

        return nimi;
    }

    public String getKoodiArvo() {
        if (getNimiKoodi() != null) {
            return getNimiKoodi().substring(getNimiKoodi().indexOf("_") + 1);
        }

        return null;
    }

}
