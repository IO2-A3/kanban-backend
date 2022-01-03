package com.example.kanbanbackend.task.taskComment.models;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class TaskCommentCommand {
    private final UUID taskCommentId;
    private final UUID taskId;
    private final UUID userId;
    private final UUID requestOwnerId;
    private final String content;
}
