package fi.vm.sade.eperusteet.pdf.dto.amosaa.tilastot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToimijaTilastotDto {
    List<ToimijanTilastoDto> toimijat = new ArrayList<>();
}
