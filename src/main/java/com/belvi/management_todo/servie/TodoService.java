package com.belvi.management_todo.servie;

import com.belvi.management_todo.model.Todo;
import com.belvi.management_todo.payload.TodoDTO;
import com.belvi.management_todo.payload.TodoResponse;

import java.util.List;

public interface TodoService {
    TodoResponse getAllTodos();
    TodoDTO addTodo(TodoDTO todoDTO);
    TodoDTO deleteTodo(Long todoId);
    TodoDTO updateTodo(TodoDTO todoDTO, Long todoId);
    TodoDTO completeTodo(Long todoId);
    TodoDTO inCompleteTodo(Long todoId);
}
