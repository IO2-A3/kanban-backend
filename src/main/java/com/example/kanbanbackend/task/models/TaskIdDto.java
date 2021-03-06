package com.example.kanbanbackend.task.models;

import com.example.kanbanbackend.task.taskComment.models.TaskCommentSetDto;
import com.example.kanbanbackend.user.models.UserListDto;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class TaskIdDto {
    private UUID listId;
    private UUID id;
    private String name;
    private String description;
    private Timestamp dueDate;
    private Set<UserListDto> users;
    private Set<TaskCommentSetDto> comments;
}
