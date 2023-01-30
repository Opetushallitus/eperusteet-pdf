package fi.vm.sade.eperusteet.pdf.domain.amosaa.validation;

import fi.vm.sade.eperusteet.pdf.domain.amosaa.LokalisoituTeksti;
import fi.vm.sade.eperusteet.pdf.domain.common.ValidHtml;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidHtmlValidator extends ValidHtmlValidatorBase implements ConstraintValidator<ValidHtml, LokalisoituTeksti> {

    @Override
    public void initialize(ValidHtml constraintAnnotation) {
        setupValidator(constraintAnnotation);
    }

    @Override
    public boolean isValid(LokalisoituTeksti value, ConstraintValidatorContext context) {
        return isValid(value);
    }
}
