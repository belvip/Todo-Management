package com.belvi.management_todo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    // TodoTDO is the request object

    private Long todoId;
    private String title;
    private String description;
    private boolean completed;
}
