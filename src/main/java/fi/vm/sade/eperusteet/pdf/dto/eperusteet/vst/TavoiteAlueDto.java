package fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst;

import fi.vm.sade.eperusteet.pdf.domain.enums.TavoiteAlueTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TavoiteAlueDto {
    private Long id;
    private TavoiteAlueTyyppi tavoiteAlueTyyppi;
    private KoodiDto otsikko;
    private List<KoodiDto> tavoitteet = new ArrayList<>();
    private List<LokalisoituTekstiDto> keskeisetSisaltoalueet = new ArrayList<>();
}