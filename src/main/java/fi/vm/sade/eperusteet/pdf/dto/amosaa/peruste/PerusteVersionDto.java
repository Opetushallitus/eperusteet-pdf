package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import lombok.Data;

import java.util.Date;

@Data
public class PerusteVersionDto {
    private Date aikaleima;

    public PerusteVersionDto() {
    }

    public PerusteVersionDto(Date aikaleima) {
        this.aikaleima = aikaleima;
    }
}
