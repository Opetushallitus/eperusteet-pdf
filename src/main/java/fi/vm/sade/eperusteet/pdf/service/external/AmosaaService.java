package fi.vm.sade.eperusteet.pdf.service.external;


import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.ops.TermiDto;

public interface AmosaaService {
    OpetussuunnitelmaDto getOpetussuunnitelma(Long ktId, Long opsId);

    TermiDto getTermi(Long ktId, String avain);
}
