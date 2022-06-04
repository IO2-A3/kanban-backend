package com.example.kanbanbackend.project.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class ProjectInputDTO {
    @NotBlank
    private String name;
}
