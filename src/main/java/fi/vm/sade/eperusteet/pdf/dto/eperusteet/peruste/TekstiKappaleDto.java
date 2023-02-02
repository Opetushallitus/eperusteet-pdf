package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.NavigationType;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.PerusteTila;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.PerusteenOsaTunniste;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("tekstikappale")
public class TekstiKappaleDto extends PerusteenOsaDto.Laaja {
    private LokalisoituTekstiDto teksti;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private KoodiDto osaamisala;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private KoodiDto tutkintonimike;
    private List<KoodiDto> koodit;
    private Boolean liite;

    public TekstiKappaleDto(LokalisoituTekstiDto nimi, PerusteTila tila, PerusteenOsaTunniste tunniste) {
        super(nimi, tila, tunniste);
    }

    @Override
    public String getOsanTyyppi() {
        return "tekstikappale";
    }

    @Override
    public NavigationType getNavigationType() {
        return NavigationType.viite;
    }
}
