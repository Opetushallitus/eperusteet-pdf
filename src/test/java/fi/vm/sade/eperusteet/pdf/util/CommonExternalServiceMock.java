package fi.vm.sade.eperusteet.pdf.util;

import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.exception.ServiceException;
import fi.vm.sade.eperusteet.pdf.service.external.CommonExternalService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
@Profile("test")
public class CommonExternalServiceMock implements CommonExternalService {

    @Override
    public InputStream getLiitetiedosto(Long sisaltoId, UUID fileName, DokumenttiTyyppi tyyppi) {
        return null;
    }

    @Override
    public byte[] getDokumenttiKuva(GeneratorData generatorData, Kuvatyyppi kuvatyyppi) {
        return new byte[0];
    }

    @Override
    public TermiDto getTermi(Long id, String avain, DokumenttiTyyppi dokumenttityyppi) {
        return null;
    }

    @Override
    public void postPdfData(byte[] pdfData, byte[] xmlData, Long dokumenttiId, DokumenttiTyyppi tyyppi) {
    }

    @Override
    public void updateDokumenttiTila(DokumenttiTila tila, Long dokumenttiId, DokumenttiTyyppi tyyppi) {
        if (DokumenttiTila.EPAONNISTUI.equals(tila)) {
            throw new ServiceException("testi epäonnistui");
        }
    }
}
