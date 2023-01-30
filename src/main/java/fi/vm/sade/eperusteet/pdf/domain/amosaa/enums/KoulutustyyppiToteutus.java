package fi.vm.sade.eperusteet.pdf.domain.amosaa.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Koulutustyyppi ei enää yksilöi toteutusta ja toteutus voi olla jaettu eri koulutustyyppien välillä.
 */

public enum KoulutustyyppiToteutus {
    YKSINKERTAINEN("yksinkertainen"), // Sisältää ainoastaan tekstikappaleita
    PERUSOPETUS("perusopetus"),
    LOPS("lops"),
    AMMATILLINEN("ammatillinen"),
    TPO("taiteenperusopetus"),
    LOPS2019("lops2019"),
    VAPAASIVISTYSTYO("vapaasivistystyo"),
    TUTKINTOONVALMENTAVA("tutkintoonvalmentava"),
    KOTOUTUMISKOULUTUS("kotoutumiskoulutus");

    private final String tyyppi;

    KoulutustyyppiToteutus(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    @JsonCreator
    public static KoulutustyyppiToteutus of(String tyyppi) {
        for (KoulutustyyppiToteutus s : values()) {
            if (s.tyyppi.equalsIgnoreCase(tyyppi)) {
                return s;
            }
        }
        throw new IllegalArgumentException(tyyppi + " ei ole kelvollinen toteutus");
    }

    @Override
    public String toString() {
        return tyyppi;
    }

}
