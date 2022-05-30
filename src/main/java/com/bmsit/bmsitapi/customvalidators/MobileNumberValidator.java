package com.bmsit.bmsitapi.customvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileNumberValidator implements ConstraintValidator<MobileNumberConstraint, String> {

    @Override
    public void initialize(MobileNumberConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext context) {
        return contactField != null && contactField.matches("[0-9]+")
                && (contactField.length() == 10);
    }
}
