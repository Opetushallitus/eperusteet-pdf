package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lukio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LukioPerusteSisaltoDto implements Serializable {
    private Long id;
    private List<LukioPerusteTekstikappaleViiteDto> lapset;
}
