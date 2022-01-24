package com.example.kanbanbackend.task.models;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class TaskMoveDto {
    @NotNull
    private UUID taskId;
    @NotNull
    private UUID listId;
    @NotNull
    private int order;
}
