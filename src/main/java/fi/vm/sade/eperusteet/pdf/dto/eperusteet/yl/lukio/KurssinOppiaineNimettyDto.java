package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.Lokalisoitava;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class KurssinOppiaineNimettyDto extends KurssinOppiaineDto implements Lokalisoitava {
    protected LokalisoituTekstiDto oppiaineNimi;

    public KurssinOppiaineNimettyDto(Long oppiaineId, Integer jarjestys, Long oppiaineNimiId) {
        super(oppiaineId, jarjestys);
        this.oppiaineNimi = LokalisoituTekstiDto.localizeLaterById(oppiaineNimiId);
    }

    @Override
    public Stream<LokalisoituTekstiDto> lokalisoitavatTekstit() {
        return Stream.of(oppiaineNimi);
    }
}
