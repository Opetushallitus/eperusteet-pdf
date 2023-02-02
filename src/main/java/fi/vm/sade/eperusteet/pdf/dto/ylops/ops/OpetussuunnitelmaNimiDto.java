package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.Reference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpetussuunnitelmaNimiDto implements Serializable {
    private Long id;
    private LokalisoituTekstiDto nimi;

    @JsonProperty("_pohja")
    private Reference pohja;
}
