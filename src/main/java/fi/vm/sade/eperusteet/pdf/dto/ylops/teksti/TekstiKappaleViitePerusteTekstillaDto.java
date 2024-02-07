package fi.vm.sade.eperusteet.pdf.dto.ylops.teksti;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.enums.Omistussuhde;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TekstiKappaleViitePerusteTekstillaDto {
    private Long id;
    @JsonProperty("_tekstiKappale")
    private Reference tekstiKappaleRef;
    private TekstiKappaleKevytDto tekstiKappale;
    private Omistussuhde omistussuhde;
    private boolean pakollinen;
    private boolean valmis;
    private List<TekstiKappaleViitePerusteTekstillaDto> lapset;
    private boolean liite;
    private Long perusteTekstikappaleId;
    private TekstiKappaleDto perusteenTekstikappale;
}
