package fi.vm.sade.eperusteet.pdf.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.koodisto.OrganisaatioDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiKappaleViiteDto;
import fi.vm.sade.eperusteet.pdf.service.external.YlopsService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("test")
public class YlopsServiceMock implements YlopsService {
    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();

    @Override
    public OrganisaatioDto getOrganisaatio(String oid) {
        return null;
    }

    @Override
    public List<TekstiKappaleViiteDto.Matala> getTekstiKappaleViiteOriginals(Long opsId, Long viiteId) {
        return null;
    }

    @Override
    public TekstiKappaleViiteDto getLops2019PerusteTekstikappale(Long opsId, Long tekstikappaleId) {
        return null;
    }

    @Override
    public PerusteKaikkiDto getPerusteKaikkiDto(Long perusteId) {
        try {
            return objectMapper.readValue(TestUtil.getTestJulkaisuJsonAsString("material/ylops-peruste.json"), PerusteKaikkiDto.class);
        } catch (Exception e) {
            return null;
        }
    }
}
