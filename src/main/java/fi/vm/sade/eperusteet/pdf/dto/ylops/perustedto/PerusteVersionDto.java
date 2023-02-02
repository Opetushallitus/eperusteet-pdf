package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PerusteVersionDto {
    private Date aikaleima;

    public PerusteVersionDto() {
    }

    public PerusteVersionDto(Date aikaleima) {
        this.aikaleima = aikaleima;
    }
}
