package fi.vm.sade.eperusteet.pdf.dto.common;

public interface Liitteellinen {

    Boolean getLiite();

    default boolean isLiite() {
        return getLiite() != null && getLiite();
    }
}
