package com.example.kanbanbackend.task;

import com.example.kanbanbackend.task.models.TaskIdDto;
import com.example.kanbanbackend.task.models.TaskInputDto;
import com.example.kanbanbackend.task.models.TaskSetDto;
import lombok.AllArgsConstructor;
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
    public Set<TaskSetDto> getTasks(){
        return taskService.findTasks();
    }

    @GetMapping("{id}")
    public TaskIdDto getTask(@PathVariable UUID id){
        return taskService.findTask(id);
    }

    @PostMapping
    public UUID addTask(@Valid @RequestBody TaskInputDto taskInputDTO){
        return taskService.createTask(taskInputDTO);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable UUID id){
        taskService.removeTask(id);
    }

    @PutMapping("/{taskId}")
    public void changeOrder(@PathVariable UUID taskId, Integer order){
        taskService.changeOrder(taskId,order);
    }
}
