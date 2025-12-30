package fi.vm.sade.eperusteet.pdf.dto.eperusteet.kios;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KaantajaAihealueKategoriaDto {

    private Long id;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto kuvaus;
    private LokalisoituTekstiDto perustaso;
    private LokalisoituTekstiDto keskitaso;
    private LokalisoituTekstiDto ylintaso;
}

