package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.LukiokurssiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lukio.LukioPerusteOppiaineDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lukio.PerusteenOsa;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiosaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class LukioOppiaineTiedotDto extends LukioOppiaineRakenneDto<LukioOppimaaraPerusTiedotDto, LukiokurssiOpsDto>
        implements PerusteeseenViittaava<LukioPerusteOppiaineDto> {
    private LukioPerusteOppiaineDto perusteen;
    @Setter
    private TekstiosaDto tehtava;
    @Setter
    private TekstiosaDto tavoitteet;
    @Setter
    private TekstiosaDto arviointi;
    @Setter
    private List<LukioOppimaaraPerusTiedotDto> pohjanTarjonta = new ArrayList<>();
    @Setter
    private Map<LukiokurssiTyyppi, Optional<LokalisoituTekstiDto>> kurssiTyyppiKuvaukset = new HashMap<>();

    public void setPerusteen(LukioPerusteOppiaineDto perusteen) {
        this.perusteen = perusteen;
        PerusteenOsa.map(this.perusteen, this);
    }

    @Override
    public Stream<? extends PerusteeseenViittaava<?>> viittaukset() {
        return kurssit.stream();
    }
}
