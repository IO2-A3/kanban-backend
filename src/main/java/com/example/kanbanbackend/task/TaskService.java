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

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ListRepository listRepository;
    private final ModelMapper mapper;

    public UUID createTask(TaskInputDto taskInputDTO){
        listRepository.findById(taskInputDTO.getListId()).orElseThrow(() -> new IllegalArgumentException("List with this id don't exist"));
        var quantity = taskRepository.count();

        var task = Task.builder()
                .id(UUID.randomUUID())
                .name(taskInputDTO.getName())
                .description(taskInputDTO.getDescription())
                .list(listRepository.findById(taskInputDTO.getListId()).get())
                .dueDate(taskInputDTO.getDueDate())
                .listOrder((int) ++quantity).build(); //todo: order tak samo jak w listach.dueDate(taskInputDTO.getDueDate()).build();
        taskRepository.save(task);
        return task.getId();
    }

    public Set<TaskSetDto> findTasks() {
        var tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> mapper.map(task, TaskSetDto.class))
                .collect(Collectors.toSet());
    }

    public TaskIdDto findTask(UUID taskId) {
        var task = taskRepository.findById(taskId).orElseThrow(() -> new IncorrectIdInputException("Wrong id!"));
        return mapper.map(task, TaskIdDto.class);
    }

    public void changeOrder(UUID taksId, Integer order){
        var task = taskRepository.findById(taksId).get();
        task.setListOrder(order);
        taskRepository.save(task);
    }

    public void removeTask(UUID taskId){
        taskRepository.deleteById(taskId);
    }
}