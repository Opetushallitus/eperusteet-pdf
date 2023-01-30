package fi.vm.sade.eperusteet.pdf.domain.amosaa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MuokkausTapahtuma {
    LUONTI("luonti"),
    PAIVITYS("paivitys"),
    PALAUTUS("palautus"),
    POISTO("poisto"),
    JULKAISU("julkaisu");

    private String tapahtuma;

    @Override
    public String toString() {
        return tapahtuma;
    }
}
