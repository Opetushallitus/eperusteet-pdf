package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisointiDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface LokalisointiService {
    LokalisointiDto get(String key, String locale);
}
