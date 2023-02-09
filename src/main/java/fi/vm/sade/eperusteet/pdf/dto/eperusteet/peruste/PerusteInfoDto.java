package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.PerusteTila;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteInfoDto {
    private Long id;
    private PerusteVersionDto globalVersion;
    private LokalisoituTekstiDto nimi;
    private String diaarinumero;
    private Date voimassaoloLoppuu;
    private Date voimassaoloAlkaa;
    private PerusteTila tila;
    private Set<SuoritustapaDto> suoritustavat;
}
