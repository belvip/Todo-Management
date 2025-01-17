package com.belvi.management_todo.servie.impl;

import com.belvi.management_todo.exception.APIException;
import com.belvi.management_todo.exception.InvalidTodoException;
import com.belvi.management_todo.exception.ResourceNotFoundException;
import com.belvi.management_todo.model.Todo;
import com.belvi.management_todo.payload.TodoDTO;
import com.belvi.management_todo.payload.TodoResponse;
import com.belvi.management_todo.repositories.TodoRepository;
import com.belvi.management_todo.servie.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TodoResponse getAllTodos(Integer pageNumber, Integer pageSize) {

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize);
        Page<Todo> todoPage = todoRepository.findAll(pageDetails);

        // Retrieve the list of todos
        List<Todo> todos = todoPage.getContent();

        // Check if the list is empty
        if (todos.isEmpty()) {
            throw new APIException(HttpStatus.NOT_FOUND, "No todo created till now");
        }

        List<TodoDTO> todoDTOS = todos.stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .toList();

        TodoResponse todoResponse = new TodoResponse();
        todoResponse.setContent(todoDTOS);
        todoResponse.setPageNumber(todoPage.getNumber());
        todoResponse.setPageSize(todoPage.getSize());
        todoResponse.setTotalElements(todoPage.getTotalElements());
        todoResponse.setTotalPages(todoPage.getTotalPages());
        todoResponse.setLastPage(todoPage.isLast());

        return todoResponse;
    }



    // Mehod to add new Todo
    @Override
    public TodoDTO addTodo(TodoDTO todoDTO) {
        // Convert TodoDTO to Todo entity
        Todo todo = modelMapper.map(todoDTO, Todo.class);

        // Check if a Todo with the same title already exist
        Todo todoFromDb = todoRepository.findByTitle(todo.getTitle());
        if (todoFromDb != null) {
            throw new APIException(HttpStatus.CONFLICT, "Todo with the title " + todo.getTitle() + " already exists");
        }
        if (todo.getTitle().isEmpty() || todo.getDescription().isEmpty()) {
            throw new InvalidTodoException("Title and description cannot be empty");
        }
        Todo savedTodo =  todoRepository.save(todo);

        return modelMapper.map(savedTodo, TodoDTO.class);
    }

   // Method to delete Todo
   @Override
   public TodoDTO deleteTodo(Long todoId) {

       Todo todo = todoRepository.findById(todoId)
               .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, "Todo with ID " + todoId + " not found"));
       todoRepository.delete(todo);

       return modelMapper.map(todo, TodoDTO.class);
   }

    // Method to update todo
    @Override
    public TodoDTO updateTodo(TodoDTO todoDTO, Long todoId) {
        Todo savedTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, "Todo with ID " + todoId + " not found"));

        Todo todo = modelMapper.map(todoDTO, Todo.class);
        todo.setTodoId(todoId);
        savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDTO.class);
    }

    @Override
    public TodoDTO completeTodo(Long todoId) {
        // Retrieve the Todo entity by its ID
        Todo completeTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, "Todo with ID " + todoId + " not found"));

        // Set the 'completed' field to true
        completeTodo.setCompleted(true);

        // Save the updated entity back to the database
        todoRepository.save(completeTodo);

        // Return the updated Todo as a TodoDTO
        return modelMapper.map(completeTodo, TodoDTO.class);
    }

    @Override
    public TodoDTO inCompleteTodo(Long todoId) {
        // Retrieve the Todo entity by its ID
        Todo inCompleteTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "todoId", todoId));

        // Set the 'completed' field to false
        inCompleteTodo.setCompleted(false);

        // Save the updated entity back to the database
        todoRepository.save(inCompleteTodo);

        // Return the updated Todo as a TodoDTO
        return modelMapper.map(inCompleteTodo, TodoDTO.class);
    }

    /*@Override
    public String deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "todoId", todoId));
        todoRepository.delete(todo);
        return "Todo with todo Id : " + todoId + " deleted successfully";
    }*/
}
