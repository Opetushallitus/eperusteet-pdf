package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.yl.lukio;

import com.sun.istack.NotNull;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.UpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LukioAihekokonaisuusLuontiDto extends UpdateDto<LukioAihekokonaisuusLuontiDto> implements Serializable {
    @NotNull
    private LokalisoituTekstiDto otsikko;
    private Optional<LokalisoituTekstiDto> yleiskuvaus;
    private Long jnro;
}
