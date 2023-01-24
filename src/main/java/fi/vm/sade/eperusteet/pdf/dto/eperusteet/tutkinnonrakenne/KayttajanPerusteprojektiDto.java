package fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne;

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
