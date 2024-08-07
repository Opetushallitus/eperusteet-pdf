package fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.common.ReferenceableDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.KoodiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lops2019OppiaineBaseDto implements ReferenceableDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private KoodiDto koodi;
    @JsonProperty("_oppiaine")
    private Reference oppiaine;

    private Lops2019ArviointiDto arviointi;
    private Lops2019TehtavaDto tehtava;
    private Lops2019OpiskeluymparistoTyotavatDto opiskeluymparistoTyotavat;
    private Lops2019OppiaineLaajaAlainenOsaaminenDto laajaAlaisetOsaamiset;
    private Lops2019OppiaineTavoitteetDto tavoitteet;
}
