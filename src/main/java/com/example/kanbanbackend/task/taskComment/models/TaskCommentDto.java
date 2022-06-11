package com.example.kanbanbackend.task.taskComment.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class TaskCommentDto {
    private UUID id;
    private Timestamp editedAt;
    private String content;
}
