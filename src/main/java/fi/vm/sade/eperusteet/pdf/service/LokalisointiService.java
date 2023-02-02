package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.dto.LokalisointiDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface LokalisointiService {
    @PreAuthorize("permitAll()")
    LokalisointiDto get(String key, String locale);
}
