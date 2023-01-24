package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpasSisaltoKevytDto {
    private Long id;
    private List<OppaanKiinnitettyKoodiDto> oppaanKiinnitetytKoodit;
}
