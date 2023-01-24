package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.yl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIPEVaiheBaseDto implements AIPEHasId {
    private Long id;
    private UUID tunniste;
    private Date luotu;
    private Date muokattu;
}
