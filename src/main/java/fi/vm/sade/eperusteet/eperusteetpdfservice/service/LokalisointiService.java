package fi.vm.sade.eperusteet.eperusteetpdfservice.service;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.LokalisointiDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface LokalisointiService {
    @PreAuthorize("permitAll()")
    LokalisointiDto get(String key, String locale);
}
