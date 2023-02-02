package fi.vm.sade.eperusteet.pdf.domain.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TutkinnonOsaTyyppi {
    NORMAALI("normaali"),
    REFORMI_TUTKE2("reformi_tutke2"),
    TUTKE2("tutke2"),

    PERUSTEESTA("perusteesta"), // Perusteen kautta käytettävä tutkinnon osa
    OMA("oma"), // Itse toteutettu tutkinnon osa
    VIERAS("vieras"), // Jokin muualta tuotu tutkinnon osa
    YHTEINEN("yhteinen"); // Viite jonkin muun opetussuunnitelman tutkinnon osaan;

    private final String tyyppi;

    private TutkinnonOsaTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    @Override
    public String toString() {
        return tyyppi;
    }

    public static boolean isTutke(TutkinnonOsaTyyppi tyyppi) {
        return tyyppi.equals(TutkinnonOsaTyyppi.TUTKE2)
                || tyyppi.equals(TutkinnonOsaTyyppi.REFORMI_TUTKE2);
    }

    @JsonCreator
    public static TutkinnonOsaTyyppi of(String tyyppi) {
        for (TutkinnonOsaTyyppi s : values()) {
            if (s.tyyppi.equalsIgnoreCase(tyyppi)) {
                return s;
            }
        }
        throw new IllegalArgumentException(tyyppi + " ei ole kelvollinen tyyppi");
    }
}
