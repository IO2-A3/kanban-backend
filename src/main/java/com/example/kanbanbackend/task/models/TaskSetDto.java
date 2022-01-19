package com.example.kanbanbackend.task.models;

import com.example.kanbanbackend.task.taskComment.models.TaskCommentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class TaskSetDto {
    private UUID taskId;
    private String taskName;
    private String taskListOrder;
    private Set<TaskCommentDto> comments;
    /* @TODO: Add participants, dueDate etc. there is no need for this DTO altogether, all we need is TaskIdDto */
}
