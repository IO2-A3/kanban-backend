package com.example.kanbanbackend.task.taskMember;

import com.example.kanbanbackend.authentication.JwtUtil;
import com.example.kanbanbackend.task.taskMember.models.TaskMemberCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/task/{taskId}/user/{userId}")
public class TaskMemberController {
    private final TaskMemberService service;
    private final JwtUtil jwtUtil;

    @PostMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "User assigned to task succesfully")
    @PreAuthorize("hasAuthority(#id)")
    public void assignTaskToUser(@PathVariable("taskId") UUID taskId,
                                 @PathVariable("userId") UUID userId,
                                 HttpServletRequest request) throws Exception {


        service.assignTaskToUser(TaskMemberCommand.builder()
                .taskId(taskId)
                .userId(userId)
                .requestOwnerId(jwtUtil.getIdFromRequest(request)).build());
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "User deleted from task succesfully")
    @PreAuthorize("hasAuthority(#id)")
    public void deleteUserFromTask(@PathVariable("taskId") UUID taskId,
                                 @PathVariable("userId") UUID userId,
                                 HttpServletRequest request) throws Exception {
        service.deleteUserFromTask(TaskMemberCommand.builder()
                .taskId(taskId)
                .userId(userId)
                .requestOwnerId(jwtUtil.getIdFromRequest(request)).build());
    }
}