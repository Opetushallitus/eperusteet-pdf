package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet;

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
    private String nimi;
    private String muokkaaja;
    private String sisalto;
    private Date luotu;
    private Date muokattu;
    private Long id;
    private Long ylinId;
    private Long parentId;
    private Long perusteprojektiId;
    private Boolean poistettu;
    private Long perusteenOsaId;
    private String suoritustapa;
}
