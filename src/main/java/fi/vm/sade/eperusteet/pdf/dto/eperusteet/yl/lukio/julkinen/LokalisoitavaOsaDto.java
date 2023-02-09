package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio.julkinen;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.Lokalisoitava;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LokalisoitavaOsaDto implements Serializable, Lokalisoitava {
    private LokalisoituTekstiDto otsikko;
    private LokalisoituTekstiDto teksti;

    @Override
    public Stream<LokalisoituTekstiDto> lokalisoitavatTekstit() {
        return Stream.of(otsikko, teksti);
    }

    public static LokalisoitavaOsaDto localizedLaterByIds(Long otsikkoId, Long tekstiId) {
        if (otsikkoId != null || tekstiId != null) {
            LokalisoitavaOsaDto dto = new LokalisoitavaOsaDto();
            dto.setOtsikko(LokalisoituTekstiDto.localizeLaterById(otsikkoId));
            dto.setTeksti(LokalisoituTekstiDto.localizeLaterById(tekstiId));
            return dto;
        }
        return null;
    }
}
