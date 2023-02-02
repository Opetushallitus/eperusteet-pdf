package fi.vm.sade.eperusteet.pdf.domain.eperusteet;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.annotation.Identifiable;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.KoulutustyyppiToteutus;

public interface PerusteIdentifiable extends Identifiable {
    KoulutustyyppiToteutus getToteutus();
}
