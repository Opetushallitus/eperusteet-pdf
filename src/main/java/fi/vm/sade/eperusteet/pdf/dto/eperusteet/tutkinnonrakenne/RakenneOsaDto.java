package fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne;

import fi.vm.sade.eperusteet.pdf.dto.common.AbstractRakenneOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.common.ReferenceableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RakenneOsaDto extends AbstractRakenneOsaDto {
    private String erikoisuus;
    private Reference tutkinnonOsaViite;

    @Override
    public String validationIdentifier() {
        return tutkinnonOsaViite != null
                ? tutkinnonOsaViite.getId()
                : "";
    }

    public static RakenneOsaDto of(Reference tov) {
        RakenneOsaDto result = new RakenneOsaDto();
        result.setTutkinnonOsaViite(tov);
        return result;
    }

    @Override
    protected void foreach(final Visitor visitor, final int depth) {
        visitor.visit(this, depth);
    }
}
