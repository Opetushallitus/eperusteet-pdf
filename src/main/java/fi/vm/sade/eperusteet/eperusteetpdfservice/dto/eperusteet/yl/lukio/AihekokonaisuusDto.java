package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.yl.lukio;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AihekokonaisuusDto implements Serializable {
    private UUID tunniste;
    private Long id;
    private Long jnro;
    private LokalisoituTekstiDto otsikko;
    private LokalisoituTekstiDto yleiskuvaus;
}
