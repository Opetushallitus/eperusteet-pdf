package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.AikatauluTapahtuma;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
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
