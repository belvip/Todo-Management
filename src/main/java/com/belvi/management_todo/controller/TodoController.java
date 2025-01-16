package com.belvi.management_todo.controller;

import com.belvi.management_todo.model.Todo;
import com.belvi.management_todo.payload.TodoResponse;
import com.belvi.management_todo.servie.TodoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService todoService;

    /*public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }*/

    // Get all todo
    @GetMapping("/public/todos")
    public ResponseEntity<TodoResponse> getAllTodos(){
        TodoResponse todoResponse = todoService.getAllTodos();
        return new ResponseEntity<>(todoResponse, HttpStatus.OK);
    }

    // Add new todo
    /*@PostMapping("/public/todos")
    public ResponseEntity<String> addTodo(@Valid @RequestBody Todo todo){
        todoService.addTodo(todo);
        return new ResponseEntity<>("Todo added sucessfully", HttpStatus.CREATED);
    }*/

    @PostMapping("/public/todos")
    public ResponseEntity<String> addTodo(@Valid @RequestBody Todo todo) {
        todoService.addTodo(todo);
        return new ResponseEntity<>("Todo added successfully", HttpStatus.CREATED);
    }

    // Delete todo
    @DeleteMapping("/admin/todos/{todoId}")
    public  ResponseEntity<String> deleteTodo(@PathVariable Long todoId){
        String status = todoService.deleteTodo(todoId);
        return new ResponseEntity<>(status, HttpStatus.OK);

    }

    // Update Todo
    @PutMapping("/public/todos/{todoId}")
    public ResponseEntity<String> updateTodo(@Valid @RequestBody Todo todo,
                                             @PathVariable Long todoId) {
        Todo savedTodo = todoService.updateTodo(todo, todoId);
        return new ResponseEntity<>("Todo with todo id "
                + savedTodo.getTodoId() +
                " updated successfully.", HttpStatus.OK);

    }

    @PatchMapping("{todoId}/complete")
    public ResponseEntity<String> completeTodo(@PathVariable Long todoId){
        Todo completeTodo = todoService.completeTodo(todoId);
        return new ResponseEntity<>("Todo with todo id " +
                completeTodo.getTodoId() + " completed successfully.",
                HttpStatus.OK);
    }

    @PatchMapping("{todoId}/in-complete")
    public ResponseEntity<String> inCompleteTodo(@PathVariable Long todoId){
        Todo inCompleteTodo = todoService.inCompleteTodo(todoId);
        return new ResponseEntity<>("Todo with todo id " +
                inCompleteTodo.getTodoId() + " completed successfully.",
                HttpStatus.OK);
    }

}
