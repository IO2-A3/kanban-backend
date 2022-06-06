package com.example.kanbanbackend.task.taskComment;

import com.example.kanbanbackend.authentication.JwtUtil;
import com.example.kanbanbackend.task.taskComment.models.TaskCommentCommand;
import com.example.kanbanbackend.task.taskComment.models.TaskCommentEditInput;
import com.example.kanbanbackend.task.taskComment.models.TaskCommentSetDto;
import com.example.kanbanbackend.task.taskComment.models.TaskCommentWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/task/comment")
public class TaskCommentController {
    private final TaskCommentService service;
    private final JwtUtil jwtUtil;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Set<TaskCommentSetDto> getComments() {
        return service.getComments();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Commend added succesfully")
    public UUID addComment(@RequestBody TaskCommentWebInput input) {
        return service.addComment(TaskCommentCommand.builder()
                .taskId(input.getTaskId())
                .content(input.getContent()).build());
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Comment edited succesfully")
    public void editComment(@RequestBody TaskCommentEditInput input,
                            HttpServletRequest request) throws Exception {
        service.editComment(
                TaskCommentCommand.builder()
                        .taskCommentId(input.getCommentId())
                        .content(input.getContent())
                        .requestOwnerId(jwtUtil.getIdFromRequest(request)).build()
        );
    }

    @DeleteMapping("{taskCommentId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Comment deleted from task succesfully")
    public void deleteComment(@PathVariable("taskCommentId") UUID id,
                              HttpServletRequest request) throws Exception {
        service.deleteComment(
                TaskCommentCommand.builder()
                        .taskCommentId(id)
                        .requestOwnerId(jwtUtil.getIdFromRequest(request)).build()
        );
    }
}
