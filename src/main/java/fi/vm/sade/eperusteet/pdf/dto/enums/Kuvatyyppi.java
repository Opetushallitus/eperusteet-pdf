package fi.vm.sade.eperusteet.pdf.dto.enums;

public enum Kuvatyyppi {
    kansikuva("kansikuva"),
    ylatunniste("ylatunniste"),
    alatunniste("alatunniste");

    private final String tunniste;

    Kuvatyyppi(String tunniste) {
        this.tunniste = tunniste;
    }

    @Override
    public String toString() {
        return tunniste;
    }
}
