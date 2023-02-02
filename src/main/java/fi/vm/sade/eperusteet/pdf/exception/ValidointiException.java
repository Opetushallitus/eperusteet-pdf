package fi.vm.sade.eperusteet.pdf.exception;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.validointi.Validointi;
import lombok.Getter;
import org.springframework.core.NestedRuntimeException;

public class ValidointiException extends NestedRuntimeException {
    @Getter
    Validointi validointi;

    public ValidointiException(Validointi validointi) {
        super("ops-validointivirheita");
        this.validointi = validointi;
    }

}
