package com.belvi.management_todo.model;

import jakarta.persistence.*;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;
    private String title;
    private String description;
    private boolean completed;

    public Todo(Long todoId, String title, String description, boolean completed) {
        this.todoId = todoId;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public Todo() {

    }

    public Long getTodoId() {

        return todoId;
    }

    public void setTodoId(Long todoId) {

        this.todoId = todoId;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public boolean isCompleted() {

        return completed;
    }

    public void setCompleted(boolean completed) {

        this.completed = completed;
    }
}
