package com.example.kanbanbackend.project.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProjectSetDto {
    private String name;
    private UUID id;
}
