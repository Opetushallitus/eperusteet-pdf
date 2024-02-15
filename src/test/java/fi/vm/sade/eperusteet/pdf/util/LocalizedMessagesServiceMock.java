package fi.vm.sade.eperusteet.pdf.util;

import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.service.LocalizedMessagesService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class LocalizedMessagesServiceMock implements LocalizedMessagesService {
    @Override
    public String translate(String key, Kieli kieli) {
        return "";
    }
}
