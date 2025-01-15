package com.belvi.management_todo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @Column(nullable = false)
    @NotBlank(message = "cannot be blank")
    @Size(min = 4, message = "must contain at least 4 characters")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "cannot be blank")
    private String description;

    @NotNull(message = "Completion status must not be null")
    @ValidCompleted
    @Column(nullable = false)
    private boolean completed;

}
