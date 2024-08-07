package fi.vm.sade.eperusteet.pdf.dto.amosaa.osaamismerkki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.liite.LiiteBaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OsaamismerkkiKategoriaLiiteDto extends LiiteBaseDto {
    private String mime;
    private String binarydata;
}
