package fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.Reference;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenalaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaiteenalaOsaViiteDto {
    private Long id;
    private Integer jarjestys;

    @JsonProperty("_taiteenala")
    private Reference taiteenala;

    @JsonProperty("tutkinnonOsa")
    private TaiteenalaDto taiteenalaDto;

    private Date muokattu;
    private LokalisoituTekstiDto nimi;

    public TaiteenalaOsaViiteDto(Integer jarjestys, LokalisoituTekstiDto nimi) {
        this.jarjestys = jarjestys;
        this.nimi = nimi;
    }
}
