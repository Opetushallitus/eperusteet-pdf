package fi.vm.sade.eperusteet.pdf.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MuokkausTapahtuma {
    LUONTI("luonti"),
    PAIVITYS("paivitys"),
    PALAUTUS("palautus"),
    JARJESTETTY("jarjestetty"),
    KOPIOINTI("kopiointi"),
    JULKAISU("julkaisu"),
    POISTO("poisto");

    private String tapahtuma;

    @Override
    public String toString() {
        return tapahtuma;
    }
}
