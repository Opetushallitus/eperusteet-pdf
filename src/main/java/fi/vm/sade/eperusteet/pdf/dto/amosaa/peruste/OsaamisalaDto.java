package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OsaamisalaDto {
    private LokalisoituTekstiDto nimi;
    private String osaamisalakoodiArvo;
    private String osaamisalakoodiUri;
}
