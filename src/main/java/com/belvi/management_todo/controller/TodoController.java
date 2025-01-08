package com.belvi.management_todo.controller;

import com.belvi.management_todo.model.Todo;
import com.belvi.management_todo.servie.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    /*public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }*/

    // Get all todo
    @GetMapping("api/public/todos")
    public List<Todo> getAllTodos(){

        return todoService.getAllTodos();
    }

    // Add new todo
    @PostMapping("api/public/todos")
    public String addTodo(@RequestBody Todo todo){
        todoService.addTodo(todo);
        return "Todo added sucessfully";
    }

    // Delete todo
    @DeleteMapping("/api/admin/todos/{todoId}")
    public  String deleteTodo(@PathVariable Long todoId){
        String status = todoService.deleteTodo(todoId);

        return status;

    }
}
