package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.Lokalisoitava;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class KurssinOppiaineTarkasteluDto extends KurssinOppiaineNimettyDto {
    private OppiaineVanhempiDto vanhempi;
    private LokalisoituTekstiDto kurssiTyyppinKuvaus;

    public KurssinOppiaineTarkasteluDto(Long oppiaineId, Integer jarjestys, Long oppiaineNimiId,
                                        OppiaineVanhempiDto vanhempi, Long kurssiTyypinKuvausId) {
        super(oppiaineId, jarjestys, oppiaineNimiId);
        this.vanhempi = vanhempi;
        this.kurssiTyyppinKuvaus = LokalisoituTekstiDto.localizeLaterById(kurssiTyypinKuvausId);
    }

    @Override
    public Stream<LokalisoituTekstiDto> lokalisoitavatTekstit() {
        return Lokalisoitava.of(vanhempi).and(kurssiTyyppinKuvaus)
                .and(super.lokalisoitavatTekstit()).lokalisoitavatTekstit();
    }
}
