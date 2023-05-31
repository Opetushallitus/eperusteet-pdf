package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;

public interface DokumenttiService {

    void generateForEperusteet(Long dokumenttiId, Kieli kieli, String perusteJson);

    void generateForEperusteetKvLiite(Long dokumenttiId, Kieli kieli, String perusteJson);

    void generateForAmosaa(Long dokumenttiId, Kieli kieli, Long ktId, String opsJson);

    void generateForYlops(Long dokumenttiId, Kieli kieli, String opsJson);
}
