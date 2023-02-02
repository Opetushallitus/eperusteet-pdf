package fi.vm.sade.eperusteet.pdf.dto.ylops;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaBaseDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaNimiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.PerusteInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpetussuunnitelmaExportDto extends OpetussuunnitelmaBaseDto {
    private PerusteInfoDto peruste;
    private OpetussuunnitelmaNimiDto pohja;
    private TekstiKappaleViiteExportDto.Puu tekstit;
}
