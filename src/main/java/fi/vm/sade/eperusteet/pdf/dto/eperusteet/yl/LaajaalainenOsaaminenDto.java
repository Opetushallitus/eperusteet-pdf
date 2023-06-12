package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.ReferenceableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaajaalainenOsaaminenDto implements ReferenceableDto, AIPEHasId {
    private Long id;
    private UUID tunniste;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto kuvaus;
    private Date muokattu;
}
