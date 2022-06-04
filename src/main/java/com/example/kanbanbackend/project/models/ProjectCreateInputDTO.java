package com.example.kanbanbackend.project.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class ProjectCreateInputDTO extends ProjectInputDTO{
    @NotNull
    private UUID userId;
}
