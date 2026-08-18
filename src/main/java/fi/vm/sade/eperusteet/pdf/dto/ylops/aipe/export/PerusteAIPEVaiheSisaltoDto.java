package fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.KevytTekstiKappaleDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.PerusteOpetuksenkohdealueDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.PerusteTekstiOsaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PerusteAIPEVaiheSisaltoDto {
    private Long id;
    private UUID tunniste;
    private LokalisoituTekstiDto nimi;
    private PerusteTekstiOsaDto siirtymaEdellisesta;
    private PerusteTekstiOsaDto tehtava;
    private PerusteTekstiOsaDto siirtymaSeuraavaan;
    private PerusteTekstiOsaDto paikallisestiPaatettavatAsiat;
    private List<PerusteOpetuksenkohdealueDto> opetuksenKohdealueet = new ArrayList<>();
    private List<KevytTekstiKappaleDto> vapaatTekstit = new ArrayList<>();
}
