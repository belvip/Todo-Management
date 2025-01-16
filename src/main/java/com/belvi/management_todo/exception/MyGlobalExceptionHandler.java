package com.belvi.management_todo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyGlobalExceptionHandler.class);

    // Helper method to build error response
    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String error, String message, String path) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        body.put("path", path);
        body.put("traceId", UUID.randomUUID().toString()); // Example trace ID
        return new ResponseEntity<>(body, status);
    }


    // Handle InvalidTodoException
    @ExceptionHandler(InvalidTodoException.class)
    public ResponseEntity<Object> handleInvalidTodoException(InvalidTodoException ex, WebRequest request) {
        logger.error("Invalid Todo Exception: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Todo", ex.getMessage(), request.getDescription(false));
    }

    // Handle validation errors from @Valid or @Validated
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        logger.error("Validation Error: {}", errorMessage);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error", errorMessage, request.getDescription(false));
    }

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        logger.error("Resource not found: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Resource Not Found", ex.getMessage(), request.getDescription(false));
    }

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        logger.error("Unexpected error: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage(), request.getDescription(false));
    }

    // Handle other custom exceptions (if any)
    @ExceptionHandler(TodoAlreadyExistsException.class)
    public ResponseEntity<Object> handleTodoAlreadyExistsException(TodoAlreadyExistsException ex, WebRequest request) {
        logger.error("Todo already exists: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.CONFLICT, "Conflict", ex.getMessage(), request.getDescription(false));
    }

    // Handle APIException (To apply when todo exist ect ...
    @ExceptionHandler(APIException.class)
    public ResponseEntity<Object> handleAPIException(APIException ex, WebRequest request) {
        logger.error("API Exception: {}", ex.getMessage());
        return buildErrorResponse(ex.getStatus(), "API Error", ex.getMessage(), request.getDescription(false));
    }



}
