package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.dto.ylops.OpetussuunnitelmaExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.koodisto.OrganisaatioDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiKappaleViiteDto;

import java.util.List;


public interface YlopsService {
    OpetussuunnitelmaExportDto getOpetussuunnitelma(Long opsId);

    OrganisaatioDto getOrganisaatio(String oid);

    byte[] getDokumenttiLiite(Long opsId, Kuvatyyppi tiedostonimi);

    OpetussuunnitelmaExportDto getOpetussuunnitelmaTemp(Long opsId);

    List<TekstiKappaleViiteDto.Matala> getTekstiKappaleViiteOriginals(Long opsId, Long viiteId);

    TekstiKappaleViiteDto getLops2019PerusteTekstikappale(Long opsId, Long tekstikappaleId);
}
