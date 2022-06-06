package com.example.kanbanbackend.project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ProjectSetDto {
    private String name;
    private UUID id;
}
