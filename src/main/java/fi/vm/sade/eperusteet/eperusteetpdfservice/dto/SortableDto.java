package fi.vm.sade.eperusteet.eperusteetpdfservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SortableDto implements Sortable {
    private Long id;
    private Integer jarjestys;
}
