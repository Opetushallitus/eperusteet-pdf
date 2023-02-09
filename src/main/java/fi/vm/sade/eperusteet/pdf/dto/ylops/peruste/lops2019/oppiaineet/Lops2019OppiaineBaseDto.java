package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lops2019.oppiaineet;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.KoodiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.Reference;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ReferenceableDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.Lops2019SortableOppiaineDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lops2019OppiaineBaseDto implements ReferenceableDto, Lops2019SortableOppiaineDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private KoodiDto koodi;
    private Reference oppiaine;
    private Lops2019ArviointiDto arviointi;
    private Lops2019TehtavaDto tehtava;
    private Lops2019OpiskeluymparistoTyotavatDto opiskeluymparistoTyotavat;
    private Lops2019OppiaineLaajaAlainenOsaaminenDto laajaAlaisetOsaamiset;
    private Lops2019OppiaineTavoitteetDto tavoitteet;
}
