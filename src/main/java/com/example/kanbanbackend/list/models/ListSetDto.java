package com.example.kanbanbackend.list.models;

import com.example.kanbanbackend.task.models.TaskIdDto;
import com.example.kanbanbackend.task.models.TaskSetDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ListSetDto {
    private UUID id;
    private String name;
    private int listOrder;
    private Set<TaskIdDto> taskSet;
}
