package fi.vm.sade.eperusteet.pdf.domain.eperusteet;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class Diaarinumero implements Serializable {

    public Diaarinumero() {

    }

    public Diaarinumero(String diaarinumero) {
        this.diaarinumero = diaarinumero;
    }

    @Getter
    private String diaarinumero;

    @Override
    public String toString() {
        return diaarinumero;
    }


}
