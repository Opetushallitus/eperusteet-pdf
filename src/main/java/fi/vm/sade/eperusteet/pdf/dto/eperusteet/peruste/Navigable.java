package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Navigable {

    @JsonIgnore
    NavigationType getNavigationType();
}
