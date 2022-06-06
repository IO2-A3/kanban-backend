package com.example.kanbanbackend.task;

import com.example.kanbanbackend.task.models.*;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Set<TaskSetDto> getTasks(){
        return taskService.findTasks();
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public TaskIdDto getTask(@PathVariable UUID id){
        return taskService.findTask(id);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public UUID addTask(@Valid @RequestBody TaskInputDto taskInputDTO){
        return taskService.createTask(taskInputDTO);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteTask(@PathVariable UUID id){
        taskService.removeTask(id);
    }

    @PutMapping()
    @PreAuthorize("isAuthenticated()")
    public void moveTask(@Valid @RequestBody TaskMoveDto dto){
        taskService.moveTask(dto);
    }

    @PutMapping("/editDescription")
    @PreAuthorize("isAuthenticated()")
    public void editTaskDescription(@RequestBody TaskDescriptionDto dto){
        taskService.editTaksDescription(dto);
    }

    @PostMapping("/user")
    @PreAuthorize("isAuthenticated()")
    public UserEmailNameDto addUserToTask(@RequestBody AddUserToTaskDto dto){
        return taskService.addUserToTask(dto);
    }
}
