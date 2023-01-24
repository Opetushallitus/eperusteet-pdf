package fi.vm.sade.eperusteet.pdf.utils;

import fi.vm.sade.eperusteet.pdf.domain.annotation.Identifiable;
import fi.vm.sade.eperusteet.pdf.domain.enums.KoulutustyyppiToteutus;

public interface PerusteIdentifiable extends Identifiable {
    KoulutustyyppiToteutus getToteutus();
}
