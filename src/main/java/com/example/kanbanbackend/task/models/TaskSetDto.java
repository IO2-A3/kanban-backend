package com.example.kanbanbackend.task.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskSetDto {
    private String taskId;
    private String taskName;
    private String taskListOrder;
}
