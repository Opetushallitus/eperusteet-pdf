package fi.vm.sade.eperusteet.pdf.utils;

import fi.vm.sade.eperusteet.pdf.domain.enums.Kieli;
import org.springframework.security.access.prepost.PreAuthorize;

public interface LocalizedMessagesService {
    @PreAuthorize("permitAll()")
    String translate(String key, Kieli kieli);
}
