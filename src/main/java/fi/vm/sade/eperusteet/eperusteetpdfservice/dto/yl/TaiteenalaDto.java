package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.yl;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.PerusteTila;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.PerusteenOsaTunniste;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.KevytTekstiKappaleDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.NavigationType;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteenOsaDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("taiteenala")
public class TaiteenalaDto extends PerusteenOsaDto.Laaja {

    public TaiteenalaDto(LokalisoituTekstiDto nimi, PerusteTila tila, PerusteenOsaTunniste tunniste) {
        super(nimi, tila, tunniste);
    }

    private LokalisoituTekstiDto teksti;
    private KoodiDto koodi;
    private KevytTekstiKappaleDto kasvatus;
    private KevytTekstiKappaleDto yhteisetOpinnot;
    private KevytTekstiKappaleDto teemaopinnot;
    private KevytTekstiKappaleDto aikuistenOpetus;
    private KevytTekstiKappaleDto tyotavatOpetuksessa;
    private KevytTekstiKappaleDto oppimisenArviointiOpetuksessa;

    @Override
    public String getOsanTyyppi() {
        return "taiteenala";
    }

    public Map<String, KevytTekstiKappaleDto> getOsaavainMap() {
        return new LinkedHashMap<String, KevytTekstiKappaleDto>() {{
            put("aikuistenOpetus", getAikuistenOpetus());
            put("kasvatus", getKasvatus());
            put("oppimisenArviointiOpetuksessa", getOppimisenArviointiOpetuksessa());
            put("teemaopinnot", getTeemaopinnot());
            put("tyotavatOpetuksessa", getTyotavatOpetuksessa());
            put("yhteisetOpinnot", getYhteisetOpinnot());
        }};
    }

    @Override
    public NavigationType getNavigationType() {
        return NavigationType.taiteenala;
    }
}
