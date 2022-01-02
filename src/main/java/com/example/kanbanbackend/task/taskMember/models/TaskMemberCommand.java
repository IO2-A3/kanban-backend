package com.example.kanbanbackend.task.taskMember.models;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class TaskMemberCommand {
    private final UUID taskId;
    private final UUID userId;
    private final UUID requestOwnerId;
}
