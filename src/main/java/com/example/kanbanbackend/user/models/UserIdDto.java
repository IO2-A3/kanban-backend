package com.example.kanbanbackend.user.models;

import com.example.kanbanbackend.task.models.TaskSetDto;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
public class UserIdDto {
    private String email;
    private String username;
    private Timestamp createdAt;
    private Set<TaskSetDto> tasks;
}
