package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.AikatauluTapahtuma;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerusteAikatauluDto {
    private Long id;
    private PerusteKevytDto peruste;
    private LokalisoituTekstiDto tavoite;
    private AikatauluTapahtuma tapahtuma;
    private Date tapahtumapaiva;
    private boolean julkinen;
}
