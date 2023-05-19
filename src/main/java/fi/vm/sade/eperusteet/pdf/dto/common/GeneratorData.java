package fi.vm.sade.eperusteet.pdf.dto.common;

import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.GeneratorVersion;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public static GeneratorData of(Long perusteId, Long dokumenttiId, Kieli kieli, DokumenttiTyyppi tyyppi, GeneratorVersion versio, Long ktId) {
        GeneratorData generatorData = new GeneratorData();
        generatorData.setId(perusteId);
        generatorData.setDokumenttiId(dokumenttiId);
        generatorData.setKieli(kieli);
        generatorData.setKtId(ktId);
        if (GeneratorVersion.KVLIITE.equals(versio)) {
            generatorData.setTyyppi(DokumenttiTyyppi.KVLIITE);
        } else {
            generatorData.setTyyppi(tyyppi);
        }
        return generatorData;
    }
}
