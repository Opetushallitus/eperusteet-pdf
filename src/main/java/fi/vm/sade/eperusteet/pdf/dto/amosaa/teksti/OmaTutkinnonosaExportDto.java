package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.GeneerinenArviointiasteikkoKaikkiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OmaTutkinnonosaExportDto extends OmaTutkinnonosaBaseDto {
    private ArviointiExportDto arviointi;
    private GeneerinenArviointiasteikkoKaikkiDto geneerinenArviointiasteikko;
}
