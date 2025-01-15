package com.belvi.management_todo.repositories;

import com.belvi.management_todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
