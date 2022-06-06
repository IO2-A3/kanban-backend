package com.example.kanbanbackend.task.models;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TaskDescriptionDto {
    private UUID taskId;
    private String description;
}
