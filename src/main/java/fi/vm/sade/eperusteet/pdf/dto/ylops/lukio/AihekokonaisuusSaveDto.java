package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AihekokonaisuusSaveDto implements Serializable {
    private LokalisoituTekstiDto otsikko;
    private LokalisoituTekstiDto yleiskuvaus;
}
