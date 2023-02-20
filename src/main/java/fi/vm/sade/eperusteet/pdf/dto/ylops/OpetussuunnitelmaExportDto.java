package fi.vm.sade.eperusteet.pdf.dto.ylops;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.Lops2019LaajaAlainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.Lops2019OppiaineJarjestysDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.export.Lops2019OpintojaksoExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.export.Lops2019OppiaineExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.export.Lops2019PaikallinenOppiaineExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaBaseDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaNimiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpsOppiaineExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpsVuosiluokkakokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.PerusteInfoDto;
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
public class OpetussuunnitelmaExportDto extends OpetussuunnitelmaBaseDto {
    private PerusteInfoDto peruste;
    private OpetussuunnitelmaNimiDto pohja;
    private TekstiKappaleViiteExportDto.Puu tekstit;

    private Set<OpsVuosiluokkakokonaisuusDto> vuosiluokkakokonaisuudet;
    private Set<OpsOppiaineExportDto> oppiaineet;

    private List<Lops2019LaajaAlainenOsaaminenDto> laajaAlaisetOsaamiset = new ArrayList<>();
    private List<Lops2019OpintojaksoExportDto> opintojaksot = new ArrayList<>();
    private List<Lops2019OppiaineExportDto> valtakunnallisetOppiaineet = new ArrayList<>();
    private List<Lops2019PaikallinenOppiaineExportDto> paikallisetOppiaineet = new ArrayList<>();
    private Set<Lops2019OppiaineJarjestysDto> oppiaineJarjestykset = new HashSet<>();
}
