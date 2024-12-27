package com.vakin.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ForumTopicDto {
    @NotBlank(message = "Необходимо указать тему")
    @Size(min = 3, max = 50, message = "Название должно быть от 3 до 50 символов")
    private String name;
    @NotBlank(message = "Необходимо описание")
    private String description;
}
