package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KevytTekstiKappaleDto {
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto teksti;
    private Integer jnro;

    static public KevytTekstiKappaleDto of(LokalisoituTekstiDto nimi, LokalisoituTekstiDto teksti) {
        KevytTekstiKappaleDto result = new KevytTekstiKappaleDto();
        result.setNimi(nimi);
        result.setTeksti(teksti);
        return result;
    }

    static public KevytTekstiKappaleDto of(String nimi, String teksti) {
        KevytTekstiKappaleDto result = new KevytTekstiKappaleDto();
        result.setNimi(LokalisoituTekstiDto.of(nimi));
        result.setTeksti(LokalisoituTekstiDto.of(teksti));
        return result;
    }
}
