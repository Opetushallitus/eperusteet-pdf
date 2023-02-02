package fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.export;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.ylops.OpetussuunnitelmaExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.Lops2019LaajaAlainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.Lops2019OppiaineJarjestysDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpetussuunnitelmaExportLops2019Dto extends OpetussuunnitelmaExportDto {
    private List<Lops2019LaajaAlainenOsaaminenDto> laajaAlaisetOsaamiset = new ArrayList<>();
    private List<Lops2019OpintojaksoExportDto> opintojaksot = new ArrayList<>();
    private List<Lops2019OppiaineExportDto> valtakunnallisetOppiaineet = new ArrayList<>();
    private List<Lops2019PaikallinenOppiaineExportDto> paikallisetOppiaineet = new ArrayList<>();
    private Set<Lops2019OppiaineJarjestysDto> oppiaineJarjestykset = new HashSet<>();
}
