package fi.vm.sade.eperusteet.pdf.dto.ylops;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaBaseDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaNimiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpsOppiaineExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpsVuosiluokkakokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.PerusteInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpetussuunnitelmaExportDto extends OpetussuunnitelmaBaseDto {
    private PerusteInfoDto peruste;
    private OpetussuunnitelmaNimiDto pohja;
    private TekstiKappaleViiteExportDto.Puu tekstit;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<OpsVuosiluokkakokonaisuusDto> vuosiluokkakokonaisuudet;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<OpsOppiaineExportDto> oppiaineet;
}
