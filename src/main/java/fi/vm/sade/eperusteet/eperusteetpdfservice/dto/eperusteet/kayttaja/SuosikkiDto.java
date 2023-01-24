package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.kayttaja;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuosikkiDto implements Serializable {
    private Long id;
    private String nimi;
    private String sisalto;
    private Date lisatty;
}
