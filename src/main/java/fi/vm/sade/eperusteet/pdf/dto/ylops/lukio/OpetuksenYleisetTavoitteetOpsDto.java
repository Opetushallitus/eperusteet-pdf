package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lukio.OpetuksenYleisetTavoitteetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class OpetuksenYleisetTavoitteetOpsDto extends OpetuksenYleisetTavoitteetDto
        implements PerusteeseenViittaava<OpetuksenYleisetTavoitteetDto> {
    private OpetuksenYleisetTavoitteetDto perusteen;
}
