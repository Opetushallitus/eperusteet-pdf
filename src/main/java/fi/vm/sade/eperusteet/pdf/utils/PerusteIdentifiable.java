package fi.vm.sade.eperusteet.pdf.utils;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.annotation.Identifiable;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.KoulutustyyppiToteutus;

public interface PerusteIdentifiable extends Identifiable {
    KoulutustyyppiToteutus getToteutus();
}
