package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.tutkinnonrakenne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KayttajanPerusteprojektiDto {
    Boolean passivoitu;
    String tehtavanimike;
    String organisaatioOid;
    Long id;
}
