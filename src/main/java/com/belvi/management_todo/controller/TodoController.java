package com.belvi.management_todo.controller;

import com.belvi.management_todo.model.Todo;
import com.belvi.management_todo.servie.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<List<Todo>> getAllTodos(){
        List<Todo> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    // Add new todo
    @PostMapping("api/public/todos")
    public ResponseEntity<String> addTodo(@RequestBody Todo todo){
        todoService.addTodo(todo);
        return new ResponseEntity<>("Todo added sucessfully", HttpStatus.CREATED);
    }

    // Delete todo
    @DeleteMapping("/api/admin/todos/{todoId}")
    public  ResponseEntity<String> deleteTodo(@PathVariable Long todoId){
        try{
            String status = todoService.deleteTodo(todoId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }

    }

    // Update Todo
    @PutMapping("api/public/todos/{todoId}")
    public ResponseEntity<String> updateTodo(@RequestBody Todo todo,
                                             @PathVariable Long todoId) {
        try {
            Todo savedTodo = todoService.updateTodo(todo, todoId);
            return new ResponseEntity<>("Todo with todo id " + savedTodo.getTodoId() + " updated successfully.", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

}
