package fi.vm.sade.eperusteet.eperusteetpdfservice.utils;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.annotation.Identifiable;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.KoulutustyyppiToteutus;

public interface PerusteIdentifiable extends Identifiable {
    KoulutustyyppiToteutus getToteutus();
}
