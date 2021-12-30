package com.example.kanbanbackend.list.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ListSetDto {
    private UUID id;
    private int listOrder;
}
