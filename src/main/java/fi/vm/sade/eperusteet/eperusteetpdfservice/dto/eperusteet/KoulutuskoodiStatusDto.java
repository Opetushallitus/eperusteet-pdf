package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.perusteprojekti.PerusteprojektiListausDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KoulutuskoodiStatusDto {
    private Long id;
    private PerusteprojektiListausDto perusteprojekti;
    private Date lastCheck;
    private List<KoulutuskoodiStatusInfoDto> infot;
    private boolean kooditOk;
}
