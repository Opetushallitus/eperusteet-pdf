package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.ReferenceableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpetuksenKohdealueDto implements ReferenceableDto {
    public Long id;
    public Optional<LokalisoituTekstiDto> nimi;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Optional<LokalisoituTekstiDto> kuvaus;
}
