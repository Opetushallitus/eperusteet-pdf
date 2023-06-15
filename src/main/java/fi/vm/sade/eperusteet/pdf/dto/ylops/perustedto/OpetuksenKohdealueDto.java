package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fi.vm.sade.eperusteet.pdf.dto.common.ReferenceableDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OpetuksenKohdealueDto implements ReferenceableDto {
    public Long id;
    public PerusteenLokalisoituTekstiDto nimi;
    public PerusteenLokalisoituTekstiDto kuvaus;
}
