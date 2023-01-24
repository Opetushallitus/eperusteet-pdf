package fi.vm.sade.eperusteet.pdf.domain.enums;

public enum Vuosiluokka {
    VUOSILUOKKA_1,
    VUOSILUOKKA_2,
    VUOSILUOKKA_3,
    VUOSILUOKKA_4,
    VUOSILUOKKA_5,
    VUOSILUOKKA_6,
    VUOSILUOKKA_7,
    VUOSILUOKKA_8,
    VUOSILUOKKA_9;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
