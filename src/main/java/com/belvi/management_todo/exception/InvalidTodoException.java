package com.belvi.management_todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTodoException extends RuntimeException{
    public InvalidTodoException(String message){
        super(message);
    }
}
