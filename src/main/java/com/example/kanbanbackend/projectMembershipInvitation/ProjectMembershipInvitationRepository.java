package com.example.kanbanbackend.projectMembershipInvitation;

import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProjectMembershipInvitationRepository extends JpaRepository<ProjectMembershipInvitation, UUID>
{
    Optional<ProjectMembershipInvitation> findByUserIdAndProjectId(UUID userId, UUID projectId);
    boolean existsByUserIdAndProjectId(UUID userId, UUID projectId);
}
