package fi.vm.sade.eperusteet.pdf.domain.validointi;

import fi.vm.sade.eperusteet.pdf.domain.TekstiPalanen;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "validointi_rakenneongelma")
public class RakenneOngelma {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    public String ongelma;

    @Getter
    @Setter
    @OneToOne
    public TekstiPalanen ryhma;
}
