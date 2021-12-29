package com.example.kanbanbackend.list.models;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class ListInputDto {
    @NotBlank
    private String name;
    @NotNull
    private UUID projectId;
}