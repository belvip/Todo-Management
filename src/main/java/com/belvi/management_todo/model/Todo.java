package com.belvi.management_todo.model;

public class Todo {
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
