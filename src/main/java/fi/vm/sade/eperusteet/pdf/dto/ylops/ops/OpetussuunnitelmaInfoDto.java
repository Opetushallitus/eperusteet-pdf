package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class OpetussuunnitelmaInfoDto extends OpetussuunnitelmaBaseDto {
    private Date julkaistu;
}
