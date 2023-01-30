package fi.vm.sade.eperusteet.pdf.dto.amosaa.kayttaja;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true )
public class KayttooikeusKayttajaDto {
    private String oid;
    private String etunimet;
    private String kutsumanimi;
    private String sukunimi;
}
