package com.ptit.edu.store.validation;

import org.hibernate.mapping.Constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GreaterOrEqualValidate implements ConstraintValidator<GreaterOrEqual, Integer> {
    private int values;

    @Override
    public void initialize(GreaterOrEqual constraintAnnotation) {
        values = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (!value.getClass().equals(Integer.class)) {
            return false;
        }
        if (value > 0) {
            return true;
        }
        return false;
    }
}
