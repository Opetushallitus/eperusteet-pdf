package fi.vm.sade.eperusteet.pdf.dto.eperusteet.tilastot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpetussuunnitelmaTilastoDto {

    private List<Object> data;
    @JsonProperty("kokonaismäärä")
    private Integer kokonaismaara;
    private Integer sivu;
    private Integer sivuja;
    private Integer sivukoko;

}
