package com.example.kanbanbackend.task.taskComment.models;

import com.example.kanbanbackend.user.models.UserListDto;
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
    private UserListDto user;
}
