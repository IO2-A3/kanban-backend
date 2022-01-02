package com.example.kanbanbackend.task.taskMember;

import com.example.kanbanbackend.task.taskMember.models.TaskMemberCommand;
import org.springframework.stereotype.Service;

@Service
public interface TaskMemberService {
    void assignTaskToUser(TaskMemberCommand command);
    void deleteUserFromTask(TaskMemberCommand command);
}
