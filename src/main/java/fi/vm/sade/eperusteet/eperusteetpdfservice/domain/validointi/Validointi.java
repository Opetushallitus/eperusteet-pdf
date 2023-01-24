package fi.vm.sade.eperusteet.eperusteetpdfservice.domain.validointi;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "validointi_status_info_validointi")
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
