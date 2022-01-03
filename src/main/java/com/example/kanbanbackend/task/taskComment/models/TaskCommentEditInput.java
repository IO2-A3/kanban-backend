package com.example.kanbanbackend.task.taskComment.models;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TaskCommentEditInput {
    private UUID commentId;
    private String content;
}
