package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.dto.common.KoodistoKoodiDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface KoodistoClient {

    List<KoodistoKoodiDto> getAll(String koodisto);

    KoodistoKoodiDto get(String koodistoUri, String koodiUri);

    KoodistoKoodiDto getByUri(String uri);
}
