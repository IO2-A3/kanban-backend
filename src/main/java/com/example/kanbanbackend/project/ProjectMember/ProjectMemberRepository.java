package com.example.kanbanbackend.project.ProjectMember;

import com.example.kanbanbackend.project.ProjectMember.models.ProjectMember;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberKey> {
    boolean existsById(ProjectMemberKey key);
}
