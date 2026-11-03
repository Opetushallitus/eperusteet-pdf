package fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.export;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TpoSisaltoExportDto {
    private Long id;
    private List<TaiteenalaExportDto> taiteenalat = new ArrayList<>();
}
