package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import fi.vm.sade.eperusteet.pdf.domain.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.liite.LiiteBaseDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuutosmaaraysDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto url;
    private Map<Kieli, LiiteBaseDto> liitteet = new HashMap<>();
}
