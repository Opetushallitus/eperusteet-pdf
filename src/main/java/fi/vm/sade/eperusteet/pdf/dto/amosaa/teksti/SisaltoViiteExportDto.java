package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.PerusteenOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Käytetään sisällön viemiseen rakenteisena
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SisaltoViiteExportDto extends SisaltoViiteExportBaseDto {
    private boolean naytaPohjanTeksti;
    private boolean naytaPerusteenTeksti;
    private LokalisoituTekstiDto perusteteksti;
    private TekstiKappaleDto pohjanTekstikappale;
    private TutkinnonosaExportDto tosa;
    private SuorituspolkuExportDto suorituspolku;
    private TekstiKappaleJulkinenDto tekstiKappale;
    private List<SisaltoViiteExportDto> lapset;
    private OpintokokonaisuusDto opintokokonaisuus;
    private KoulutuksenOsaDto koulutuksenosa;
    private TuvaLaajaAlainenOsaaminenDto tuvaLaajaAlainenOsaaminen;
    private KotoKielitaitotasoExportDto kotoKielitaitotaso;
    private KotoOpintoExportDto kotoOpinto;
    private KotoLaajaAlainenOsaaminenExportDto kotoLaajaAlainenOsaaminen;
    private Long perusteenOsaId;
    private List<OmaOsaAlueExportDto> osaAlueet = new ArrayList<>();

    public LokalisoituTekstiDto getNimi() {
        if (koulutuksenosa != null) {
            return koulutuksenosa.getNimi();
        }

        if (tuvaLaajaAlainenOsaaminen != null) {
            return tuvaLaajaAlainenOsaaminen.getNimi();
        }

        if (tekstiKappale != null) {
            return tekstiKappale.getNimi();
        }

        return null;
    }

    public void setPerusteSisalto(PerusteenOsaDto perusteenOsaDto) {
        if (kotoKielitaitotaso != null && perusteenOsaDto instanceof fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KotoKielitaitotasoDto) {
            kotoKielitaitotaso.setPerusteenOsa((fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KotoKielitaitotasoDto) perusteenOsaDto);
        }
        if (kotoOpinto != null && perusteenOsaDto instanceof fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KotoOpintoDto) {
            kotoOpinto.setPerusteenOsa((fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KotoOpintoDto) perusteenOsaDto);
        }
        if (kotoLaajaAlainenOsaaminen != null && perusteenOsaDto instanceof fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KotoLaajaAlainenOsaaminenDto) {
            kotoLaajaAlainenOsaaminen.setPerusteenOsa((fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KotoLaajaAlainenOsaaminenDto) perusteenOsaDto);
        }

        if (perusteenOsaDto instanceof fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.TekstiKappaleDto) {
            perusteteksti = ((fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.TekstiKappaleDto) perusteenOsaDto).getTeksti();
        }
    }

    public List<OmaOsaAlueExportDto> getOsaAlueet() {
        if (CollectionUtils.isNotEmpty(this.osaAlueet)) {
            return this.osaAlueet.stream().filter(osaAlue -> !osaAlue.isPiilotettu()).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
