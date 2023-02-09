package fi.vm.sade.eperusteet.pdf.dto.amosaa;

import fi.vm.sade.eperusteet.pdf.dto.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.OpsTyyppi;
import fi.vm.sade.eperusteet.utils.domain.utils.Tila;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OpsHakuDto extends QueryDto {
    private Long koulutustoimija;
    private Long peruste;
    private List<KoulutusTyyppi> koulutustyyppi;
    private Set<Tila> tila;
    private Set<OpsTyyppi> tyyppi;
    private boolean organisaatioRyhma;

    public void setTila(Tila tila) {
        if (this.tila == null) {
            this.tila = new HashSet<>();
        }
        this.tila.add(tila);
    }

    public void setTila(Set<Tila> tila) {
        this.tila = tila;
    }

    public void setTyyppi(OpsTyyppi tyyppi) {
        if (this.tyyppi == null) {
            this.tyyppi = new HashSet<>();
        }
        this.tyyppi.add(tyyppi);
    }

    public void setTyyppi(Set<OpsTyyppi> tyyppi) {
        this.tyyppi = tyyppi;
    }
}
