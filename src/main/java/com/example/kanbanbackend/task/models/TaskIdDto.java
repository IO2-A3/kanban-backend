package com.example.kanbanbackend.task.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class TaskIdDto {
    private UUID listId;
    private String name;
    private String description;
    private Timestamp dueDate;
}
