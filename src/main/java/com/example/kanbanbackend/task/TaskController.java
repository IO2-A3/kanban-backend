package com.example.kanbanbackend.task;

import com.example.kanbanbackend.task.models.TaskIdDto;
import com.example.kanbanbackend.task.models.TaskInputDto;
import com.example.kanbanbackend.task.models.TaskSetDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

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
    public TaskIdDto getTask(@PathVariable String id){
        return taskService.findTask(id);
    }

    @PostMapping
    public void addTask(@Valid @RequestBody TaskInputDto taskInputDTO){
        taskService.createTask(taskInputDTO);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable String id){
        taskService.removeTask(id);
    }
}
