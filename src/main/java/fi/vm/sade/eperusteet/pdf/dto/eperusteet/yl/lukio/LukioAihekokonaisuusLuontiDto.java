package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.UpdateDto;
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
    private LokalisoituTekstiDto otsikko;
    private Optional<LokalisoituTekstiDto> yleiskuvaus;
    private Long jnro;
}
