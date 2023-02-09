package fi.vm.sade.eperusteet.pdf.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractRakenneOsaDto {
    private LokalisoituTekstiDto kuvaus;
    private KoodiDto vieras;
    private UUID tunniste;
    private Boolean pakollinen;

    public abstract String validationIdentifier();

    public final void foreach(final Visitor visitor) {
        foreach(visitor, 0);
    }

    protected abstract void foreach(final Visitor visitor,final int currentDepth);

    public interface Visitor {
        void visit(final AbstractRakenneOsaDto dto, final int depth);
    }
}
