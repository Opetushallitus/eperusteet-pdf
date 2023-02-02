package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpetuksenYleisetTavoitteetUpdateDto implements Serializable {
    private LokalisoituTekstiDto otsikko;
    private LokalisoituTekstiDto kuvaus;
}
