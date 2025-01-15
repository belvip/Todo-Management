package com.belvi.management_todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Handle exceptions globally for controllers
public class

GlobalExceptionHandler {

    // Handle custom exception InvalidTodoException
    @ExceptionHandler(InvalidTodoException.class)
    public ResponseEntity<String> handleInvalidTodoException(InvalidTodoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handle validation errors (e.g. @NotBlank) from @Valid annotation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .findFirst()
                .orElse("Invalid input");
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
