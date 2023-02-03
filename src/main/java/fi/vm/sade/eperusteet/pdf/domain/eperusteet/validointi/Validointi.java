package fi.vm.sade.eperusteet.pdf.domain.eperusteet.validointi;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Validointi {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Long id;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<RakenneOngelma> ongelmat = new ArrayList<>();

    public void setOngelmat(List<RakenneOngelma> rakenneOngelmat) {
        this.ongelmat.clear();
        this.ongelmat.addAll(rakenneOngelmat);
    }
}
