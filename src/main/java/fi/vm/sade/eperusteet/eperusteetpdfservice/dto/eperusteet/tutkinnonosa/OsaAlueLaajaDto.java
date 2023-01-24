package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.tutkinnonosa;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.Arviointi2020Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OsaAlueLaajaDto extends OsaAlueDto {
    private Arviointi2020Dto arviointi;
    private Osaamistavoite2020Dto pakollisetOsaamistavoitteet;
    private Osaamistavoite2020Dto valinnaisetOsaamistavoitteet;

    @Deprecated
    private List<OsaamistavoiteDto> osaamistavoitteet;
}
