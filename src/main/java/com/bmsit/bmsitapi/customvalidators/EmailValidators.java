package com.bmsit.bmsitapi.customvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidators implements ConstraintValidator<EmailConstraint, String> {
    @Override
    public void initialize(EmailConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext context) {
        return emailField != null && emailField.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                && (emailField.length() <= 64);
    }
}