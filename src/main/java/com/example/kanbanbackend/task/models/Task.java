package com.example.kanbanbackend.task.models;

import com.example.kanbanbackend.list.models.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Task {
    @Type(type="org.hibernate.type.UUIDCharType")
    @Id
    private UUID id;

    @JsonBackReference
    @ManyToOne
    private List list;

    private String name;
    private String description; //todo: string -> text
    private Integer listOrder;
    private Timestamp dueDate;
}