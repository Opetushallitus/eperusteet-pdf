package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LukioAbstraktiOppiaineTuontiDto implements Serializable {
    private LokalisoituTekstiDto nimi;
    private UUID tunniste;
}
