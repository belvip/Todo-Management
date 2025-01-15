package com.belvi.management_todo.model;

import jakarta.persistence.*;
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
    private String title;

    @Column(nullable = false)
    private String description;
    private boolean completed;

}
