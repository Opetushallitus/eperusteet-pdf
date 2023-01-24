package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.validointi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RakenneValidointiDto {
    public List<RakenneOngelmaDto> ongelmat = new ArrayList<>();
}
