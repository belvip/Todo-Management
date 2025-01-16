package com.belvi.management_todo.servie.impl;

import com.belvi.management_todo.exception.APIException;
import com.belvi.management_todo.exception.InvalidTodoException;
import com.belvi.management_todo.exception.ResourceNotFoundException;
import com.belvi.management_todo.model.Todo;
import com.belvi.management_todo.repositories.TodoRepository;
import com.belvi.management_todo.servie.TodoService;
import com.zaxxer.hikari.util.FastList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // Mehod to add new Todo
    @Override
    public void addTodo(Todo todo) {
        Todo savedTodo = todoRepository.findByTitle(todo.getTitle());
        if (savedTodo != null) {
            throw new APIException(HttpStatus.CONFLICT, "Todo with the title " + todo.getTitle() + " already exists");
        }
        if (todo.getTitle().isEmpty() || todo.getDescription().isEmpty()) {
            throw new InvalidTodoException("Title and description cannot be empty");
        }
        todoRepository.save(todo);
    }

   // Method to delete Todo
   @Override
   public String deleteTodo(Long todoId) {
       Todo todo = todoRepository.findById(todoId)
               .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, "Todo with ID " + todoId + " not found"));
       todoRepository.delete(todo);
       return "Todo with ID " + todoId + " deleted successfully";
   }

    /*@Override
    public String deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "todoId", todoId));
        todoRepository.delete(todo);
        return "Todo with todo Id : " + todoId + " deleted successfully";
    }*/

    // Method to update todo
    @Override
    public Todo updateTodo(Todo todo, Long todoId) {
        Todo savedTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, "Todo with ID " + todoId + " not found"));
        todo.setTodoId(todoId);
        savedTodo = todoRepository.save(todo);
        return savedTodo;
    }


    @Override
    public Todo completeTodo(Long todoId) {
        Todo completeTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, "Todo with ID " + todoId + " not found"));
        completeTodo.setCompleted(true);
        return todoRepository.save(completeTodo);
    }

    @Override
    public Todo inCompleteTodo(Long todoId) {
        Todo inCompleteTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "todoId", todoId));
        inCompleteTodo.setCompleted(false);
        return todoRepository.save(inCompleteTodo);
    }
}
