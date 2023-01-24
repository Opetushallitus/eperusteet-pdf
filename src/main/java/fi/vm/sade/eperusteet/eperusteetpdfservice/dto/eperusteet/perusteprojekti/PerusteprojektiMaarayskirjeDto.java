package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.perusteprojekti;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.PerusteMaarayskirjeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteprojektiMaarayskirjeDto {
    private PerusteMaarayskirjeDto peruste;
}
