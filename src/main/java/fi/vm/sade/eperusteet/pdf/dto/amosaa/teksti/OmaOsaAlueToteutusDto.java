package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OmaOsaAlueToteutusDto {
    private Long id;
    private LokalisoituTekstiDto otsikko;
    private TekstiosaDto tavatjaymparisto;
    private TekstiosaDto arvioinnista;
    private List<VapaaTekstiDto> vapaat = new ArrayList<>();
    private boolean oletustoteutus;
}
