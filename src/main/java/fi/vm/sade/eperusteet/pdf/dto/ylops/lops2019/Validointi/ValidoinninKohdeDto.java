package fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.Validointi;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.ylops.validointi.ValidationCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidoinninKohdeDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private String kuvaus;
    private ValidationCategory targetClass;
    private boolean failed;
    private boolean isFatal;
    private Map<String, Object> meta;

}
