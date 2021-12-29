package com.example.kanbanbackend.task.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
public class TaskInputDto {
    @NotBlank
    private String name;
    @NotNull
    private String listId;
    @NotBlank
    private String description;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss+0000",
            timezone = "Europe/Berlin")
    private Timestamp dueDate;
}