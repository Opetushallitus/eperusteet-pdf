package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.koodisto.KoodistoKoodiDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface KoodistoClient {

    @PreAuthorize("permitAll()")
    List<KoodistoKoodiDto> getAll(String koodisto);

    @PreAuthorize("permitAll()")
    List<KoodistoKoodiDto> getAll(String koodisto, boolean onlyValidKoodis);

    @PreAuthorize("permitAll()")
    KoodistoKoodiDto get(String koodistoUri, String koodiUri);

    @PreAuthorize("permitAll()")
    KoodistoKoodiDto get(String koodistoUri, String koodiUri, Long versio);
}
