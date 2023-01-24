package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.validointi;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RakenneOngelmaDto {
    public String ongelma;
    public LokalisoituTekstiDto ryhma;
}
