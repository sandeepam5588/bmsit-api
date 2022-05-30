package com.bmsit.bmsitapi.customvalidators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = EmailValidators.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailConstraint {
    String message() default "Invalid Email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
