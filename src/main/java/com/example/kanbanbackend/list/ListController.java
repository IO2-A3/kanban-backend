package com.example.kanbanbackend.list;

import com.example.kanbanbackend.list.models.ListIdDto;
import com.example.kanbanbackend.list.models.ListInputDto;
import com.example.kanbanbackend.list.models.ListSetDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

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
    public ListIdDto getListById(@PathVariable String id){
        return listService.findListById(id);
    }

    @PostMapping
    public void addList(@Valid @RequestBody ListInputDto listInputDTO){
        listService.createList(listInputDTO);
    }

    @DeleteMapping("{id}")
    public void deleteList(String id){
        listService.removeList(id);
    }
}
