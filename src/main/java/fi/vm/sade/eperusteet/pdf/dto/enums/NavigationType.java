package fi.vm.sade.eperusteet.pdf.dto.enums;

public enum NavigationType {
    root,
    tiedot,
    viite,
    liitteet,
    liite,
    kvliite,
    laajaalaiset,
    laajaalainen,
    oppiaineet,
    oppimaarat,
    oppiaine,
    lukiooppiaineet_2015,
    lukiooppimaarat_2015,
    lukiooppiaine_2015,
    lukiokurssit,
    lukiokurssi,
    moduulit,
    moduuli,
    muodostuminen,
    tutkinnonosat,
    tutkinnonosa,
    tutkinnonosaviite,
    osaalue,
    osaalueet,
    tutkinnon_muodostuminen,
    peruste,
    lukiorakenne,
    aihekokonaisuudet,
    opetuksenyleisettavoitteet,
    tekstikappale,
    taiteenala,
    perusopetusoppiaineet,
    perusopetusoppiaine,
    vuosiluokkakokonaisuus,
    aipevaihe,
    aipekurssi,
    aipeoppiaine,
    kurssit,
    taiteenosa,
    termi,
    opintokokonaisuus,
    tavoitesisaltoalue,
    koto_kielitaitotaso,
    koto_opinto,
    koto_laajaalainenosaaminen,
    linkkisivu,
    koulutuksenosat,
    koulutuksenosa,
    laajaalainenosaaminen,
    osaamiskokonaisuus,
    osaamiskokonaisuus_paa_alue;

    public static NavigationType of(String type) {
        for (NavigationType t : values()) {
            if (t.name().equalsIgnoreCase(type)) {
                return t;
            }
        }
        throw new IllegalArgumentException(type + " ei ole kelvollinen tila");
    }
}
