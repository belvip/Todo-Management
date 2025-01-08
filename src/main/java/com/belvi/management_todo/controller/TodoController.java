package com.belvi.management_todo.controller;

import com.belvi.management_todo.model.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {
    // List of todos
    private List<Todo> todos = new ArrayList<>();

    // Get all todos
    @GetMapping("api/todos")
    public List<Todo> getAllTodos(){

        return todos;
    }

    // Add new todo
    @PostMapping("api/todos")
    public String addTodo(@RequestBody Todo todo){
        todos.add(todo);
        return "Todo added sucessfully";
    }
}
