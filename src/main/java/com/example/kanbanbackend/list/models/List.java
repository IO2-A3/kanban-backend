package com.example.kanbanbackend.list.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class List {
    @Type(type="org.hibernate.type.UUIDCharType")
    @Id
    private UUID id;
    private UUID projectId;
    private String name; //todo: unique
    private Integer listOrder; // order to keyword w sqlu
}
