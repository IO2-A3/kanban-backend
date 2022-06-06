package com.example.kanbanbackend.task.models;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
public class TaskDueDateEditDto {
    private UUID taskId;
    private Timestamp dueDate;
}
