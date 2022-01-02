package com.example.kanbanbackend.task.taskMember;


import com.example.kanbanbackend.exceptions.ForbiddenException;
import com.example.kanbanbackend.project.ProjectMember.ProjectMemberRepository;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberKey;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectRole;
import com.example.kanbanbackend.task.TaskRepository;
import com.example.kanbanbackend.task.models.Task;
import com.example.kanbanbackend.task.taskMember.models.TaskMemberCommand;
import com.example.kanbanbackend.user.UserRepository;
import com.example.kanbanbackend.user.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskMemberServiceImpl implements TaskMemberService{
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectMemberRepository projectMemberRepository;

    @Override
    public void assignTaskToUser(TaskMemberCommand command) {
        var task = taskRepository.findById(command.getTaskId()).orElseThrow();
        var user = getUser(command, task);

        task.addUser(user);
        taskRepository.save(task);
    }

    @Override
    public void deleteUserFromTask(TaskMemberCommand command) {
        var task = taskRepository.findById(command.getTaskId()).orElseThrow();
        var user = getUser(command, task);

        task.deleteAnUser(user);
        taskRepository.save(task);
    }

    private User getUser(TaskMemberCommand command, Task task) {
        var user = userRepository.findById(command.getUserId()).orElseThrow();

        var requestOwner = userRepository.findById(command.getRequestOwnerId()).orElseThrow();
        validateRequestOwner(task, requestOwner);

        return user;
    }

    private void validateRequestOwner(Task task, User requestOwner) {
        var project = task.getList().getProject();
        var projectMemberKey = new ProjectMemberKey(requestOwner, project);

        var requestProjectMember = projectMemberRepository.findById(projectMemberKey).orElseThrow();
        if (requestProjectMember.getRole() != ProjectRole.OWNER) {
            throw new ForbiddenException();
        }
    }
}
