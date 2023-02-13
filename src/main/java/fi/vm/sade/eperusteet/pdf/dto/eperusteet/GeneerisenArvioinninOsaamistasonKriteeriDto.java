package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.OsaamistasoDto;
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
    private OsaamistasoDto osaamistaso;
    private List<LokalisoituTekstiDto> kriteerit = new ArrayList<>();
}
