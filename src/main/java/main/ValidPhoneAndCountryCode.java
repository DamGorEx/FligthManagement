package main;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = { PhoneAndCountryCodeBothExistValidator.class })
public @interface ValidPhoneAndCountryCode {

    String message() default "both phone number and country code should be filled in";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

