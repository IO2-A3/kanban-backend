package com.example.kanbanbackend.projectMembershipInvitation.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class ProjectMembershipInvitation {
    @Type(type="org.hibernate.type.UUIDCharType")
    @Id
    private UUID id;
    private UUID userId;
    private UUID projectId;
    private Timestamp createdAt;
    private Boolean pending;
    private Boolean isAccepted;
}