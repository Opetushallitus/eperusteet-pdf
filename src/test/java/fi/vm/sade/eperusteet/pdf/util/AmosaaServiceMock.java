package fi.vm.sade.eperusteet.pdf.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.service.external.AmosaaService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class AmosaaServiceMock implements AmosaaService {
    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();

    @Override
    public PerusteKaikkiDto getPerusteKaikkiDto(Long cachedPerusteId) {
        try {
            Resource resource = new ClassPathResource("material/amosaa-peruste.json");
            ObjectNode fileData = objectMapper.readValue(resource.getFile(), ObjectNode.class);
            return objectMapper.readValue(fileData.toString(), PerusteKaikkiDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArviointiAsteikkoDto getArviointiasteikko(Long arviointiAsteikkoId) {
        return new ArviointiAsteikkoDto();
    }
}
