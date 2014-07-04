package uk.ac.openmf.validation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author harish
 */
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ForenameValidator.class)
public @interface Forename {
    String message() default "{uk.ac.openmf.messages.forename}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
