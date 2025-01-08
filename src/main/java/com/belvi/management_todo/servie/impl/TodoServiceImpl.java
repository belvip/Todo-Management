package com.belvi.management_todo.servie.impl;

import com.belvi.management_todo.model.Todo;
import com.belvi.management_todo.servie.TodoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private List<Todo> todos = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Todo> getAllTodos() {
        return todos;
    }

    @Override
    public void addTodo(Todo todo) {
        todo.setTodoId(nextId++);
        todos.add(todo);

    }

    @Override
    public String deleteTodo(Long todoId) {
        Todo todo = todos.stream()
                .filter(t -> t.getTodoId().equals(todoId))
                .findFirst().get();
        todos.remove(todo);
        return "Todo with todoId : " + todoId + " deleted sucessfully";
    }
}
