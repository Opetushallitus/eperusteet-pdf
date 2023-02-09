package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import fi.vm.sade.eperusteet.pdf.dto.enums.PerusteTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.ProjektiTila;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteprojektiQueryDto {
    private int sivu = 0;
    private int sivukoko = 25;
    private String nimi;
    private List<PerusteTyyppi> tyyppi = new ArrayList<>();
    private Set<ProjektiTila> tila;
    private Set<String> koulutustyyppi;
    private String jarjestysTapa;
    private Boolean jarjestysOrder;
    private Set<Long> perusteet;
    private boolean tuleva;
    private boolean voimassaolo;
    private boolean siirtyma;
    private boolean poistunut;

    public void setTila(ProjektiTila tila) {
        this.tila = new HashSet<>();
        this.tila.add(tila);
    }

    public void setTila(Set<ProjektiTila> tila) {
        this.tila = tila;
    }
}
