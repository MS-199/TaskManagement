package com.taskmanagement.backend.repository;

import com.taskmanagement.backend.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ListRepository extends JpaRepository<TaskList, UUID> {
    List<TaskList> findByBoardIdOrderByOrderAsc(UUID boardId);
}
