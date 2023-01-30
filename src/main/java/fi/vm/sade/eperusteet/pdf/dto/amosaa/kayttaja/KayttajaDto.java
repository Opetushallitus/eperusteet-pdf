package fi.vm.sade.eperusteet.pdf.dto.amosaa.kayttaja;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.Reference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

/**
 * @author nkala
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class KayttajaDto extends KayttajaBaseDto {
    private String etunimet;
    private String kutsumanimi;
    private String sukunimi;
    private Date tiedotekuittaus;
    private Set<Reference> suosikit;

}
