package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.SisaltoTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.CachedPerusteBaseDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KotoKielitaitotasoDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KotoLaajaAlainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KotoOpintoDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KoulutuksenOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.OpintokokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.TekstiKappaleDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.TuvaLaajaAlainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SuorituspolkuDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.TutkinnonosaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SisaltoviiteLaajaDto {
    private Long id;
    private TekstiKappaleDto tekstiKappale;
    private SisaltoTyyppi tyyppi;
    @JsonIgnore
    private OpetussuunnitelmaDto owner;

    private CachedPerusteBaseDto peruste;
    private TutkinnonosaDto tosa;
    private SuorituspolkuDto suorituspolku;
    private OpintokokonaisuusDto opintokokonaisuus;
    private KoulutuksenOsaDto koulutuksenosa;
    private TuvaLaajaAlainenOsaaminenDto tuvaLaajaAlainenOsaaminen;
    private KotoKielitaitotasoDto kotoKielitaitotaso;
    private KotoOpintoDto kotoOpinto;
    private KotoLaajaAlainenOsaaminenDto kotoLaajaAlainenOsaaminen;
    private Long perusteenOsaId;

    public OpetussuunnitelmaDto getOpetussuunnitelma() {
        return owner;
    }
}
