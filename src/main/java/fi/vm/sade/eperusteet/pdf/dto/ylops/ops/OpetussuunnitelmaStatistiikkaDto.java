package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpetussuunnitelmaStatistiikkaDto implements Serializable {
    private Map<String, Long> kielittain = new HashMap<>();
    private Map<String, Long> koulutustyypeittain = new HashMap<>();
    private Map<String, Long> tasoittain = new HashMap<>();
    private Map<String, Long> tiloittain = new HashMap<>();
}
