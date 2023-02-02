package fi.vm.sade.eperusteet.pdf.dto.ylops.liite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiiteDto {
    private UUID id;
    private String tyyppi;
    private String nimi;
    private Date luotu;
}
