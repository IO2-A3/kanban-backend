package com.example.kanbanbackend.task;

import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.list.ListRepository;
import com.example.kanbanbackend.task.models.Task;
import com.example.kanbanbackend.task.models.TaskIdDto;
import com.example.kanbanbackend.task.models.TaskInputDto;
import com.example.kanbanbackend.task.models.TaskSetDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ListRepository listRepository;
    private final ModelMapper mapper;

    public void createTask(TaskInputDto taskInputDTO){
        listRepository.findById(taskInputDTO.getListId()).orElseThrow(() -> new IllegalArgumentException("List with this id don't exist"));

        var task = Task.builder()
                .id(UUID.randomUUID().toString())
                .name(taskInputDTO.getName())
                .description(taskInputDTO.getDescription())
                .listId(taskInputDTO.getListId())
                .listOrder(1).build(); //todo: order tak samo jak w listach.dueDate(taskInputDTO.getDueDate()).build();
        taskRepository.save(task);
    }

    public Set<TaskSetDto> findTasks() {
        var tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> mapper.map(task, TaskSetDto.class))
                .collect(Collectors.toSet());
    }

    public TaskIdDto findTask(String taskId) {
        var task = taskRepository.findById(taskId).orElseThrow(() -> new IncorrectIdInputException("Wrong id!"));
        return mapper.map(task, TaskIdDto.class);
    }

    public void removeTask(String taskId){
        taskRepository.deleteById(taskId);
    }
}