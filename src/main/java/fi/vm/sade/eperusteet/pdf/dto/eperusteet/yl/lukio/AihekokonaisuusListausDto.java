package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.Lokalisoitava;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AihekokonaisuusListausDto implements Serializable, Lokalisoitava {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto kuvaus;
    private Long jnro;
    private Date muokattu;

    public AihekokonaisuusListausDto(Long id, Long nimiId, Long kuvausId, Long jnro, Date muokattu) {
        this.id = id;
        this.nimi = LokalisoituTekstiDto.localizeLaterById(nimiId);
        this.kuvaus = LokalisoituTekstiDto.localizeLaterById(kuvausId);
        this.jnro = jnro;
        this.muokattu = muokattu;
    }

    @Override
    public Stream<LokalisoituTekstiDto> lokalisoitavatTekstit() {
        return Stream.of(nimi, kuvaus);
    }
}
