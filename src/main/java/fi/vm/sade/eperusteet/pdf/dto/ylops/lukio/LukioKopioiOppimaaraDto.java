package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LukioKopioiOppimaaraDto {
    @NotNull
    private LokalisoituTekstiDto nimi;
    @NotNull
    private UUID tunniste;
    private String kieliKoodiUri;
    private String kieliKoodiArvo;
    private LokalisoituTekstiDto kieli;
}
