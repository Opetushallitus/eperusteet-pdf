package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.liite;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.LiiteTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class LiiteDto extends LiiteBaseDto {
    private LiiteTyyppi tyyppi;
    private Date luotu;
    private String mime;
    private String lisatieto;
}
