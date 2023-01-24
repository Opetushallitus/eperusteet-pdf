package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.yl.lukio.osaviitteet;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.PerusteenOsaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("rakenne")
@NoArgsConstructor
public class LukioOpetussuunnitelmaRakenneSuppeaDto extends PerusteenOsaDto.Suppea {
}
