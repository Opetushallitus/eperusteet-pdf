package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lops2019.oppiaineet;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lops2019OppiaineTavoitteetDto {
    private LokalisoituTekstiDto kuvaus;
    private List<Lops2019OppiaineTavoitealueDto> tavoitealueet = new ArrayList<>();
}
