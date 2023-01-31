package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("koto_kielitaitotaso")
public class KotoKielitaitotasoDto extends PerusteenOsaDto.Laaja {

    private KoodiDto nimiKoodi;
    private LokalisoituTekstiDto kuvaus;
    private List<KotoTaitotasoDto> taitotasot = new ArrayList<>();

    @Override
    public String getOsanTyyppi() {
        return "koto_kielitaitotaso";
    }
}
