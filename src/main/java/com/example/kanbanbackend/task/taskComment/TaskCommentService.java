package com.example.kanbanbackend.task.taskComment;

import com.example.kanbanbackend.task.taskComment.models.TaskComment;
import com.example.kanbanbackend.task.taskComment.models.TaskCommentCommand;
import com.example.kanbanbackend.task.taskComment.models.TaskCommentSetDto;

import java.util.Set;

public interface TaskCommentService {
    Set<TaskCommentSetDto> getComments();
    TaskComment addComment(TaskCommentCommand command);
    void editComment(TaskCommentCommand command);
    void deleteComment(TaskCommentCommand command);
}
