package fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.export;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.KevytTekstiKappaleDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.TavoiteAlueDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PerusteTpoVaiheSisaltoDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private Integer laajuus;
    private LokalisoituTekstiDto kuvaus;
    private List<TavoiteAlueDto> tavoitealueet = new ArrayList<>();
    private List<KevytTekstiKappaleDto> vapaatTekstit = new ArrayList<>();
}
