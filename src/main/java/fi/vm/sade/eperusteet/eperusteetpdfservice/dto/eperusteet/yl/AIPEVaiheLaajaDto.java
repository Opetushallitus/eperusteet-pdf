package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.yl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AIPEVaiheLaajaDto extends AIPEVaiheDto {
    private List<AIPEOppiaineLaajaDto> oppiaineet;
}
