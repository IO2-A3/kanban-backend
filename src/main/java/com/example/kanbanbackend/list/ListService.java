package com.example.kanbanbackend.list;

import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.list.models.List;
import com.example.kanbanbackend.list.models.ListIdDto;
import com.example.kanbanbackend.list.models.ListInputDto;
import com.example.kanbanbackend.list.models.ListSetDto;
import com.example.kanbanbackend.project.ProjectRepository;
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
    private final ModelMapper mapper;

    public void createList(ListInputDto listInputDTO){
        projectRepository.findById(listInputDTO.getProjectId()).orElseThrow(() -> new IncorrectIdInputException("Wrong id!"));
        var quantity = listRepository.count();

        var list = List.builder()
                .id(UUID.randomUUID().toString())
                .name(listInputDTO.getName())
                .projectId(listInputDTO.getProjectId())
                .listOrder((int) ++quantity)//todo: zrobic order, zliczac ile jest list w projekcie i dodawac na koncu z order ostatniej +1
                .build();

        listRepository.save(list);
    }

    public Set<ListSetDto> findLists(){
        var lists = listRepository.findAll();

        return lists.stream()
                .map(list -> mapper.map(list, ListSetDto.class))
                .collect(Collectors.toSet());
    }

    public ListIdDto findListById(String listId){
        var list = listRepository.findById(listId).orElseThrow(() -> new IncorrectIdInputException("Wrond id!"));
        return mapper.map(list, ListIdDto.class);
    }

    public void removeList(String listId){
        listRepository.deleteById(listId);
    }
}