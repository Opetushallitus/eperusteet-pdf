package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.Reference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteenOsaTyoryhmaDto {
    private Long id;
    private Reference perusteprojekti;
    private Reference perusteenosa;
    private String nimi;
}
