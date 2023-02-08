package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisointiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizedMessagesServiceImpl implements LocalizedMessagesService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LokalisointiService lokalisointiService;

    @Override
    public String translate(String key, Kieli kieli) {
        // Koitetaan hakea lokalisointipalvelimelta käänös
        LokalisointiDto valueDto = lokalisointiService.get(key, kieli.toString());
        if (valueDto != null) {
            return valueDto.getValue();
        }

        try {
            return messageSource.getMessage(key, null, Locale.forLanguageTag(kieli.toString()));
        } catch (NoSuchMessageException ex) {
            return "[" + kieli + " " + key + "]";
        }
    }
}
