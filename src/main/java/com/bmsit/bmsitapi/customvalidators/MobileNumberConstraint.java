package com.bmsit.bmsitapi.customvalidators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MobileNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MobileNumberConstraint {
    String message() default "Invalid phone number: enter 10 digit contact number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
