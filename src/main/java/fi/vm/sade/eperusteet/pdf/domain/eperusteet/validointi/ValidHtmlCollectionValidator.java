package fi.vm.sade.eperusteet.pdf.domain.eperusteet.validointi;


import fi.vm.sade.eperusteet.pdf.domain.common.ValidHtml;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.TekstiPalanen;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class ValidHtmlCollectionValidator extends ValidHtmlValidatorBase implements
    ConstraintValidator<ValidHtml, Collection<TekstiPalanen>> {

    @Override
    public void initialize(ValidHtml constraintAnnotation) {
        setupValidator(constraintAnnotation);
    }

    @Override
    public boolean isValid(Collection<TekstiPalanen> value, ConstraintValidatorContext context) {
        if (value != null) {
            for (TekstiPalanen palanen : value) {
                if (!isValid(palanen)) {
                    return false;
                }
            }
        }
        return true;
    }
}
