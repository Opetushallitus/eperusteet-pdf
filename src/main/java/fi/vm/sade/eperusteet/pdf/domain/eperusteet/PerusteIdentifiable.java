package fi.vm.sade.eperusteet.pdf.domain.eperusteet;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.KoulutustyyppiToteutus;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.annotation.Identifiable;

public interface PerusteIdentifiable extends Identifiable {
    KoulutustyyppiToteutus getToteutus();
}
