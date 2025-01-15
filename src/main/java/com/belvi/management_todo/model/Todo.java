package com.belvi.management_todo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 4, message = "Title must contain at least 4 characters")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Description cannot be blank")
    private String description;

    private boolean completed;

}
