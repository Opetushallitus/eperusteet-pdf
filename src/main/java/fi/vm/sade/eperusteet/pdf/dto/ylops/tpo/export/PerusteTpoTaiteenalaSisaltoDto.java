package fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.export;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.KevytTekstiKappaleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PerusteTpoTaiteenalaSisaltoDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto teksti;
    private KevytTekstiKappaleDto kasvatus;
    private KevytTekstiKappaleDto yhteisetOpinnot;
    private KevytTekstiKappaleDto teemaopinnot;
    private KevytTekstiKappaleDto aikuistenOpetus;
    private KevytTekstiKappaleDto tyotavatOpetuksessa;
    private KevytTekstiKappaleDto oppimisenArviointiOpetuksessa;
    private List<KevytTekstiKappaleDto> vapaatTekstit = new ArrayList<>();
}
