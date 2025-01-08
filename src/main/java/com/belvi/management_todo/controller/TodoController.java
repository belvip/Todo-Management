package com.belvi.management_todo.controller;

import com.belvi.management_todo.model.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {
    private List<Todo> todos = new ArrayList<>();

    @GetMapping("api/todos")
    public List<Todo> getAllTodos(){

        return todos;
    }
}
