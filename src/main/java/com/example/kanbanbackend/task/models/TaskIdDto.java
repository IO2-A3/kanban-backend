package com.example.kanbanbackend.task.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TaskIdDto {
    private String listId;
    private String name;
    private String description;
    private Timestamp dueDate;
}
