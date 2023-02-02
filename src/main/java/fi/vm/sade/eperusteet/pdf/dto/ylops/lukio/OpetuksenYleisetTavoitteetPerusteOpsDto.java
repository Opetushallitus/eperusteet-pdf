package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lukio.OpetuksenYleisetTavoitteetDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OpetuksenYleisetTavoitteetPerusteOpsDto extends PerusteOpsDto<OpetuksenYleisetTavoitteetDto,
        OpetuksenYleisetTavoitteetOpsDto> {
    public OpetuksenYleisetTavoitteetPerusteOpsDto(OpetuksenYleisetTavoitteetDto perusteen, OpetuksenYleisetTavoitteetOpsDto paikallinen) {
        super(perusteen, paikallinen);
    }

    public OpetuksenYleisetTavoitteetPerusteOpsDto(OpetuksenYleisetTavoitteetOpsDto paikallinen) {
        super(paikallinen);
    }
}
