package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OpetussuunnitelmaLuontiDto extends OpetussuunnitelmaDto {
    private Long opsId;
    private Set<String> tutkinnonOsaKoodiIncludes;
}
