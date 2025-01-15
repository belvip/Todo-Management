package com.belvi.management_todo.servie.impl;

import com.belvi.management_todo.model.ValidCompleted;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CompletedTaskValidator implements ConstraintValidator<ValidCompleted, Boolean> {

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        // Validate: Completed must be true
        return value != null && value;
    }
}