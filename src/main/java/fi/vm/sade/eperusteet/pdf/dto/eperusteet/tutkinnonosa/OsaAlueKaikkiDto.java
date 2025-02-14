package fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.GeneerinenArviointiasteikkoKaikkiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OsaAlueKaikkiDto extends OsaAlueDto {

    private GeneerinenArviointiasteikkoKaikkiDto arviointi;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Osaamistavoite2020Dto pakollisetOsaamistavoitteet;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Osaamistavoite2020Dto valinnaisetOsaamistavoitteet;

    @Deprecated
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<OsaamistavoiteLaajaDto> osaamistavoitteet = new ArrayList<>();
}
