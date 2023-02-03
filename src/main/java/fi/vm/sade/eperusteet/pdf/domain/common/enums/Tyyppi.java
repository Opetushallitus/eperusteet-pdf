package fi.vm.sade.eperusteet.pdf.domain.common.enums;

public enum Tyyppi {
    OPS("ops"),
    POHJA("pohja");

    private final String tyyppi;

    private Tyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    @Override
    public String toString() {
        return tyyppi;
    }
}
