package com.example.kanbanbackend.project.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ProjectInputDTO {
    @NotBlank
    private String name;
    @NotNull
    private UUID userId;
}
