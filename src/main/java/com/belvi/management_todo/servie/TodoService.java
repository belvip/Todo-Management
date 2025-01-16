package com.belvi.management_todo.servie;

import com.belvi.management_todo.model.Todo;
import com.belvi.management_todo.payload.TodoDTO;
import com.belvi.management_todo.payload.TodoResponse;

import java.util.List;

public interface TodoService {
    TodoResponse getAllTodos();
    void addTodo(Todo todo);
    String deleteTodo(Long todoId);
    Todo updateTodo(Todo todo, Long todoId);
    Todo completeTodo(Long todoId);
    Todo inCompleteTodo(Long todoId);
}
