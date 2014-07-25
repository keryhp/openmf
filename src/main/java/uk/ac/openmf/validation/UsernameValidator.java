package uk.ac.openmf.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author harish
 */
public class UsernameValidator implements ConstraintValidator<Username, String> {

	private static final Pattern VALID = Pattern.compile("\\s") ;


	public void initialize(Username constraintAnnotation) {}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if("test".equalsIgnoreCase(value))
			return true;
		else
			return !VALID.matcher(value).matches();
	}
}
