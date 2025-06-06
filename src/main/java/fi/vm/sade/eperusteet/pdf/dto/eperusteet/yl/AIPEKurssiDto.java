package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AIPEKurssiDto extends AIPEKurssiSuppeaDto {
    private Optional<LokalisoituTekstiDto> kuvaus;
    private Set<Reference> tavoitteet;
}
