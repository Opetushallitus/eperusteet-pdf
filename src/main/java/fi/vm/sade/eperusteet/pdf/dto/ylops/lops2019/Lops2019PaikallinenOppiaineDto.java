package fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lops2019PaikallinenOppiaineDto implements Lops2019SortablePaikallinenOppiaineDto {
    private Long id;
    private String koodi;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto kuvaus;
    private List<Lops2019PaikallinenLaajaAlainenDto> laajaAlainenOsaaminen = new ArrayList<>();
    private Lops2019TehtavaDto tehtava;
    private Lops2019OpiskeluymparistoTyotavatDto opiskeluymparistoTyotavat;
    private Lops2019ArviointiDto arviointi;
    private Lops2019OppiaineenTavoitteetDto tavoitteet;
    private String perusteenOppiaineUri;
}
