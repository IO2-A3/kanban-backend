package com.example.kanbanbackend.task;

import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.list.ListRepository;
import com.example.kanbanbackend.task.models.*;
import com.example.kanbanbackend.user.UserRepository;
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
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public Task createTask(TaskInputDto taskInputDTO){
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
        return task;
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

    public void moveTask(TaskMoveDto dto){
        var task = taskRepository.findById(dto.getTaskId()).orElseThrow(() -> new IncorrectIdInputException("Wrong id!"));
        task.setList(listRepository.findById(dto.getListId()).orElseThrow(() -> new IncorrectIdInputException("Wrong id!")));
        task.setListOrder(dto.getOrder());
        taskRepository.save(task);
    }

    public void editTaksDescription(TaskDescriptionDto dto){
        var task = taskRepository.findById(dto.getTaskId()).get();
        var newDescription = dto.getDescription();
        task.setDescription(newDescription);
        taskRepository.save(task);
    }

    //username i email czlonka dodanego do taksa ma zwracac dodawanie czlonka do taska
    public UserEmailNameDto addUserToTask(AddUserToTaskDto dto){
        var task = taskRepository.findById(dto.getTaskId()).get();
        var user = userRepository.findByEmail(dto.getUserEmail()).get();

        var taskUsers = task.getUsers();
        taskUsers.add(user);
        taskRepository.save(task);
        return new UserEmailNameDto(user.getEmail(),user.getUsername());
    }


    public void removeTask(UUID taskId){
        taskRepository.deleteById(taskId);
    }
}