package fi.vm.sade.eperusteet.pdf.util;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.osaamismerkki.OsaamismerkkiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.service.external.EperusteetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Profile("test")
public class EperusteetServiceMock implements EperusteetService {
    @Override
    public KVLiiteJulkinenDto getKvLiite(Long id) {
        KVLiiteJulkinenDto kv = new KVLiiteJulkinenDto();
        kv.setNimi(LokalisoituTekstiDto.of("KvNimi"));
        kv.setDiaarinumero("111/222/3333");
        kv.setVoimassaoloAlkaa(new Date());
        return kv;
    }

    @Override
    public ArviointiAsteikkoDto getArviointiasteikko(Long id) {
        return null;
    }

    @Override
    public List<OsaamismerkkiDto> getOsaamismerkit(Set<Long> koodit) {
        return null;
    }
}
