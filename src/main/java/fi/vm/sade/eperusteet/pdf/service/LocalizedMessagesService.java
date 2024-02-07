package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;

public interface LocalizedMessagesService {
    String translate(String key, Kieli kieli);
}
