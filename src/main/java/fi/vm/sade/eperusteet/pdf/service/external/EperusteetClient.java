package fi.vm.sade.eperusteet.pdf.service.external;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@PreAuthorize("permitAll()") // OK, koska mäppääntyy julkisiin rajapintoihin
public interface EperusteetClient {
    String getPerusteData(Long id);
}
