package com.belvi.management_todo.exception;

import com.belvi.management_todo.payload.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyGlobalExceptionHandler.class);

    // Helper method to build APIResponse error response
    private ResponseEntity<APIResponse> buildErrorResponse(HttpStatus status, String error, String message, String path) {
        APIResponse response = new APIResponse();
        response.setMessage(String.format("%s: %s [Path: %s]", error, message, path));
        response.setStatus(false); // Indicates an error
        return new ResponseEntity<>(response, status);
    }

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        logger.error("Resource not found: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Resource Not Found", ex.getMessage(), request.getDescription(false));
    }

    // Handle InvalidTodoException
    @ExceptionHandler(InvalidTodoException.class)
    public ResponseEntity<APIResponse> handleInvalidTodoException(InvalidTodoException ex, WebRequest request) {
        logger.error("Invalid Todo Exception: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Todo", ex.getMessage(), request.getDescription(false));
    }

    // Handle validation errors from @Valid or @Validated
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        logger.error("Validation Error: {}", errorMessage);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error", errorMessage, request.getDescription(false));
    }

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse> handleGeneralException(Exception ex, WebRequest request) {
        logger.error("Unexpected error: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage(), request.getDescription(false));
    }

    // Handle TodoAlreadyExistsException
    @ExceptionHandler(TodoAlreadyExistsException.class)
    public ResponseEntity<APIResponse> handleTodoAlreadyExistsException(TodoAlreadyExistsException ex, WebRequest request) {
        logger.error("Todo already exists: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.CONFLICT, "Conflict", ex.getMessage(), request.getDescription(false));
    }

    // Handle APIException
    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse> handleAPIException(APIException ex, WebRequest request) {
        logger.error("API Exception: {}", ex.getMessage());
        return buildErrorResponse(ex.getStatus(), "API Error", ex.getMessage(), request.getDescription(false));
    }
}
