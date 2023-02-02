package fi.vm.sade.eperusteet.pdf.domain.ylops;

import fi.vm.sade.eperusteet.pdf.domain.common.ValidHtml;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.LokalisoituTeksti;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

// Tämä on raadeltu luokka pdf serviceen ja ei vastaa enää ylopsin vastaavaa.
public class Tekstiosa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    private Long id;

    @Getter
    @Setter
    @ValidHtml(whitelist = ValidHtml.WhitelistType.MINIMAL)
    private LokalisoituTeksti otsikko;

    @Getter
    @Setter
    @ValidHtml
    private LokalisoituTeksti teksti;

    public Tekstiosa() {
    }

    public Tekstiosa(LokalisoituTeksti otsikko, LokalisoituTeksti teksti) {
        this.otsikko = otsikko;
        this.teksti = teksti;
    }

    public Tekstiosa(Tekstiosa other) {
        this.otsikko = other.getOtsikko();
        this.teksti = other.getTeksti();
    }

    public static Tekstiosa copyOf(Tekstiosa other) {
        if (other == null) return null;
        return new Tekstiosa(other);
    }
}
