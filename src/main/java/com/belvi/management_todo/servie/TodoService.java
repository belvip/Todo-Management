package com.belvi.management_todo.servie;

import com.belvi.management_todo.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();
    void addTodo(Todo todo);

    String deleteTodo(Long todoId);
}
