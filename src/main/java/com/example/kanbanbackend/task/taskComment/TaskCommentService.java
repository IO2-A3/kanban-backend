package com.example.kanbanbackend.task.taskComment;

import com.example.kanbanbackend.task.taskComment.models.TaskCommentCommand;
import com.example.kanbanbackend.task.taskComment.models.TaskCommentSetDto;

import java.util.Set;
import java.util.UUID;

public interface TaskCommentService {
    Set<TaskCommentSetDto> getComments();
    UUID addComment(TaskCommentCommand command);
    void editComment(TaskCommentCommand command);
    void deleteComment(TaskCommentCommand command);
}
