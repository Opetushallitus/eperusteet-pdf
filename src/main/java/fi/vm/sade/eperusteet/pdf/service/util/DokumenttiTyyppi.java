package fi.vm.sade.eperusteet.pdf.service.util;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum DokumenttiTyyppi {
    PERUSTE("peruste"),
    OPS("ops"),
    TOTEUTUSSUUNNITELMA("toteutussuunnitelma");

    private final String tyyppi;

    DokumenttiTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    @Override
    public String toString() {
        return tyyppi;
    }

    @JsonCreator
    public static DokumenttiTyyppi of(String tyyppi) {
        for (DokumenttiTyyppi s : values()) {
            if (s.tyyppi.equalsIgnoreCase(tyyppi)) {
                return s;
            }
        }
        throw new IllegalArgumentException(tyyppi + " ei ole kelvollinen dokumenttityyppi");
    }
}
