package fi.vm.sade.eperusteet.pdf.dto.amosaa.organisaatio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganisaatioHistoriaLiitosDto {

    private OrganisaatioHierarkiaDto organisaatio;
    private OrganisaatioHierarkiaDto kohde;
    private Date alkuPvm;
}
