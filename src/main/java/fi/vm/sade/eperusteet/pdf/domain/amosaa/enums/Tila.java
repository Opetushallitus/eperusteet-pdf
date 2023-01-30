package fi.vm.sade.eperusteet.pdf.domain.amosaa.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.EnumSet;
import java.util.Set;

public enum Tila {
    LUONNOS("luonnos") {
        @Override
        public Set<Tila> mahdollisetSiirtymat(boolean isPohja) {
            return EnumSet.of(VALMIS, POISTETTU);
        }
    },
    VALMIS("valmis") {
        @Override
        public Set<Tila> mahdollisetSiirtymat(boolean isPohja) {
            return isPohja ? EnumSet.of(LUONNOS, POISTETTU) : EnumSet.of(LUONNOS, POISTETTU, JULKAISTU);
        }
    },
    POISTETTU("poistettu") {
        @Override
        public Set<Tila> mahdollisetSiirtymat(boolean isPohja) {
            return EnumSet.of(LUONNOS);
        }
    },
    JULKAISTU("julkaistu") {
        @Override
        public Set<Tila> mahdollisetSiirtymat(boolean isPohja) {
            return EnumSet.of(LUONNOS);
        }
    };

    private final String tila;

    Tila(String tila) {
        this.tila = tila;
    }

    @Override
    public String toString() {
        return tila;
    }

    public Set<Tila> mahdollisetSiirtymat() {
        return mahdollisetSiirtymat(false);
    }

    public Set<Tila> mahdollisetSiirtymat(boolean isPohja) {
        return EnumSet.noneOf(Tila.class);
    }

    @JsonCreator
    public static Tila of(String tila) {
        for (Tila s : values()) {
            if (s.tila.equalsIgnoreCase(tila)) {
                return s;
            }
        }
        throw new IllegalArgumentException(tila + " ei ole kelvollinen Tila");
    }
}
