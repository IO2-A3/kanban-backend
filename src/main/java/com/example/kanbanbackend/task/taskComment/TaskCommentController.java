package com.example.kanbanbackend.task.taskComment;

import com.example.kanbanbackend.authentication.JwtUtil;
import com.example.kanbanbackend.task.taskComment.models.*;
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
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(code = HttpStatus.OK)
    public Set<TaskCommentSetDto> getComments() {
        return service.getComments();
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public TaskComment addComment(@RequestBody TaskCommentWebInput input) {
        return service.addComment(TaskCommentCommand.builder()
                .taskId(input.getTaskId())
                .content(input.getContent()).build());
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
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
    @PreAuthorize("isAuthenticated()")
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
