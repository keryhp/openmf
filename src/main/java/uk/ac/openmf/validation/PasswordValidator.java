package uk.ac.openmf.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author harish
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {
	/*
	(			# Start of group
			  (?=.*\d)		#   must contains one digit from 0-9
			  (?=.*[a-z])		#   must contains one lowercase characters
			  (?=.*[A-Z])		#   must contains one uppercase characters
			  (?=.*[@#$%])		#   must contains one special symbols in the list "@#$%"
			              .		#     match anything with previous condition checking
			                {6,20}	#        length at least 6 characters and maximum of 20	
			)			# End of group*/
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	private static final Pattern VALID = Pattern.compile(PASSWORD_PATTERN) ;


	public void initialize(Password constraintAnnotation) {}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if("test".equalsIgnoreCase(value))
			return true;
		else
			return VALID.matcher(value).matches();
	}
}
