package com.belvi.management_todo.controller;

import com.belvi.management_todo.model.Todo;
import com.belvi.management_todo.payload.TodoDTO;
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

    // Build Add Todo REST API
    @PostMapping("/public/todos")
    public ResponseEntity<TodoDTO> addTodo(@Valid @RequestBody TodoDTO todoDTO) {
        TodoDTO savedTodoDTO = todoService.addTodo(todoDTO);
        return new ResponseEntity<>(savedTodoDTO, HttpStatus.CREATED);
    }

    // Build Delete Todo REST API
    @DeleteMapping("/admin/todos/{todoId}")
    public  ResponseEntity<TodoDTO> deleteTodo(@PathVariable Long todoId){
        TodoDTO deletedTodo = todoService.deleteTodo(todoId);
        return new ResponseEntity<>(deletedTodo, HttpStatus.OK);

    }

    // Build Update Todo REST API
    @PutMapping("/public/todos/{todoId}")
    public ResponseEntity<TodoDTO> updateTodo(@Valid @RequestBody TodoDTO todoDTO,
                                             @PathVariable Long todoId) {
        TodoDTO savedTodoDTO = todoService.updateTodo(todoDTO, todoId);
        return new ResponseEntity<>(savedTodoDTO, HttpStatus.OK);

    }

    // Build Complete Todo REST API
    @PatchMapping("{todoId}/complete")
    public ResponseEntity<TodoDTO> completeTodo(@PathVariable Long todoId){
        TodoDTO completeTodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(completeTodo);

        /*return new ResponseEntity<>("Todo with todo id " +
                completeTodo.getTodoId() + " completed successfully.",
                HttpStatus.OK); */
    }

    // Build Incomplete Todo REST API
    @PatchMapping("{todoId}/in-complete")
    public ResponseEntity<TodoDTO> inCompleteTodo(@PathVariable Long todoId){
        TodoDTO inCompleteTodo = todoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(inCompleteTodo);
    }

}
