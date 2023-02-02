package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import fi.vm.sade.eperusteet.pdf.dto.ylops.IdHolder;
import lombok.Getter;

@Getter
public class LongIdResultDto implements IdHolder {
    private final Long id;

    public LongIdResultDto(Long id) {
        this.id = id;
    }
}
