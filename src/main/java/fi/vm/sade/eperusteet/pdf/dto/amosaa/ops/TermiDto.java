package fi.vm.sade.eperusteet.pdf.dto.amosaa.ops;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TermiDto {
    private Long id;
    private String avain;
    private Boolean alaviite;
    private LokalisoituTekstiDto termi;
    private LokalisoituTekstiDto selitys;
}
