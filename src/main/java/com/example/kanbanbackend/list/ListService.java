package com.example.kanbanbackend.list;

import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.list.models.List;
import com.example.kanbanbackend.list.models.ListIdDto;
import com.example.kanbanbackend.list.models.ListInputDto;
import com.example.kanbanbackend.list.models.ListSetDto;
import com.example.kanbanbackend.project.ProjectRepository;
import com.example.kanbanbackend.task.TaskRepository;
import com.example.kanbanbackend.task.TaskService;
import com.example.kanbanbackend.task.models.Task;
import com.example.kanbanbackend.task.models.TaskSetDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ListService {
    private final ListRepository listRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final ModelMapper mapper;

    public UUID createList(ListInputDto listInputDTO){
        var project = projectRepository.findById(listInputDTO.getProjectId()).orElseThrow(() -> new IncorrectIdInputException("Wrong id!"));

        var quantity = project.getListSet().size();

        var list = List.builder()
                .id(UUID.randomUUID())
                .name(listInputDTO.getName())
                .project(project)
                .listOrder(++quantity)//todo: zrobic order, zliczac ile jest list w projekcie i dodawac na koncu z order ostatniej +1
                .build();

        listRepository.save(list);

        return list.getId();
    }

    public Set<ListSetDto> findLists(){
        var lists = listRepository.findAll();

        var listSet =  lists.stream()
                .map(list -> mapper.map(list, ListSetDto.class))
                .collect(Collectors.toSet());
        for (ListSetDto list : listSet) {
            list.setTaskSet(taskRepository.findByListId(list.getId()).stream()
                    .map(task -> mapper.map(task, TaskSetDto.class)).collect(Collectors.toSet()));
        }
        return listSet;
    }

    public ListIdDto findListById(UUID listId){
        var list = listRepository.findById(listId).orElseThrow(() -> new IncorrectIdInputException("Wrond id!"));
        return mapper.map(list, ListIdDto.class);
    }

    public void removeList(UUID listId){
        listRepository.deleteById(listId);
    }
}