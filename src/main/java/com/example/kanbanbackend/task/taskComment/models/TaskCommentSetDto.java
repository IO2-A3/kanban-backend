package com.example.kanbanbackend.task.taskComment.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class TaskCommentSetDto {
    private UUID id;
    private String content;
    private Timestamp createdAt;
    private String userUsername;
}
