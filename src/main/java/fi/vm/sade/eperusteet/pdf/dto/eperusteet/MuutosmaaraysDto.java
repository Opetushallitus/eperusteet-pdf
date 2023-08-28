package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.liite.LiiteBaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuutosmaaraysDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto url;
    private Map<Kieli, LiiteBaseDto> liitteet = new HashMap<>();

    public LokalisoituTekstiDto getUrl(String service, Long perusteId) {
        if (url != null) {
            return url;
        }

        return LokalisoituTekstiDto.of(liitteet.keySet().stream()
                        .collect(Collectors.toMap((kieli -> kieli), (kieli -> String.format("%s/api/perusteet/%d/liitteet/%s",
                                service,
                                perusteId,
                                liitteet.get(kieli).getId().toString()
                                )))));
    }
}
