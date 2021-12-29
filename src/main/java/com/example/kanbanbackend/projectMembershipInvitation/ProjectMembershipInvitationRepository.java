package com.example.kanbanbackend.projectMembershipInvitation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectMembershipInvitationRepository extends JpaRepository<ProjectMembershipInvitation, UUID>
{

}
