package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JulkaisuDataDto {
    private Long id;
    private int hash;
    private ObjectNode data;
}
