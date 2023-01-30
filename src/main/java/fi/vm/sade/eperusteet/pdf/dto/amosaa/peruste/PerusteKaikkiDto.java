package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PerusteKaikkiDto extends PerusteBaseDto {

    Set<SuoritustapaLaajaDto> suoritustavat;
    List<TutkinnonosaKaikkiDto> tutkinnonOsat;
    VapaasivistystyoSisaltoDto vapaasivistystyo;
    TutkintoonvalmentavaSisaltoDto tutkintoonvalmentava;

    @JsonIgnore
    public PerusteenOsaViiteDto.Laaja getSisalto() {
        if (vapaasivistystyo != null) {
            return vapaasivistystyo.getSisalto();
        }

        if (tutkintoonvalmentava != null) {
            return tutkintoonvalmentava.getSisalto();
        }

        return null;
    }
}
