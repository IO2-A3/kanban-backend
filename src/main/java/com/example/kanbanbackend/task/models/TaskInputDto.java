package com.example.kanbanbackend.task.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
public class TaskInputDto {
    @NotBlank
    private String name;
    @NotNull
    private UUID listId;
    private String description;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "Europe/Berlin")
    private Timestamp dueDate;
}