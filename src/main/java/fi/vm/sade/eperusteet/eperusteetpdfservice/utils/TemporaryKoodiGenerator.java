package fi.vm.sade.eperusteet.eperusteetpdfservice.utils;

import java.util.UUID;

public class TemporaryKoodiGenerator {

    public static String generate(String koodisto) {
        return String.format("temporary_%s_%s", koodisto, UUID.randomUUID().toString());
    }
}
