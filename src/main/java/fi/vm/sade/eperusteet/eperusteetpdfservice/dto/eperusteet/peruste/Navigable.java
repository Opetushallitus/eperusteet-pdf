package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Navigable {

    @JsonIgnore
    NavigationType getNavigationType();
}
