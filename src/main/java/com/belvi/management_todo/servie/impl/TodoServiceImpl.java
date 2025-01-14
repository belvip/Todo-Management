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
        List<Todo> todos = todoRepository.findAll();
        Todo todo = todos.stream()
                .filter(t -> t.getTodoId().equals(todoId))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        todoRepository.delete(todo);
        return "Todo with todoId : " + todoId + " deleted sucessfully";
    }

    @Override
    public Todo updateTodo(Todo todo, Long todoId) {
        List<Todo> todos = todoRepository.findAll();
        Optional<Todo> optionalTodo = todos.stream()
                .filter(t -> t.getTodoId().equals(todoId))
                .findFirst();

        if(optionalTodo.isPresent()){
            Todo existingTodo = optionalTodo.get();
            existingTodo.setTitle(todo.getTitle());
            existingTodo.setDescription(todo.getDescription());
            existingTodo.setCompleted(todo.isCompleted());
            Todo savedTodo = todoRepository.save(existingTodo);
            return savedTodo;
        }else {
            throw new ResponseStatusException(NOT_FOUND, "Todo not found");
        }

    }


}
