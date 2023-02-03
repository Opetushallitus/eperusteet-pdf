package fi.vm.sade.eperusteet.pdf.dto;

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
    private LokalisoituTekstiDto termi;
    private LokalisoituTekstiDto selitys;
    private Boolean alaviite;
}
