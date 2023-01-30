package fi.vm.sade.eperusteet.pdf.dto.amosaa.ops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiedoteDto {
    private Long id;
    private String otsikko;
    private String teksti;
    private Boolean julkinen;
    private Boolean tarkea;
    private Date luotu;
    private String luoja;
    private Date muokattu;
    private String muokkaaja;
}
