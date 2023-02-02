package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.AikatauluTapahtuma;
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
public class OpetussuunnitelmanAikatauluDto {
    private Long id;
    private Long opetussuunnitelmaId;
    private LokalisoituTekstiDto tavoite;
    private AikatauluTapahtuma tapahtuma;
    private Date tapahtumapaiva;
}
