package com.belvi.management_todo.repositories;

import com.belvi.management_todo.model.Todo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    Todo findByTitle(@NotBlank(message = "cannot be blank") @Size(min = 4, message = "must contain at least 4 characters") String title);
}
