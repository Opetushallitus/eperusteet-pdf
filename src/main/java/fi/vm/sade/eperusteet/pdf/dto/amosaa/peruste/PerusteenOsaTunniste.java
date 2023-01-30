package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PerusteenOsaTunniste {
    NORMAALI("normaali"),
    LAAJAALAINENOSAAMINEN("laajaalainenosaaminen"),
    RAKENNE("rakenne");

    private final String tunniste;

    private PerusteenOsaTunniste(String tunniste) {
        this.tunniste = tunniste;
    }

    @Override
    public String toString() {
        return tunniste;
    }

    @JsonCreator
    public static PerusteenOsaTunniste of(String tila) {
        for (PerusteenOsaTunniste s : values()) {
            if (s.tunniste.equalsIgnoreCase(tila)) {
                return s;
            }
        }
        throw new IllegalArgumentException(tila + " ei ole kelvollinen tunniste");
    }
}
