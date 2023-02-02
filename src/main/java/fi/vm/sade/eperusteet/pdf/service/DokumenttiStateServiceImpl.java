package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.domain.common.Dokumentti;
import fi.vm.sade.eperusteet.pdf.repository.DokumenttiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DokumenttiStateServiceImpl implements DokumenttiStateService {

    @Autowired
    private DokumenttiRepository dokumenttiRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Dokumentti save(Dokumentti dokumentti) {
        return dokumenttiRepository.save(dokumentti);
    }
}
