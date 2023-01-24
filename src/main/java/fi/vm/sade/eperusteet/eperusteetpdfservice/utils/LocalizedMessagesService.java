package fi.vm.sade.eperusteet.eperusteetpdfservice.utils;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.Kieli;
import org.springframework.security.access.prepost.PreAuthorize;

public interface LocalizedMessagesService {
    @PreAuthorize("permitAll()")
    String translate(String key, Kieli kieli);
}
