package fi.vm.sade.eperusteet.pdf.domain.ylops.validointi;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.LokalisoituTeksti;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.ReferenceableEntity;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.Validointi.ValidointiContext;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.Validointi.ValidointiDto;

public interface Validable extends ReferenceableEntity {
    void validate(ValidointiDto validointi, ValidointiContext ctx);
    ValidationCategory category();
    LokalisoituTeksti getNimi();
}
