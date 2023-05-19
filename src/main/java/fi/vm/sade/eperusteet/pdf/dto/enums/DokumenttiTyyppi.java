package fi.vm.sade.eperusteet.pdf.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum DokumenttiTyyppi {
    PERUSTE("peruste"),
    YLOPS("ylops"),
    AMOSAA("amosaa"),
    KVLIITE("kvliite");

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
