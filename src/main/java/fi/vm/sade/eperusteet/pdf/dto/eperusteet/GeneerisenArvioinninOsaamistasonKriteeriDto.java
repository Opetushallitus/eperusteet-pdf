package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "osaamistaso")
@Builder
public class GeneerisenArvioinninOsaamistasonKriteeriDto {
    private Reference osaamistaso;
    private List<LokalisoituTekstiDto> kriteerit = new ArrayList<>();
}
