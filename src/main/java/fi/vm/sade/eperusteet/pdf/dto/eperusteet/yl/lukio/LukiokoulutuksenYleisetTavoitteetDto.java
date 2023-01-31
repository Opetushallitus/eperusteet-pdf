package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.UpdateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class LukiokoulutuksenYleisetTavoitteetDto extends UpdateDto<LukiokoulutuksenYleisetTavoitteetDto> implements Serializable {
    private Long id;
    private LokalisoituTekstiDto otsikko;
    private LokalisoituTekstiDto kuvaus;
    private Date muokattu;
}
