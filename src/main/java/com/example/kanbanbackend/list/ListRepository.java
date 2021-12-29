package com.example.kanbanbackend.list;

import com.example.kanbanbackend.list.models.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<List, String> {
}
