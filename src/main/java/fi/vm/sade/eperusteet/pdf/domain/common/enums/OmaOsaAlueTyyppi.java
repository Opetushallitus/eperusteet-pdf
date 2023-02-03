package fi.vm.sade.eperusteet.pdf.domain.common.enums;

public enum OmaOsaAlueTyyppi {
    PAKOLLINEN("pakollinen"),
    VALINNAINEN("valinnainen"),
    PAIKALLINEN("paikallinen");

    private final String tyyppi;

    private OmaOsaAlueTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    @Override
    public String toString() {
        return tyyppi;
    }
}
