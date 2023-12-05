package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import org.springframework.security.access.prepost.PreAuthorize;

public interface LocalizedMessagesService {
    String translate(String key, Kieli kieli);
}
