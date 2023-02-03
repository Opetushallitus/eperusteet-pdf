package fi.vm.sade.eperusteet.pdf.dto.ylops;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Omistussuhde;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiKappaleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TekstiKappaleViiteExportDto {

    private TekstiKappaleViiteExportDto original;

    private Long id;

    private TekstiKappaleDto tekstiKappale;
    private Omistussuhde omistussuhde;
    private boolean pakollinen;
    private boolean valmis;
    private Long perusteTekstikappaleId;
    private boolean naytaPerusteenTeksti = true;
    private boolean naytaPohjanTeksti = false;
    private boolean piilotettu = false;
    private boolean liite = false;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class Puu extends TekstiKappaleViiteExportDto {
        private List<Puu> lapset;
    }
}
