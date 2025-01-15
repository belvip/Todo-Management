package com.belvi.management_todo.servie.impl;

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
    //private List<Todo> todos = new ArrayList<>();
    //private Long nextId = 1L;

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public void addTodo(Todo todo) {
        //todo.setTodoId(nextId++);
        todoRepository.save(todo);


    }

    @Override
    public String deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        todoRepository.delete(todo);
        return "Todo with todoId : " + todoId + " deleted sucessfully";
    }

    @Override
    public Todo updateTodo(Todo todo, Long todoId) {
        Todo savedTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        todo.setTodoId(todoId);
        savedTodo = todoRepository.save(todo);
        return savedTodo;

    }

    @Override
    public Todo completeTodo(Long todoId) {
        Todo completeTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        completeTodo.setCompleted(true);
        Todo savedTodo = todoRepository.save(completeTodo);
        return savedTodo;

    }

    @Override
    public Todo inCompleteTodo(Long todoId) {
        Todo inCompleteTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        inCompleteTodo.setCompleted(false);
        Todo savedTodo = todoRepository.save(inCompleteTodo);
        return savedTodo;
    }




}
