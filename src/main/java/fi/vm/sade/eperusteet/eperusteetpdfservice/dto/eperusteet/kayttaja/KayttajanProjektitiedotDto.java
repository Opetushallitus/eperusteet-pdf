package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.kayttaja;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KayttajanProjektitiedotDto {
    Long perusteprojekti;
    String organisaatioOid;
    String tehtavanimike;
    Boolean passivoitu;
}
