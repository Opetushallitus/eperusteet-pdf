package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.NavigationType;

public interface Navigable {

    @JsonIgnore
    NavigationType getNavigationType();
}
