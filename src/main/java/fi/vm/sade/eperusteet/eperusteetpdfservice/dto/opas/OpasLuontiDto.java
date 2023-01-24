package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.opas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteKevytDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class OpasLuontiDto extends OpasDto {

    private Long pohjaId;
    private Set<PerusteKevytDto> oppaanPerusteet;
    private Set<KoulutusTyyppi> oppaanKoulutustyypit;
    private LokalisoituTekstiDto lokalisoituNimi;

}
