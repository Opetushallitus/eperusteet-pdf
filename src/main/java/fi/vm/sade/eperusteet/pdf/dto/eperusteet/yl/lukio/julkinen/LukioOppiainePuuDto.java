package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio.julkinen;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.Lokalisoitava;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LukioOppiainePuuDto implements Lokalisoitava {
    private Long perusteId;
    private List<LukioOppiaineOppimaaraNodeDto> oppiaineet = new ArrayList<>();

    public LukioOppiainePuuDto(Long perusteId) {
        this.perusteId = perusteId;
    }

    @Override
    public Stream<LokalisoituTekstiDto> lokalisoitavatTekstit() {
        return Lokalisoitava.of(oppiaineet).lokalisoitavatTekstit();
    }
}
