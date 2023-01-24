package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.yl.lukio;

import com.sun.istack.NotNull;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.UpdateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class LukioAihekokonaisuusMuokkausDto extends UpdateDto<LukioAihekokonaisuusMuokkausDto> implements Serializable {
    @NotNull
    private Long id;
    @NotNull
    private LokalisoituTekstiDto otsikko;
    private Optional<LokalisoituTekstiDto> yleiskuvaus;
    private Long jnro;
}
