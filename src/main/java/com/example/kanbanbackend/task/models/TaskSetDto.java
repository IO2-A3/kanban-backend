package com.example.kanbanbackend.task.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TaskSetDto {
    private UUID taskId;
    private String taskName;
    private String taskListOrder;
}
