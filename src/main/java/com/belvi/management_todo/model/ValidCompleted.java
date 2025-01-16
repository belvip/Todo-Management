package com.belvi.management_todo.model;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = com.belvi.management_todo.servie.impl.CompletedTaskValidator.class) // Full path
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCompleted {
    String message() default "Task must be marked as true or false to be completed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
