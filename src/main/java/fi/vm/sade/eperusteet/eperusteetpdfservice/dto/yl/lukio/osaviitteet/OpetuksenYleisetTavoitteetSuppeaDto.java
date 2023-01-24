package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.yl.lukio.osaviitteet;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteenOsaDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("opetuksenyleisettavoitteet")
public class OpetuksenYleisetTavoitteetSuppeaDto extends PerusteenOsaDto.Suppea {
    private LokalisoituTekstiDto otsikko;
}
