package fi.vm.sade.eperusteet.eperusteetpdfservice.domain.validointi;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.TekstiPalanen;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidHtmlValidator extends ValidHtmlValidatorBase implements ConstraintValidator<ValidHtml, TekstiPalanen> {

	@Override
	public void initialize(ValidHtml constraintAnnotation) {
		setupValidator(constraintAnnotation);
	}

	@Override
	public boolean isValid(TekstiPalanen value, ConstraintValidatorContext context) {
		return isValid(value);
	}
}
