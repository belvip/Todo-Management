package com.belvi.management_todo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyGlobalExceptionHandler.class);

    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String error, String message, String path) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        body.put("path", path);
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(InvalidTodoException.class)
    public ResponseEntity<Object> handleInvalidTodoException(InvalidTodoException ex) {
        logger.error("Invalid Todo Exception: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Todo", ex.getMessage(), "/api/public/todos");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        logger.error("Validation Error: {}", errorMessage);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error", errorMessage, "/api/public/todos");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        logger.error("Resource not found: {}", e.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND,
                "Resource Not Found", e.getMessage(),
                "/api/public/todos");
    }
}
