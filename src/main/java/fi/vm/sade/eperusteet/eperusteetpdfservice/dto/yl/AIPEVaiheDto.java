package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.yl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AIPEVaiheDto extends AIPEVaiheSuppeaDto {
    private Optional<TekstiOsaDto> siirtymaEdellisesta;
    private Optional<TekstiOsaDto> tehtava;
    private Optional<TekstiOsaDto> siirtymaSeuraavaan;
    private Optional<TekstiOsaDto> laajaalainenOsaaminen;
    private Optional<TekstiOsaDto> paikallisestiPaatettavatAsiat;
    private List<OpetuksenKohdealueDto> opetuksenKohdealueet;
    private List<AIPEOppiaineLaajaDto> oppiaineet;
}
