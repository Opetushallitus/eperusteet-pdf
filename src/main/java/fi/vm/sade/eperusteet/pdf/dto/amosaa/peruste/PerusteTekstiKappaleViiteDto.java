package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteTekstiKappaleViiteDto {
    private Long id;
    private PerusteTekstiKappaleDto tesktiKappale;
    private List<PerusteTekstiKappaleViiteDto> lapset;
}
