package fi.vm.sade.eperusteet.pdf.dto.common;

import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kuvatyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneratorData {
    private Long id;
    private Long dokumenttiId;
    private Kieli kieli;
    private DokumenttiTyyppi tyyppi;
    private Long ktId;

    public static GeneratorData of(DokumenttiTyyppi tyyppi) {
        GeneratorData generatorData = new GeneratorData();
        generatorData.setTyyppi(tyyppi);
        return generatorData;
    }

    public static GeneratorData of(Long perusteId, Long dokumenttiId, Kieli kieli, DokumenttiTyyppi tyyppi, Long ktId) {
        GeneratorData generatorData = new GeneratorData();
        generatorData.setId(perusteId);
        generatorData.setDokumenttiId(dokumenttiId);
        generatorData.setKieli(kieli);
        generatorData.setKtId(ktId);
        generatorData.setTyyppi(tyyppi);
        return generatorData;
    }

    public Map<String, ?> uriParameters(Kuvatyyppi kuvatyyppi) {
        if (ktId != null) {
            return Map.of("opsId", id, "kieli", kieli, "tyyppi", kuvatyyppi, "ktId", ktId);
        }
        return Map.of("opsId", id, "kieli", kieli, "tyyppi", kuvatyyppi);
    }
}
