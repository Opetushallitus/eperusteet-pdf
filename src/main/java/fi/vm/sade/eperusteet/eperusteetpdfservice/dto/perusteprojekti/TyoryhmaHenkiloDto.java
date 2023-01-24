package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.perusteprojekti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TyoryhmaHenkiloDto implements Serializable {
    private Long id;
    private String kayttajaOid;
    private String nimi;

    public TyoryhmaHenkiloDto(String nimi, String kayttajaOid) {
        this.kayttajaOid = kayttajaOid;
        this.nimi = nimi;
    }

}
