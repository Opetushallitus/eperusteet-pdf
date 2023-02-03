package fi.vm.sade.eperusteet.pdf.domain.common.enums;

public enum OhjeTyyppi {

    OHJE("ohje"),
    PERUSTETEKSTI("perusteteksti");

    private final String tyyppi;

    private OhjeTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    @Override
    public String toString() {
        return tyyppi;
    }
}
