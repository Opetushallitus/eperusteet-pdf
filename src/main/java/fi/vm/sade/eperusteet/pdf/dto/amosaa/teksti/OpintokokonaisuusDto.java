package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.LaajuusYksikko;
import fi.vm.sade.eperusteet.pdf.dto.enums.OpintokokonaisuusTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpintokokonaisuusDto {
    private Long id;
    private String nimiKoodi;
    private String koodi;
    private LokalisoituTekstiDto kuvaus;
    private BigDecimal laajuus;
    private LaajuusYksikko laajuusYksikko;
    private Integer minimilaajuus;
    private OpintokokonaisuusTyyppi tyyppi;
    private LokalisoituTekstiDto opetuksenTavoiteOtsikko;
    private List<OpintokokonaisuusTavoiteDto> tavoitteet = new ArrayList<>();
    private LokalisoituTekstiDto tavoitteidenKuvaus;
    private LokalisoituTekstiDto keskeisetSisallot;
    private LokalisoituTekstiDto arvioinninKuvaus;
    private List<OpintokokonaisuusArviointiDto> arvioinnit = new ArrayList<>();

    public String getKoodiArvo() {
        if (koodi != null) {
            return koodi.substring(koodi.indexOf("_") + 1);
        }

        return null;
    }

}
