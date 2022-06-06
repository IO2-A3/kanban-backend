package com.example.kanbanbackend.task.models;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AddUserToTaskDto {
    private String userEmail;
    private UUID taskId;
}
