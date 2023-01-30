package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KommenttiDto {
    private Long id;
    private Date luotu;
    private String luoja;
    private Date muokattu;
    private String muokkaaja;
    private Long tekstikappaleviiteId;
    private Long parentId;
    private Boolean poistettu;
    private String sisalto;
    private String nimi;
}
