package fi.vm.sade.eperusteet.pdf.dto.eperusteet.opas.lops2019.oppiaineet;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.Reference;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.ReferenceableDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
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
    private Reference oppiaine;

    private Lops2019ArviointiDto arviointi;
    private Lops2019TehtavaDto tehtava;
    private Lops2019OpiskeluymparistoTyotavatDto opiskeluymparistoTyotavat;
    private Lops2019OppiaineLaajaAlainenOsaaminenDto laajaAlaisetOsaamiset;
    private Lops2019OppiaineTavoitteetDto tavoitteet;
}
