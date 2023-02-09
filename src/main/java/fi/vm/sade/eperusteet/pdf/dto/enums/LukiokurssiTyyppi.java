package fi.vm.sade.eperusteet.pdf.dto.enums;

public enum LukiokurssiTyyppi {
    VALTAKUNNALLINEN_PAKOLLINEN,
    VALTAKUNNALLINEN_SYVENTAVA,
    VALTAKUNNALLINEN_SOVELTAVA,
    PAIKALLINEN_SYVENTAVA,
    PAIKALLINEN_SOVELTAVA,
    PAKOLLINEN;

    LukiokurssiTyyppi() {

    }

    public enum Paikallinen {
        PAIKALLINEN_SYVENTAVA(LukiokurssiTyyppi.PAIKALLINEN_SYVENTAVA, "lukionkurssit_psy"),
        PAIKALLINEN_SOVELTAVA(LukiokurssiTyyppi.PAIKALLINEN_SOVELTAVA, "lukionkurssit_pso");

        private final LukiokurssiTyyppi tyyppi;
        private final String koodi;

        Paikallinen(LukiokurssiTyyppi tyyppi, String koodi) {
            this.tyyppi = tyyppi;
            this.koodi = koodi;
        }

        public LukiokurssiTyyppi toKurssiiTyyppi() {
            return tyyppi;
        }

        public String getKurssiKoodi() {
            return koodi;
        }
    }


    public static LukiokurssiTyyppi ofPerusteTyyppi(PerusteenLukiokurssiTyyppi tyyppi) {
        switch (tyyppi) {
            case PAKOLLINEN:
                return VALTAKUNNALLINEN_PAKOLLINEN;
            case VALTAKUNNALLINEN_SOVELTAVA:
                return VALTAKUNNALLINEN_SOVELTAVA;
            case VALTAKUNNALLINEN_SYVENTAVA:
                return VALTAKUNNALLINEN_SYVENTAVA;
            default:
                throw new IllegalStateException("Unimplemented peruste lukiokurssityyppi: " + tyyppi);
        }
    }
}
