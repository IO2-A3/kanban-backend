package com.example.kanbanbackend.task.models;

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

    private Timestamp dueDate;

}