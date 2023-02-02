package fi.vm.sade.eperusteet.pdf.dto.ylops.teksti;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Tila;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TekstiKappaleKevytDto {
    private Long id;
    private Date luotu;
    private Date muokattu;
    private String muokkaaja;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String muokkaajanNimi;
    private LokalisoituTekstiDto nimi;
    private Tila tila;
    private UUID tunniste;
    private Boolean pakollinen;
    private Boolean valmis;
}
