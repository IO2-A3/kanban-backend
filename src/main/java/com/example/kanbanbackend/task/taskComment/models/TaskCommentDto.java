package com.example.kanbanbackend.task.taskComment.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TaskCommentDto {
    private String userFirstName;
    private String userLastName;
    private Timestamp editedAt;
    private String content;
}
