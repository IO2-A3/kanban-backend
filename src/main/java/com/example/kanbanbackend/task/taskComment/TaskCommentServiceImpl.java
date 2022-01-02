package com.example.kanbanbackend.task.taskComment;

import com.example.kanbanbackend.exceptions.ForbiddenException;
import com.example.kanbanbackend.project.ProjectMember.ProjectMemberRepository;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberKey;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectRole;
import com.example.kanbanbackend.task.TaskRepository;
import com.example.kanbanbackend.task.models.Task;
import com.example.kanbanbackend.task.taskComment.models.TaskComment;
import com.example.kanbanbackend.task.taskComment.models.TaskCommentCommand;
import com.example.kanbanbackend.task.taskComment.models.TaskCommentSetDto;
import com.example.kanbanbackend.user.UserRepository;
import com.sun.xml.bind.v2.schemagen.episode.SchemaBindings;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskCommentServiceImpl implements TaskCommentService{
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskCommentRepository taskCommentRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ModelMapper mapper;

    @Override
    public Set<TaskCommentSetDto> getComments() {
        var comments = taskCommentRepository.findAll();

        return comments.stream()
                .map(comment -> mapper.map(comment, TaskCommentSetDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public UUID addComment(TaskCommentCommand command) {
        var user = userRepository.findById(command.getUserId()).orElseThrow();
        var task = taskRepository.findById(command.getTaskId()).orElseThrow();

        var currentTime = new Timestamp(System.currentTimeMillis());
        var id = UUID.randomUUID();

        taskCommentRepository.save(
                TaskComment.builder()
                        .id(id)
                        .content(command.getContent())
                        .task(task)
                        .user(user)
                        .createdAt(currentTime)
                        .editedAt(currentTime).build()
        );

        return id;
    }

    @Override
    public void editComment(TaskCommentCommand command) {
        var comment = taskCommentRepository.findById(command.getTaskCommentId()).orElseThrow();

        if (!comment.getUser().getId().equals(command.getRequestOwnerId())) {
            throw new ForbiddenException();
        }

        comment.setContent(command.getContent());

        taskCommentRepository.save(comment);
    }

    @Override
    public void deleteComment(TaskCommentCommand command) {
        var comment = taskCommentRepository.findById(command.getTaskCommentId()).orElseThrow();

        if (!comment.getUser().getId().equals(command.getRequestOwnerId())) {
            validateRequestOwner(comment.getTask(), command.getRequestOwnerId());
        }

        taskCommentRepository.delete(comment);
    }

    private void validateRequestOwner(Task task, UUID requestOwnerId) {
        var requestOwner = userRepository.findById(requestOwnerId).orElseThrow();
        var project = task.getList().getProject();
        var projectMemberKey = new ProjectMemberKey(requestOwner, project);

        var requestProjectMember = projectMemberRepository.findById(projectMemberKey).orElseThrow();
        if (requestProjectMember.getRole() != ProjectRole.OWNER) {
            throw new ForbiddenException();
        }
    }
}
