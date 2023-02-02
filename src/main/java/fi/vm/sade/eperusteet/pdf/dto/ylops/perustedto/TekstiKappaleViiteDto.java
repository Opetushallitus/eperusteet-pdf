package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TekstiKappaleViiteDto {
    private Long id;
    @JsonProperty(value = "perusteenOsa")
    private TekstiKappaleDto tekstiKappale;
    private List<TekstiKappaleViiteDto> lapset;
}
