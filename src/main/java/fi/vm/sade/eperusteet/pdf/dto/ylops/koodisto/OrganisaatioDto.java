package fi.vm.sade.eperusteet.pdf.dto.ylops.koodisto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganisaatioDto {
    private String oid;
    private List<String> tyypit;
    private LokalisoituTekstiDto nimi;
}
