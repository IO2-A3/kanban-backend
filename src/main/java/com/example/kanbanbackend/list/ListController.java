package com.example.kanbanbackend.list;

import com.example.kanbanbackend.list.models.ListIdDto;
import com.example.kanbanbackend.list.models.ListInputDto;
import com.example.kanbanbackend.list.models.ListSetDto;
import com.example.kanbanbackend.security.OwnerPermission;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;


@RestController
@RequestMapping("/api/list")
@AllArgsConstructor
public class ListController {
    private final ListService listService;

    @GetMapping
    public Set<ListSetDto> getLists(){
        return listService.findLists();
    }

    @GetMapping("{id}")
    public ListIdDto getListById(@PathVariable UUID id){
        return listService.findListById(id);
    }

    @PostMapping
    @OwnerPermission(value = "#inputDto.projectId")
    public UUID addList(@Valid @RequestBody ListInputDto inputDto){
        return listService.createList(inputDto);
    }

    @DeleteMapping("{id}")
    public void deleteList(@PathVariable UUID id){
        listService.removeList(id);
    }
}

