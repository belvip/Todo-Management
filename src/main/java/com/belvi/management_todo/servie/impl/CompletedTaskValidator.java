package com.belvi.management_todo.servie.impl;

import com.belvi.management_todo.model.ValidCompleted;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CompletedTaskValidator implements ConstraintValidator<ValidCompleted, Boolean> {

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        // Validate: Completed must be true
        if (value == null) {
            // Add custom validation message
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Completion status must be true or false")
                    .addConstraintViolation();
            return false; // Null is invalid, you can choose whether to treat it as false or not
        }
        return value; // The task must be marked as true
    }

    @Override
    public void initialize(ValidCompleted constraintAnnotation) {
        // No initialization required for this use case
    }
}
