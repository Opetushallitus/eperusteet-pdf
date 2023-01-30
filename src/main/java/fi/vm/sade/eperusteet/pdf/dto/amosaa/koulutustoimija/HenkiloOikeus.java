package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum HenkiloOikeus {
    LUKU("luku"), // Säästetty ulkopuolisia varten
    MUOKKAUS("muokkaus"),
    LUOMINEN("luominen"),
    POISTO("poisto"),
    HALLINNOINTI("hallinnointi");

    private final String tyyppi;

    private HenkiloOikeus(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    @Override
    public String toString() {
        return tyyppi;
    }

    @JsonCreator
    public static HenkiloOikeus of(String tila) {
        for (HenkiloOikeus s : values()) {
            if (s.tyyppi.equalsIgnoreCase(tila)) {
                return s;
            }
        }
        throw new IllegalArgumentException(tila + " ei ole kelvollinen tila");
    }

    public boolean isOneOf(HenkiloOikeus[] tyypit) {
        for (HenkiloOikeus toinen : tyypit) {
            if (toinen.toString().equals(this.tyyppi)) {
                return true;
            }
        }
        return false;
    }
}
