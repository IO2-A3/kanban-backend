package com.example.kanbanbackend.task.taskComment.models;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TaskCommentWebInput {
    private UUID taskId;
    private UUID userId;
    private String content;
}
